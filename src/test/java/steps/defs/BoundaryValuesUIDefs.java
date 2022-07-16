package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class BoundaryValuesUIDefs extends BaseCucumberTest {

    private static final Logger logger = LogManager.getLogger(BoundaryValuesUIDefs.class);

    @And("User open Board creating page")
    public void userOpenBoardCreatingPage() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira All Projects page is opened");
        jiraAllProjectsPage.getAddBoardButton().click();
        jiraAllProjectsPage.getBoardTypeBoardButton("Kanban").click();
        jiraAllProjectsPage.getUsePatternButton().click();
        jiraAllProjectsPage.getTeamManagedButton().click();
        logger.info("Project Naming widget is opened");
    }

    @When("User enter in Board Name Field One symbol")
    public void userEnterInBoardNameFieldOneSymbol() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("1");
        logger.info("1 symbol entered to Name Field");
    }

    @Then("User see Title To Short message")
    public void userSeeTitleToShortMessage() {
        Assert.assertTrue(jiraAllProjectsPage.getTitleTooShortMessage().isDisplayed());
        logger.info("Error message is displayed");
    }

    @When("User enter in Board Name Field Two symbols")
    public void userEnterInBoardNameFieldTwoSymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("12");
        logger.info("2 symbols entered to Name Field");
        jiraAllProjectsPage.getBoardKeyField().click();
    }

    @When("User enter in Board Name Field Eighty symbols")
    public void userEnterInBoardNameFieldEightySymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
        logger.info("80 symbols entered to Name Field");
        jiraAllProjectsPage.getBoardKeyField().click();
    }

    @Then("User see generated Board Key")
    public void userSeeGeneratedBoardKey() {
        Assert.assertNotNull(jiraAllProjectsPage.getBoardNameField().getText());
        logger.info("Board Key is generated");
    }

    @When("User enter in Board Name Eighty One symbols")
    public void userEnterInBoardNameEightyOneSymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("123456789012345678901234567890123456789012345678901234567890123456789012345678901");
        logger.info("81 symbols entered to Name Field");
    }

    @Then("User see Title To Long message")
    public void userSeeTitleToLongMessage() {
        Assert.assertTrue(jiraAllProjectsPage.getTitleTooLongMessage().isDisplayed());
        logger.info("Error message is displayed");
    }

    @When("User dont enter symbols in Board Name Field")
    public void userDontEnterSymbolsInBoardNameField() {
        jiraAllProjectsPage.getCreateBoardWithParametersButton().click();
        logger.info("Name Field is clean");
    }

    @Then("User see Board Need Name message")
    public void userSeeBoardNeedNameMessage() {
        Assert.assertTrue(jiraAllProjectsPage.getNeedTitleMessage().isDisplayed());
        logger.info("Board Need Message notification is displayed");
    }

    @When("User enter in Board Name Field Tree symbols")
    public void userEnterInBoardNameFieldTreeSymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("123");
        logger.info("3 symbols entered to Name Field");
        jiraAllProjectsPage.getBoardKeyField().click();
    }

    @When("User enter in Board Name Field Seventy Nine symbols")
    public void userEnterInBoardNameFieldSeventyNineSymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("1234567890123456789012345678901234567890123456789012345678901234567890123456789");
        logger.info("79 symbol entered to Name Field");
        jiraAllProjectsPage.getBoardKeyField().click();
    }
}
