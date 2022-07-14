package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class SmokeUIDefs extends BaseCucumberTest {

    @When("User go to the Jira Software page and create a new board  with some parameters")
    public void userGoToTheJiraSoftwarePageAndCreateANewBoardWithSomeParameters()  {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getAddBoardButton().click();
        jiraAllProjectsPage.getBoardTypeBoardButton("Kanban").click();
        jiraAllProjectsPage.getUsePatternButton().click();
        jiraAllProjectsPage.getTeamManagedButton().click();
        jiraAllProjectsPage.getBoardNameField().sendKeys(boardNames.get(0));
        jiraAllProjectsPage.getBoardKeyField().sendKeys(boardKeys.get(0));
        jiraAllProjectsPage.getCreateBoardWithParametersButton().click();
        boardPage.getCurrentBoardButton().isDisplayed();
    }

    @Then("User see created Board with entered parameters")
    public void userSeeCreatedBoardWithEnteredParameters() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(), boardNames.get(0));
    }

    @When("User logout from account")
    public void userLogoutFromAccount() throws InterruptedException {
        startPage.getJiraSoftwareButton().click();
        jiraSoftwareNavigationPage.getProfileButton().click();
        jiraSoftwareNavigationPage.getLogoutButtonLocator().click();
        logoutPage.getLogoutButton().click();
    }

    @Then("User see login page")
    public void userSeeLoginPage() {
        Assert.assertTrue(loginPage.getEmailInput().isDisplayed());
    }
}
