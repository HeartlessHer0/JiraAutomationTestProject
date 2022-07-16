package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class SmokeUIDefs extends BaseCucumberTest {

    private static final Logger logger = LogManager.getLogger(SmokeUIDefs.class);

    @When("User go to the Jira Software page and create a new board  with some parameters")
    public void userGoToTheJiraSoftwarePageAndCreateANewBoardWithSomeParameters() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira All Projects page is opened");
        jiraAllProjectsPage.getAddBoardButton().click();
        jiraAllProjectsPage.getBoardTypeBoardButton("Kanban").click();
        jiraAllProjectsPage.getUsePatternButton().click();
        jiraAllProjectsPage.getTeamManagedButton().click();
        jiraAllProjectsPage.getBoardNameField().sendKeys(boardNames.get(0));
        jiraAllProjectsPage.getBoardKeyField().sendKeys(boardKeys.get(0));
        jiraAllProjectsPage.getCreateBoardWithParametersButton().click();
        logger.info("Smoke UI board is created");
    }

    @Then("User see created Board with entered parameters")
    public void userSeeCreatedBoardWithEnteredParameters() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(), boardNames.get(0));
        logger.info("Smoke UI board with entered parameters is displayed");
    }

    @When("User logout from account")
    public void userLogoutFromAccount() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira All Projects page is opened");
        jiraSoftwareNavigationPage.getProfileButton().click();
        jiraSoftwareNavigationPage.getLogoutButtonLocator().click();
        logger.info("Logout page is opened");
        logoutPage.getLogoutButton().click();
        logger.info("Logout button is clicked");
    }

    @Then("User see login page")
    public void userSeeLoginPage() {
        Assert.assertTrue(loginPage.getEmailInput().isDisplayed());
        logger.info("Login page is opened after Logout");
    }
}
