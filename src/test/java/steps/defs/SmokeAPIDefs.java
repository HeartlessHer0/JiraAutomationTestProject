package steps.defs;

import baseEntities.BaseCucumberTest;

import configuration.Endpoints;
import configuration.ReadProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import models.Issue;
import models.Project;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class SmokeAPIDefs extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;
    private ValidatableResponse validatableResponse;

    private Project actualProject;
    private Project[] arrayOfActualProjects;

    public SmokeAPIDefs(BaseCucumberTest baseCucumberTest) {
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

    @Given("a test project for the following tests has been created")
    public void aTestProjectForTheFollowingTestsHasBeenCreated() {
        iSendARequestToCreateAProjectAccordingMyTestingStandards();
        iGetACorrectResponseAboutTheCreatedProject();
        iRememberProjectIDAndSELFFieldsFromTheResponse();
    }

    /*
     *
     */

    @When("I send a request to create a project according my testing standards")
    public void iSendARequestToCreateAProjectAccordingMyTestingStandards() {
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

    @When("I send a request to get the previously created project")
    public void iSendARequestToGetThePreviouslyCreatedProject() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .get(Endpoints.GET_PROJECT)
                .then();
    }

    @When("I send a request to get all projects")
    public void iSendARequestToGetAllProjects() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .when()
                .get(Endpoints.GET_ALL_PROJECTS)
                .then();
    }

    @When("I send a request to update the previously created project")
    public void iSendARequestToUpdateThePreviouslyCreatedProject() {
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

    @When("I send a request to get all statuses for the previously created project")
    public void iSendARequestToGetAllStatusesForThePreviouslyCreatedProject() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .get(Endpoints.GET_ALL_STATUSES_FOR_PROJECT)
                .then();
    }

    @When("I send a request to delete the previously created project")
    public void iSendARequestToDeleteThePreviouslyCreatedProject() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .delete(Endpoints.DELETE_PROJECT)
                .then();
    }

    @When("I send a request to create an issue according my testing standards")
    public void iSendARequestToCreateAnIssueAccordingMyTestingStandards() {
        baseIssue = new Issue.Builder()
                .key(baseProject.getKey())
                .summary("[AUTO TEST] Summary")
                .name("Задача")
                .build();

        validatableResponse = baseCucumberTest.restAssured.given()
                .body(baseIssue, ObjectMapperType.GSON)
                .when()
                .post(Endpoints.CREATE_ISSUE)
                .then();
    }

    @When("I send a request to get the previously created issue")
    public void iSendARequestToGetThePreviouslyCreatedIssue() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("issueIdOrKey", baseIssue.getIssueKey())
                .when()
                .get(Endpoints.GET_ISSUE)
                .then();
    }

    @When("I send a request to edit the previously created issue")
    public void iSendARequestToEditThePreviouslyCreatedIssue() {
        String actualBaseIssueKey = baseIssue.getIssueKey();

        baseIssue = new Issue.Builder()
                .key(baseProject.getKey())
                .summary("[AUTO TEST] Edited summary")
                .name("Задача")
                .issueKey(actualBaseIssueKey)
                .build();

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("issueIdOrKey", actualBaseIssueKey)
                .body(baseIssue, ObjectMapperType.GSON)
                .when()
                .put(Endpoints.EDIT_ISSUE)
                .then();
    }

    @When("I send a request to delete the previously created issue")
    public void iSendARequestToDeleteThePreviouslyCreatedIssue() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("issueIdOrKey", baseIssue.getIssueKey())
                .when()
                .delete(Endpoints.DELETE_ISSUE)
                .then();
    }

    /*
     *
     */

    @Then("I get a correct response about the created project")
    public void iGetACorrectResponseAboutTheCreatedProject() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("key", equalTo(baseProject.getKey()));
    }

    @Then("I remember project ID and SELF fields from the response")
    public void iRememberProjectIDAndSELFFieldsFromTheResponse() {
        JsonPath jsonPath = validatableResponse.extract().jsonPath();

        baseProject.setSelf(jsonPath.getString("self"));
        baseProject.setId(jsonPath.getLong("id"));
    }

    @Then("I get a correct response about the requested project")
    public void iGetACorrectResponseAboutTheRequestedProject() {
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

    @Then("I get a correct response about all projects")
    public void iGetACorrectResponseAboutAllProjects() {
        arrayOfActualProjects = validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Project[].class);
    }

    @Then("my previously created project is among them")
    public void myPreviouslyCreatedProjectIsAmongThem() {
        Assert.assertEquals(arrayOfActualProjects[0], baseProject);
    }

    @Then("I get a correct response about all statuses for the requested project")
    public void iGetACorrectResponseAboutAllStatusesForTheRequestedProject() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Then("I get a correct response about the deleted project")
    public void iGetACorrectResponseAboutTheDeletedProject() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Then("I get a correct response about the created issue")
    public void iGetACorrectResponseAboutTheCreatedIssue() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body("key", startsWith(baseProject.getKey()));
    }

    @Then("I remember issue ID and SELF fields from the response")
    public void iRememberIssueIDAndSELFFieldsFromTheResponse() {
        JsonPath jsonPath = validatableResponse.extract().jsonPath();

        baseIssue.setIssueID(jsonPath.getLong("id"));
        baseIssue.setIssueKey(jsonPath.getString("key"));
        baseIssue.setIssueSelf(jsonPath.getString("self"));
    }

    @Then("I get a correct response about the requested issue")
    public void iGetACorrectResponseAboutTheRequestedIssue() {
        JsonPath jsonPath = validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath();

        Assert.assertEquals(jsonPath.getLong("id"), baseIssue.getIssueID());
        Assert.assertEquals(jsonPath.getString("self"), baseIssue.getIssueSelf());
        Assert.assertEquals(jsonPath.getString("key"), baseIssue.getIssueKey());
        Assert.assertEquals(jsonPath.getString("fields.summary"), baseIssue.getFields().getSummary());
    }

    @Then("I get a correct response about the edited issue")
    public void iGetACorrectResponseAboutTheEditedIssue() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Then("I get a correct response about the deleted issue")
    public void iGetACorrectResponseAboutTheDeletedIssue() {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
