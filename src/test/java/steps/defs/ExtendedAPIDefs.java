package steps.defs;

import baseEntities.BaseCucumberTest;
import configuration.Endpoints;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ExtendedAPIDefs extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;

    public ExtendedAPIDefs(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    private static final Logger logger = LogManager.getLogger(ExtendedAPIDefs.class);

    private ValidatableResponse validatableResponse;

    @When("I send a request to get the project with PROJECT_ID or KEY {string}")
    public void iSendARequestToGetTheProjectWithPROJECT_IDOrKEY(String projectIdOrKey) {
        logger.info("Sending a GET request to the GET_PROJECT endpoint; pathParam 'projectIdOrKey' [" + projectIdOrKey + "]...");

        validatableResponse = given()
                .pathParam("projectIdOrKey", projectIdOrKey)
                .when()
                .get(Endpoints.GET_PROJECT)
                .then();

        logger.info("... the request sent!");
    }


    @Then("I get the status code {int}")
    public void iGetTheStatusCode(int statusCode) {
        logger.info("Status codes of response: actual [" + validatableResponse.extract().statusCode() + "], " +
                "expected [" + statusCode + "]");
        logger.info("Body of response [" + validatableResponse.extract().body().asString() + "]");
        logger.info("Assertions...");

        try {
            validatableResponse
                    .assertThat().statusCode(statusCode);

            logger.info("... status codes assertion successfully completed!");
        } catch (AssertionError assertionError) {
            logger.error("... " + assertionError);

            Assert.fail(assertionError.getMessage());
        }
    }

    @Then("I get a response with the error message {string}")
    public void iGetAResponseWithTheErrorMessage(String expectedErrorMsg) {
        if (!Objects.equals(expectedErrorMsg, "<none>")) {
            String actualErrorMsg = validatableResponse.extract().jsonPath().getString("errorMessages[0]");

            logger.info("Messages of response: actual [" + actualErrorMsg + "], " +
                    "expected [" + expectedErrorMsg + "]...");

            try {
                Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

                logger.info("... messages assertion successfully completed!");
            } catch (AssertionError assertionError) {
                logger.error("... " + assertionError);

                Assert.fail(assertionError.getMessage());
            }
        }
    }
}
