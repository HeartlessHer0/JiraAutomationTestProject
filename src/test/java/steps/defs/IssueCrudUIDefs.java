package steps.defs;

import baseEntities.BaseCucumberTest;
import configuration.ReadProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import services.WaitsService;

import java.time.Duration;

public class IssueCrudUIDefs extends BaseCucumberTest {

    WaitsService waitsService = new WaitsService(driver, Duration.ofSeconds(ReadProperties.timeout()));

    private static final Logger logger = LogManager.getLogger(IssueCrudUIDefs.class);

    private final String testBoard = "TestBoard1";

    @And("User open the Test Board")
    public void userOpenTheTestBoard() {
        startPage.getJiraSoftwareButton().click();

        logger.info("Jira All Projects Page is opened");

        jiraAllProjectsPage.getProjectButton(testBoard).click();

        logger.info("Test Board Page is opened");
    }

    @When("User create issue with some parameters")
    public void userCreateIssueWithSomeParameters() {
        boardPage.getCreateIssueButton("К выполнению").click();

        logger.info("Issue Field Widget is Active");

        boardPage.getIssueNameFieldLocator().sendKeys(boardNames.get(6));
        boardPage.getIssueNameFieldLocator().sendKeys(Keys.ENTER);
        boardPage.getCurrentBoardButton().click();

        logger.info("CRUD Issue is created");
    }

    @Then("User see created Issue on the Test Board Page")
    public void userSeeCreatedIssueOnTheTestBoardPage() {
        Assert.assertTrue(boardPage.getOpenIssueButtonLocator(boardNames.get(6)).isDisplayed());

        logger.info("CRUD Issue is displayed on the page");
    }

    @When("User open created Issue")
    public void userOpenCreatedIssue() {
        boardPage.getOpenIssueButtonLocator(boardNames.get(6)).click();

        logger.info("CRUD Issue Widget is opened");
    }

    @Then("User see Window with created Issue")
    public void userSeeWindowWithCreatedIssue() {
        Assert.assertEquals(boardPage.getIssueOpenNameFieldLocator().getText(), boardNames.get(6));

        logger.info("CRUD Issue With test name is displayed");
    }

    @And("User update Issue parameters")
    public void userUpdateIssueParameters() {
        Actions actions = new Actions(driver);
        actions
                .doubleClick(boardPage.getIssueOpenNameFieldLocator())
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(boardKeys.get(6))
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        logger.info("CRUD Issue parameters are updated");
    }

    @Then("User see Window with updated Issue")
    public void userSeeWindowWithUpdatedIssue() {
        Assert.assertEquals(boardPage.getIssueOpenNameFieldLocator().getText(), boardKeys.get(6));

        logger.info("CRUD Issue with updated parameters is displayed");
    }

    @When("User delete created and updated Issue")
    public void userDeleteCreatedAndUpdatedIssue() {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(boardPage.getOpenIssueButtonLocator(boardKeys.get(6)))
                .build()
                .perform();

        boardPage.getIssueActionsButton(boardKeys.get(6)).click();
        boardPage.getActionDeleteIssueButton().click();
        boardPage.getConfirmDeleteIssueButton().click();

        logger.info("Updated CRUD Issue is deleted");
    }

    @Then("User see clean board")
    public void userCleanBoard() {
        Assert.assertTrue(waitsService.waitForInvisibilityElement(By.xpath(boardPage.getOpenIssueButtonInvLocator(boardKeys.get(6)))));

        logger.info("Updated CRUD Issue is not displayed");
    }
}
