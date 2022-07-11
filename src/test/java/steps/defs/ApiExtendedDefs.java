package steps.defs;

import baseEntities.BaseCucumberTest;
import configuration.Endpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ApiExtendedDefs extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;
    private ValidatableResponse validatableResponse;

    Map<String, Object> jsonAsMap = new HashMap<>();

    public ApiExtendedDefs(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    @Given("Body of my request has parameter {string} with the value {string} of {string}")
    public void bodyOfMyRequestHasParameterWithTheValueOf(String key, String value, String typeOf) {
        if (!Objects.equals(value, "<empty>")) {
            switch (typeOf) {
                case "String": {
                    jsonAsMap.put(key, value);
                    break;
                }

                case "Integer": {
                    jsonAsMap.put(key, Integer.parseInt(value));
                    break;
                }

                case "Boolean": {
                    jsonAsMap.put(key, Boolean.parseBoolean(value));
                    break;
                }
            }
        }
    }

    @Given("Body of my request is {string}")
    public void bodyOfMyRequestIs(String bodyOfMyRequest) {
        switch (bodyOfMyRequest) {
            case "<empty>": {
                break;
            }

            case "<already_filled_by_jsonAsMap>": {
                baseCucumberTest.restAssured.given()
                        .body(jsonAsMap);
                break;
            }

            default: {
                baseCucumberTest.restAssured.given()
                        .body(bodyOfMyRequest);
                break;
            }
        }
    }

    /*
     *
     */


    @When("I want to get the project with PROJECT_ID or KEY {string}")
    public void iWantToGetTheProjectWithPROJECT_IDOrKEY(String projectIdOrKey) {
        validatableResponse = given()
                .pathParam("projectIdOrKey", projectIdOrKey)
                .when()
                .get(Endpoints.GET_PROJECT)
                .then();
    }

    /*
     *
     */

    @Then("I should get the status code {int}")
    public void iShouldGetTheStatusCode(int statusCode) {
        validatableResponse
                .log().status().log().body()//todo del
                .assertThat()
                .statusCode(statusCode);
    }

    @Then("I should get a response with the error message {string}")
    public void iShouldGetAResponseWithTheErrorMessage(String errorMessage) {
        if (!Objects.equals(errorMessage, "<none>")) {
            Assert.assertEquals(
                    validatableResponse.extract().jsonPath().getString("errorMessages[0]"),
                    errorMessage
            );
        }
    }
}
