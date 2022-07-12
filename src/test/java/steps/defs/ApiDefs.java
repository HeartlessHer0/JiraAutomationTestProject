package steps.defs;


import baseEntities.BaseCucumberTest;

import com.google.gson.Gson;

import configuration.Endpoints;
import configuration.ReadProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;


import models.Project;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiDefs extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;
    private ValidatableResponse validatableResponse;

    private Project actualProject;
    private Project[] arrayOfActualProjects;

    public ApiDefs(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    @Given("RestAssured is configured to the basic correct configuration")
    public void restassuredIsConfiguredToTheBasicCorrectConfiguration() {
        baseCucumberTest.restAssured.baseURI = ReadProperties.getUrl();

        baseCucumberTest.restAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
    }

    @Given("username and token are correct")
    public void usernameAndTokenAreCorrect() {
        baseCucumberTest.restAssured.requestSpecification = given()
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password());
    }

    /*
     *
     */

    @When("I want to create a project according my testing standards")
    public void iWantToCreateAProjectAccordingMyTestingStandards() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 999999 + 1);
        baseProject = new Project.Builder()
                .key("TEST" + randomNum)
                .name("[AUTO TEST] Name of test project " + randomNum)
                .description("[AUTO TEST] Description of test project")
                .projectTypeKey("software")
                .leadAccountId("70121:77540319-5931-4c40-a571-3bebf0eff56e")//todo make unhardcoded
                .build();

        validatableResponse = baseCucumberTest.restAssured.given()
                .body(baseProject, ObjectMapperType.GSON)
                .when()
                .post(Endpoints.CREATE_PROJECT)
                .then();
    }

    @When("I want to get the previously created project")
    public void iWantToGetThePreviouslyCreatedProject() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .get(Endpoints.GET_PROJECT)
                .then();
    }

    @When("I want to get all projects")
    public void iWantToGetAllProjects() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .when()
                .get(Endpoints.GET_ALL_PROJECTS)
                .then();
    }


    @When("I want to update the previously created project")
    public void iWantToUpdateThePreviouslyCreatedProject() {
        String previousBaseProjectKey = baseProject.getKey();

        int randomNum = ThreadLocalRandom.current().nextInt(0, 999999 + 1);
        baseProject = new Project.Builder()
                .key("TEST" + randomNum)
                .name("[AUTO TEST] Name of updated test project " + randomNum)
                .description("[AUTO TEST] Description of updated test project")
                .projectTypeKey("software")
                .leadAccountId("70121:77540319-5931-4c40-a571-3bebf0eff56e")//todo make unhardcoded
                .build();

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", previousBaseProjectKey)
                .body(baseProject, ObjectMapperType.GSON)
                .when()
                .put(Endpoints.UPDATE_PROJECT)
                .then();
    }


    @When("I want to get all statuses for the previously created project")
    public void iWantToGetAllStatusesForThePreviouslyCreatedProject() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .get(Endpoints.GET_ALL_STATUSES_FOR_PROJECT)
                .then();
    }


    @When("I want to delete the previously created project")
    public void iWantToDeleteThePreviouslyCreatedProject() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .delete(Endpoints.DELETE_PROJECT)
                .then();
    }

    /*
     *
     */

    @Then("I should get the correct response about the created project")
    public void iShouldGetTheCorrectResponseAboutTheCreatedProject() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("key", equalTo(baseProject.getKey()));
    }

    @Then("I will be able to remember ID and SELF fields from the response")
    public void iWillBeAbleToRememberIDAndSELFFieldsFromTheResponse() {
        JsonPath jsonPath = validatableResponse.extract().jsonPath();

        baseProject.setSelf(jsonPath.getString("self"));
        baseProject.setId(jsonPath.getLong("id"));
    }

    @Then("I should get the correct response about the requested project")
    public void iShouldGetTheCorrectResponseAboutTheRequestedProject() {
        actualProject = validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);

        Assert.assertEquals(actualProject, baseProject);
        Assert.assertEquals(actualProject.getDescription(), baseProject.getDescription());
        Assert.assertEquals(
                validatableResponse.extract().jsonPath().getString("lead.accountId"),
                baseProject.getLeadAccountId()
        );
    }

    @Then("I should get the correct response about all projects")
    public void iShouldGetTheCorrectResponseAboutAllProjects() {
        arrayOfActualProjects = validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Project[].class);
    }

    @Then("my previously created project should be among them")
    public void myPreviouslyCreatedProjectShouldBeAmongThem() {
        Assert.assertEquals(arrayOfActualProjects[0], baseProject);

//        boolean flagOfPresence = false;todo wtf flaky test?
//        for (Project project : arrayOfActualProjects) {
//            try {
//                Assert.assertEquals(project, baseProject);
//                flagOfPresence = true;
//                break;
//            } catch (AssertionError assertionError) {
//                System.out.println("Trying once again...");//todo rename it
//            }
//        }
//        Assert.assertTrue(flagOfPresence);
    }

    @Then("I should get the correct response about all statuses for the requested project")
    public void iShouldGetTheCorrectResponseAboutAllStatusesForTheRequestedProject() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Then("I should get the correct response about the deleted project")
    public void iShouldGetTheCorrectResponseAboutTheDeletedProject() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
