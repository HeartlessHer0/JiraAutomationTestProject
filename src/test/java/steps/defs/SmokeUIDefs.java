package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;

public class SmokeUIDefs extends BaseCucumberTest {

    private  String boardName = "SmokeUI11";
    private  String boardKey = "SM11";
    JiraAllProjectsPage jiraAllProjectsPage = new JiraAllProjectsPage(driver);
    JiraSoftwareNavigationPage jiraSoftwareNavigationPage = new JiraSoftwareNavigationPage(driver);
    JiraWorkPage jiraWorkPage = new JiraWorkPage(driver);
    StartPage startPage = new StartPage(driver);
    BoardPage boardPage = new BoardPage(driver);
    LogoutPage logoutPage = new LogoutPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    ProjectSettingPage projectSettingPage = new ProjectSettingPage(driver);


    @When("User go to the Jira Software page and create a new board  with some parameters")
    public void userGoToTheJiraSoftwarePageAndCreateANewBoardWithSomeParameters()  {


        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getAddBoardButton().click();
        jiraAllProjectsPage.getBoardTypeBoardButton("Kanban").click();
        jiraAllProjectsPage.getUsePatternButton().click();
        jiraAllProjectsPage.getTeamManagedButton().click();
        jiraAllProjectsPage.getBoardNameField().sendKeys(boardName);
        jiraAllProjectsPage.getBoardKeyField().sendKeys(boardKey);
        jiraAllProjectsPage.getCreateBoardWithParametersButton().click();
        boardPage.getCurrentBoardButton().isDisplayed();

    }

    @Then("User see created Board with entered parameters")
    public void userSeeCreatedBoardWithEnteredParameters() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(), boardName);
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