package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.*;

public class BoundaryValuesUIDefs extends BaseCucumberTest {
//    JiraAllProjectsPage jiraAllProjectsPage = new JiraAllProjectsPage(driver);
//    JiraSoftwareNavigationPage jiraSoftwareNavigationPage = new JiraSoftwareNavigationPage(driver);
//    JiraWorkPage jiraWorkPage = new JiraWorkPage(driver);
//    StartPage startPage = new StartPage(driver);
//    BoardPage boardPage = new BoardPage(driver);
//    LogoutPage logoutPage = new LogoutPage(driver);
//    LoginPage loginPage = new LoginPage(driver);
//    ProjectSettingPage projectSettingPage = new ProjectSettingPage(driver);

    @And("User open Board creating page")
    public void userOpenBoardCreatingPage() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getAddBoardButton().click();
        jiraAllProjectsPage.getBoardTypeBoardButton("Kanban").click();
        jiraAllProjectsPage.getUsePatternButton().click();
        jiraAllProjectsPage.getTeamManagedButton().click();
    }

    @When("User enter in Board Name Field One symbol")
    public void userEnterInBoardNameFieldOneSymbol() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("1");
    }

    @Then("User see Title To Short message")
    public void userSeeTitleToShortMessage() {
        Assert.assertTrue(jiraAllProjectsPage.getTitleTooShortMessage().isDisplayed());
    }

    @When("User enter in Board Name Field Two symbols")
    public void userEnterInBoardNameFieldTwoSymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("12");
        jiraAllProjectsPage.getBoardKeyField().click();
    }

    @When("User enter in Board Name Field Eighty symbols")
    public void userEnterInBoardNameFieldEightySymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
        jiraAllProjectsPage.getBoardKeyField().click();
    }

    @Then("User see generated Board Key")
    public void userSeeGeneratedBoardKey() {
        Assert.assertNotNull(jiraAllProjectsPage.getBoardNameField().getText());
    }

    @When("User enter in Board Name Eighty One symbols")
    public void userEnterInBoardNameEightyOneSymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("123456789012345678901234567890123456789012345678901234567890123456789012345678901");
    }

    @Then("User see Title To Long message")
    public void userSeeTitleToLongMessage() {
        Assert.assertTrue(jiraAllProjectsPage.getTitleTooLongMessage().isDisplayed());
    }

    @When("User dont enter symbols in Board Name Field")
    public void userDontEnterSymbolsInBoardNameField() {
        jiraAllProjectsPage.getCreateBoardWithParametersButton().click();
    }

    @Then("User see Board Need Name message")
    public void userSeeBoardNeedNameMessage() {
        Assert.assertTrue(jiraAllProjectsPage.getNeedTitleMessage().isDisplayed());
    }

    @When("User enter in Board Name Field Tree symbols")
    public void userEnterInBoardNameFieldTreeSymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("123");
        jiraAllProjectsPage.getBoardKeyField().click();
    }

    @When("User enter in Board Name Field Seventy Nine symbols")
    public void userEnterInBoardNameFieldSeventyNineSymbols() {
        jiraAllProjectsPage.getBoardNameField().sendKeys("1234567890123456789012345678901234567890123456789012345678901234567890123456789");
        jiraAllProjectsPage.getBoardKeyField().click();
    }
}
