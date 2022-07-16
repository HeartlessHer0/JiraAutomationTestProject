package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class NavigationUIDefs extends BaseCucumberTest {

    private static final Logger logger = LogManager.getLogger(NavigationUIDefs.class);

    @When("User click Jira Software button")
    public void userClickJiraSoftwareButton() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira Software button is clicked");
    }

    @Then("User see Jira All Projects Page")
    public void userSeeJiraAllProjectsPage() {
        Assert.assertTrue(jiraAllProjectsPage.isPageOpened());
        logger.info("Jira All Projects page is opened");
    }

    @When("User click Profile Setting Button")
    public void userClickProfileSettingButton() {
        startPage.getProfileButton().click();
        logger.info("Profile button is clicked");
        startPage.getAccountSettingsButton().click();
        logger.info("Account Settings button is clicked");
    }

    @Then("User see Profile Settings Page")
    public void userSeeProfileSettingsPage() {
        Assert.assertTrue(profileSettingsPage.isPageOpened());
        logger.info("Profile Settings page is opened");
    }

    @When("User go to Jira All Projects Page and  click some Board Button")
    public void userGoToJiraAllProjectsPageAndClickSomeBoardButton() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira Software button is clicked");
        jiraAllProjectsPage.getProjectButton("TestBoard1").click();
        logger.info("Test Board button is clicked");
    }

    @Then("User see Board Page")
    public void userSeeBoardPage() {
        Assert.assertTrue(boardPage.isPageOpened());
        logger.info("Test Board Page is opened");
    }

    @When("User go to Jira All Projects Page and click Jira Software Main Button")
    public void userGoToJiraAllProjectsPageAndClickJiraSoftwareMainButton() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira Software button is clicked");
        Actions actions = new Actions(driver);
        actions
                .moveToElement(jiraSoftwareNavigationPage.getJiraSoftwareMainButton())
                .click(jiraSoftwareNavigationPage.getJiraSoftwareMainButton())
                        .build()
                                .perform();
        logger.info("Jira Software Main button is clicked");
    }

    @Then("User see Jira Work Page")
    public void userSeeJiraWorkPage() {
        Assert.assertTrue(jiraWorkPage.isPageOpened());
        logger.info("Jira Work Page is opened");
    }

    @When("User click Quit Button")
    public void userClickQuitButton() {
        startPage.getProfileButton().click();
        logger.info("Profile button is clicked");
        startPage.getQuitButton().click();
        logger.info("Quit button is clicked");
    }

    @When("User click Project Settings Button")
    public void userClickProjectSettingsButton() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira Software button is clicked");
        jiraAllProjectsPage.getProjectActionsButton("TestBoard1").click();
        logger.info("Test Board button is clicked");
        jiraAllProjectsPage.getProjectSettingButton().click();
        logger.info("Project Settings button is clicked");
    }

    @Then("User see Project Settings Page")
    public void userSeeProjectSettingsPage() {
        Assert.assertTrue(projectSettingPage.isPageOpened());
        logger.info("Project Settings page is opened");
    }

    @Then("User see Logout page")
    public void userSeeLogoutPage() {
        Assert.assertTrue(logoutPage.isPageOpened());
        logger.info("Logout page is opened");
    }
}
