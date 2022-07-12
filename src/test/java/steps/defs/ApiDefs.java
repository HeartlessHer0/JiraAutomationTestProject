package steps.defs;

import com.google.gson.Gson;
import configuration.Endpoints;
import configuration.ReadProperties;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import models.Board;
import models.ListOfBoards;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiDefs extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;
    private ValidatableResponse validatableResponse;
    private Board expectedBoard = new Board();

    Gson gson = new Gson();

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

    /**
     *
     */

    @When("I want to get all boards")
    public void iWantToGetAllBoards() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .when()
                .get(Endpoints.GET_ALL_BOARDS)
                .then();
    }

    @When("I want to create a board according my testing standards")
    public void iWantToCreateABoardAccordingMyTestingStandards() {
        //todo self-made builder for it
        expectedBoard.setName("[AUTO TEST] Board name");
        expectedBoard.setType("kanban");
        expectedBoard.setFilterId(10000);

        validatableResponse = baseCucumberTest.restAssured.given()
                .body(gson.toJson(expectedBoard))
                .when()
                .post(Endpoints.CREATE_BOARD)
                .then();
    }

    @When("I want to get the board")
    public void iWantToGetTheBoard() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("boardId", baseBoard.getId())
                .log().params()//todo del
                .when()
                .get(Endpoints.GET_BOARD)
                .then();
    }

    @When("I want to delete the board")
    public void iWantToDeleteTheBoard() {
        validatableResponse = baseCucumberTest.restAssured.given()
                .pathParam("boardId", baseBoard.getId())
                .log().params()//todo del
                .when()
                .delete(Endpoints.DELETE_BOARD)
                .then();
    }

    /**
     *
     */

    @Then("I should get the correct response about all requested boards")
    public void iShouldGetTheCorrectResponseAboutAllRequestedBoards() {
        ListOfBoards response = validatableResponse
                .assertThat()
                .log().status().log().body()//todo del
                .statusCode(HttpStatus.SC_OK)
                .extract().as(ListOfBoards.class);

        Assert.assertEquals(response.getMaxResults(), response.MAX_RESULTS);
        Assert.assertEquals(response.getStartAt(), response.START_AT);

        if (response.getTotal() >= response.MAX_RESULTS) {
            Assert.assertEquals(response.getValues().length, response.MAX_RESULTS);
        } else {
            Assert.assertEquals(response.getValues().length, response.getTotal());
        }
    }

    @Then("I should get the correct response about the created board")
    public void iShouldGetTheCorrectResponseAboutTheCreatedBoard() {
        baseBoard = validatableResponse
                .assertThat()
                .log().status().log().body()//todo del
                .statusCode(HttpStatus.SC_CREATED)
                .extract().as(Board.class);

        Assert.assertEquals(baseBoard, expectedBoard);

        Matcher matcher = Pattern.compile("\\d+$").matcher(baseBoard.getSelf());
        matcher.find();
        Assert.assertEquals(Long.parseLong(matcher.group()), baseBoard.getId());
    }

    @Then("I should get the correct response about the requested board")
    public void iShouldGetTheCorrectResponseAboutTheRequestedBoard() {
        Board actualBoard = validatableResponse
                .assertThat()
                .log().status().log().body()//todo del
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Board.class);

        Assert.assertEquals(actualBoard, baseBoard);
    }

    @Then("I should get the correct response about the deleted board")
    public void iShouldGetTheCorrectResponseAboutTheDeletedBoard() {
        validatableResponse
                .assertThat()
                .log().status().log().body()//todo del
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
