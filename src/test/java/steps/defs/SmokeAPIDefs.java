package steps.defs;

import baseEntities.BaseCucumberTest;

import configuration.Endpoints;
import configuration.ReadProperties;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.concurrent.ThreadLocalRandom;

import static baseEntities.AuthEntities.LOGIN;
import static baseEntities.AuthEntities.TOKEN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

public class SmokeAPIDefs extends BaseCucumberTest {

    private Project actualProject;
    private Project[] arrayOfActualProjects;
    private BaseCucumberTest baseCucumberTest;
    private ValidatableResponse validatableResponse;

    public SmokeAPIDefs(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    private static final Logger logger = LogManager.getLogger(SmokeAPIDefs.class);

    private static final String LEAD_ACCOUNT_ID = "70121:77540319-5931-4c40-a571-3bebf0eff56e";

    @Given("RestAssured is configured to the basic correct configuration")
    public void restassuredIsConfiguredToTheBasicCorrectConfiguration() {
        logger.info("Configuring RestAssured...");

        baseCucumberTest.restAssured.baseURI = ReadProperties.getUrl();

        baseCucumberTest.restAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);

        logger.info("... RestAssured is successfully configured!");
    }

    @Given("username and token are correct")
    public void usernameAndTokenAreCorrect() {
        logger.info("Configuring username and token...");

        baseCucumberTest.restAssured.requestSpecification = given()
                .auth().preemptive().basic(LOGIN, TOKEN);

        logger.info("... username and token are successfully configured!");
    }

    @Given("a test project for the following tests has been created")
    public void aTestProjectForTheFollowingTestsHasBeenCreated() {
        logger.info("Creating a new test project... ... ...");

        iSendARequestToCreateAProjectAccordingMyTestingStandards();
        iGetACorrectResponseAboutTheCreatedProject();
        iRememberProjectIDAndSELFFieldsFromTheResponse();

        logger.info("... ... ... the new test project is successfully created!");
    }


