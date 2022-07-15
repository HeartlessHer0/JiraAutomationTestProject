package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class BoardCrudUITests extends BaseCucumberTest {

    @When("User go to the Jira Software page and create a new board for CRUD test")
    public void userGoToTheJiraSoftwarePageAndCreateANewBoardForCRUDTest() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getAddBoardButton().click();
        jiraAllProjectsPage.getBoardTypeBoardButton("Kanban").click();
        jiraAllProjectsPage.getUsePatternButton().click();
        jiraAllProjectsPage.getTeamManagedButton().click();
        jiraAllProjectsPage.getBoardNameField().sendKeys(boardNames.get(1));
        jiraAllProjectsPage.getBoardKeyField().sendKeys(boardKeys.get(1));
        jiraAllProjectsPage.getCreateBoardWithParametersButton().click();
        boardPage.getCurrentBoardButton().isDisplayed();
    }

    @When("User open the created Board from the Projects page")
    public void userOpenTheCreatedBoardFromTheProjectsPage() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getProjectButton(boardNames.get(1)).click();
    }

    @Then("User see the created Board on Board page")
    public void userSeeTheCreatedBoardOnBoardPage() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(),boardNames.get(1));
    }

    @And("User update board parameters")
    public void userUpdateBoardParameters() {
        Actions actions = new Actions(driver);
        actions.moveToElement(boardPage.getProjectSettingButton()).click().build().perform();
        projectSettingPage.getProjectNameFieldLocator().clear();
        projectSettingPage.getProjectNameFieldLocator().sendKeys(boardNames.get(5));
        projectSettingPage.getProjectKeyFieldLocator().clear();
        projectSettingPage.getProjectKeyFieldLocator().sendKeys(boardKeys.get(5));
        projectSettingPage.getSaveChangesButton().click();
        projectSettingPage.getBackToProjectButton().click();
        boardPage.getCurrentBoardButton().click();
    }

    @Then("User see updated Board with entered parameters")
    public void userSeeUpdatedBoardWithEnteredParameters() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(), boardNames.get(5));
    }

    @When("User delete the created Board from the Projects page")
    public void userDeleteTheCreatedBoardFromTheProjectsPage() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getProjectActionsButton(boardNames.get(5)).click();
        jiraAllProjectsPage.getMoveToTrashProjectButton().click();
        jiraAllProjectsPage.getConfirmMoveToTrashProjectButton().click();
    }

    @Then("User see delete notification")
    public void userSeeDeleteNotification() {
        Assert.assertTrue(trashPage.getDeleteNotificationLocator().isDisplayed());
    }

    @And("User delete the Board from the Trash page")
    public void userDeleteTheBoardFromTheTrashPage(){
        jiraAllProjectsPage.getGoToTrashPageButton().click();
        trashPage.getBoardActionsButton(boardNames.get(5)).click();
        trashPage.getDeleteFromTrashButton().click();
        trashPage.getConfirmDeleteButton().click();
    }
}
