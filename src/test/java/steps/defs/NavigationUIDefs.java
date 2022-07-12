package steps.defs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class NavigationUIDefs extends BaseCucumberTest {

    @When("User click Jira Software button")
    public void userClickJiraSoftwareButton() {
        startPage.getJiraSoftwareButton().click();
    }

    @Then("User see Jira All Projects Page")
    public void userSeeJiraAllProjectsPage() {
        Assert.assertTrue(jiraAllProjectsPage.isPageOpened());
    }

    @When("User click Profile Setting Button")
    public void userClickProfileSettingButton() {
        startPage.getProfileButton().click();
        startPage.getAccountSettingsButton().click();
    }

    @Then("User see Profile Settings Page")
    public void userSeeProfileSettingsPage() {
        Assert.assertTrue(profileSettingsPage.isPageOpened());
    }

    @When("User go to Jira All Projects Page and  click some Board Button")
    public void userGoToJiraAllProjectsPageAndClickSomeBoardButton() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getProjectButton("TestBoard1").click();
    }

    @Then("User see Board Page")
    public void userSeeBoardPage() {
        Assert.assertTrue(boardPage.isPageOpened());
    }

    @When("User go to Jira All Projects Page and click Jira Software Main Button")
    public void userGoToJiraAllProjectsPageAndClickJiraSoftwareMainButton() {
        startPage.getJiraSoftwareButton().click();
        jiraSoftwareNavigationPage.getJiraSoftwareMainButton().click();
    }

    @Then("User see Jira Work Page")
    public void userSeeJiraWorkPage() {
        Assert.assertTrue(jiraWorkPage.isPageOpened());
    }

    @When("User click Quit Button")
    public void userClickQuitButton() {
        startPage.getProfileButton().click();
        startPage.getQuitButton().click();
    }

    @When("User click Project Settings Button")
    public void userClickProjectSettingsButton() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getProjectActionsButton("TestBoard1").click();
        jiraAllProjectsPage.getProjectSettingButton().click();
    }

    @Then("User see Project Settings Page")
    public void userSeeProjectSettingsPage() {
        Assert.assertTrue(projectSettingPage.isPageOpened());
    }

    @Then("User see Logout page")
    public void userSeeLogoutPage() {
        Assert.assertTrue(logoutPage.isPageOpened());
    }
}