    @When("I send a request to create a project according my testing standards")
    public void iSendARequestToCreateAProjectAccordingMyTestingStandards() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 999999 + 1);
        baseProject = new Project.Builder()
                .key("TEST" + randomNum)
                .name("[AUTO TEST] Name of test project " + randomNum)
                .description("[AUTO TEST] Description of test project")
                .projectTypeKey("software")
                .leadAccountId("70121:77540319-5931-4c40-a571-3bebf0eff56e")
                .build();

        logger.info("Sending a POST request to the CREATE_PROJECT endpoint;" +
                " body of request [" + baseProject.toString() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .body(baseProject, ObjectMapperType.GSON)
                .when()
                .post(Endpoints.CREATE_PROJECT)
                .then();

        logger.info("... the request sent!");
    }

    @When("I send a request to get the previously created project")
    public void iSendARequestToGetThePreviouslyCreatedProject() {
        logger.info("Sending a GET request to the GET_PROJECT endpoint;" +
                " pathParam 'projectIdOrKey' [" + baseProject.getKey() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .get(Endpoints.GET_PROJECT)
                .then();

        logger.info("... the request sent!");
    }

    @When("I send a request to get all projects")
    public void iSendARequestToGetAllProjects() {
        logger.info("Sending a GET request to the GET_ALL_PROJECTS endpoint...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .when()
                .get(Endpoints.GET_ALL_PROJECTS)
                .then();

        logger.info("... the request sent!");
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
                .leadAccountId(LEAD_ACCOUNT_ID)
                .build();

        logger.info("Sending a PUT request to the UPDATE_PROJECT endpoint;" +
                " pathParam 'projectIdOrKey' [" + previousBaseProjectKey + "]," +
                " body of request [" + baseProject.toString() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", previousBaseProjectKey)
                .body(baseProject, ObjectMapperType.GSON)
                .when()
                .put(Endpoints.UPDATE_PROJECT)
                .then();

        logger.info("... the request sent!");
    }

    @When("I send a request to get all statuses for the previously created project")
    public void iSendARequestToGetAllStatusesForThePreviouslyCreatedProject() {
        logger.info("Sending a GET request to the GET_ALL_STATUSES_FOR_PROJECT endpoint;" +
                " pathParam 'projectIdOrKey' [" + baseProject.getKey() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .get(Endpoints.GET_ALL_STATUSES_FOR_PROJECT)
                .then();

        logger.info("... the request sent!");
    }

    @When("I send a request to delete the previously created project")
    public void iSendARequestToDeleteThePreviouslyCreatedProject() {
        logger.info("Sending a DELETE request to the DELETE_PROJECT endpoint;" +
                " pathParam 'projectIdOrKey' [" + baseProject.getKey() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("projectIdOrKey", baseProject.getKey())
                .when()
                .delete(Endpoints.DELETE_PROJECT)
                .then();

        logger.info("... the request sent!");
    }

    @When("I send a request to create an issue according my testing standards")
    public void iSendARequestToCreateAnIssueAccordingMyTestingStandards() {
        baseIssue = new Issue.Builder()
                .key(baseProject.getKey())
                .summary("[AUTO TEST] Summary")
                .name("Задача")
                .build();

        logger.info("Sending a POST request to the CREATE_ISSUE endpoint;" +
                " body of request [" + baseIssue.toString() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .body(baseIssue, ObjectMapperType.GSON)
                .when()
                .post(Endpoints.CREATE_ISSUE)
                .then();

        logger.info("... the request sent!");
    }

    @When("I send a request to get the previously created issue")
    public void iSendARequestToGetThePreviouslyCreatedIssue() {
        logger.info("Sending a GET request to the GET_ISSUE endpoint;" +
                " pathParam 'issueIdOrKey' [" + baseIssue.getIssueKey() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("issueIdOrKey", baseIssue.getIssueKey())
                .when()
                .get(Endpoints.GET_ISSUE)
                .then();

        logger.info("... the request sent!");
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

        logger.info("Sending a PUT request to the EDIT_ISSUE endpoint;" +
                " pathParam 'issueIdOrKey' [" + actualBaseIssueKey + "]," +
                " body of request [" + baseIssue.toString() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("issueIdOrKey", actualBaseIssueKey)
                .body(baseIssue, ObjectMapperType.GSON)
                .when()
                .put(Endpoints.EDIT_ISSUE)
                .then();

        logger.info("... the request sent!");
    }

    @When("I send a request to delete the previously created issue")
    public void iSendARequestToDeleteThePreviouslyCreatedIssue() {
        logger.info("Sending a DELETE request to the DELETE_ISSUE endpoint;" +
                " pathParam 'issueIdOrKey' [" + baseIssue.getIssueKey() + "]...");

        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("issueIdOrKey", baseIssue.getIssueKey())
                .when()
                .delete(Endpoints.DELETE_ISSUE)
                .then();

        logger.info("... the request sent!");
    }


    @Then("I get a correct response about the created project")
    public void iGetACorrectResponseAboutTheCreatedProject() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_CREATED + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_CREATED);

            logger.info("... status code assertion successfully completed...");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }

        try {
            validatableResponse
                    .assertThat().body("key", equalTo(baseProject.getKey()));

            logger.info("... body assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I remember project ID and SELF fields from the response")
    public void iRememberProjectIDAndSELFFieldsFromTheResponse() {
        logger.info("Remembering project ID and SELF fields from the response...");

        JsonPath jsonPath = validatableResponse.extract().jsonPath();

        long id = jsonPath.getLong("id");
        String self = jsonPath.getString("self");

        logger.info("... values: ID [" + id + "], SELF [" + self + "] ...");

        baseProject.setId(id);
        baseProject.setSelf(self);

        logger.info("... remembering is successfully completed!");
    }

    @Then("I get a correct response about the requested project")
    public void iGetACorrectResponseAboutTheRequestedProject() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_OK + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions and deserialization...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_OK);

            logger.info("... status code assertion successfully completed...");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }

        try {
            actualProject = validatableResponse.extract().as(Project.class);

            logger.info("... deserialization successfully completed...");
        } catch (Exception exception) {
            logger.error("... " + exception);

            Assert.fail(exception.getMessage());
        }

        try {
            Assert.assertEquals(actualProject, baseProject);
            Assert.assertEquals(actualProject.getDescription(), baseProject.getDescription());
            Assert.assertEquals(
                    validatableResponse.extract().jsonPath().getString("lead.accountId"),
                    baseProject.getLeadAccountId()
            );

            logger.info("... body assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I get a correct response about all projects")
    public void iGetACorrectResponseAboutAllProjects() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_OK + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions and deserialization...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_OK);

            logger.info("... status code assertion successfully completed...");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }

        try {
            arrayOfActualProjects = validatableResponse.extract().as(Project[].class);

            logger.info("... deserialization successfully completed!");
        } catch (Exception exception) {
            logger.error("... " + exception);

            Assert.fail(exception.getMessage());
        }
    }

    @Then("my previously created project is among them")
    public void myPreviouslyCreatedProjectIsAmongThem() {
        logger.info("Containing assertion...");

        try {
            Assert.assertEquals(arrayOfActualProjects[0], baseProject);

            logger.info("... assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I get a correct response about all statuses for the requested project")
    public void iGetACorrectResponseAboutAllStatusesForTheRequestedProject() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_OK + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_OK);

            logger.info("... status code assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I get a correct response about the deleted project")
    public void iGetACorrectResponseAboutTheDeletedProject() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_NO_CONTENT + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);

            logger.info("... status code assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I get a correct response about the created issue")
    public void iGetACorrectResponseAboutTheCreatedIssue() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_CREATED + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_CREATED);

            logger.info("... status code assertion successfully completed...");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }

        try {
            validatableResponse
                    .assertThat().body("key", startsWith(baseProject.getKey()));

            logger.info("... body assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I remember issue ID, KEY and SELF fields from the response")
    public void iRememberIssueIDKEYAndSELFFieldsFromTheResponse() {
        logger.info("Remembering issue ID, KEY and SELF fields from the response...");

        JsonPath jsonPath = validatableResponse.extract().jsonPath();

        long id = jsonPath.getLong("id");
        String key = jsonPath.getString("key");
        String self = jsonPath.getString("self");

        logger.info("... values: ID [" + id + "], KEY [" + key + "], SELF [" + self + "] ...");

        baseIssue.setIssueID(id);
        baseIssue.setIssueKey(key);
        baseIssue.setIssueSelf(self);

        logger.info("... remembering is successfully completed!");
    }

    @Then("I get a correct response about the requested issue")
    public void iGetACorrectResponseAboutTheRequestedIssue() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_OK + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_OK);

            logger.info("... status code assertion successfully completed...");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }

        JsonPath jsonPath = validatableResponse.extract().jsonPath();

        try {
            Assert.assertEquals(jsonPath.getLong("id"), baseIssue.getIssueID());
            Assert.assertEquals(jsonPath.getString("self"), baseIssue.getIssueSelf());
            Assert.assertEquals(jsonPath.getString("key"), baseIssue.getIssueKey());
            Assert.assertEquals(jsonPath.getString("fields.summary"), baseIssue.getFields().getSummary());

            logger.info("... body assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I get a correct response about the edited issue")
    public void iGetACorrectResponseAboutTheEditedIssue() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_NO_CONTENT + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);

            logger.info("... status code assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I get a correct response about the deleted issue")
    public void iGetACorrectResponseAboutTheDeletedIssue() {
        logger.info("Status codes of response: " +
                "actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + HttpStatus.SC_NO_CONTENT + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions...");

        try {
            validatableResponse
                    .assertThat().statusCode(HttpStatus.SC_NO_CONTENT);

            logger.info("... status code assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }
}
