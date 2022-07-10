package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class BoardCrudUITests extends BaseCucumberTest {
    String boardName = "CrudBoardUI";
    String boardKey = "CBUI";

    @When("User go to the Jira Software page and create a new board for CRUD test")
    public void userGoToTheJiraSoftwarePageAndCreateANewBoardForCRUDTest() {
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
    @When("User open the created Board from the Projects page")
    public void userOpenTheCreatedBoardFromTheProjectsPage() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getProjectButton(boardName).click();
    }

    @Then("User see the created Board on Board page")
    public void userSeeTheCreatedBoardOnBoardPage() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(),boardName);
    }

    @And("User update board parameters")
    public void userUpdateBoardParameters() {
        boardPage.getProjectSettingButton().click();
        projectSettingPage.getProjectNameFieldLocator().clear();
        projectSettingPage.getProjectNameFieldLocator().sendKeys("CrudBoardUIUPD");
        projectSettingPage.getProjectKeyFieldLocator().clear();
        projectSettingPage.getProjectKeyFieldLocator().sendKeys("CBUPD");
        projectSettingPage.getSaveChangesButton().click();
        projectSettingPage.getBackToProjectButton().click();
        boardPage.getCurrentBoardButton().click();

    }

    @Then("User see updated Board with entered parameters")
    public void userSeeUpdatedBoardWithEnteredParameters() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(), "CrudBoardUIUPD");
    }

    @When("User delete the created Board from the Projects page")
    public void userDeleteTheCreatedBoardFromTheProjectsPage() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getProjectActionsButton("CrudBoardUIUPD").click();
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
        trashPage.getBoardActionsButton("CrudBoardUIUPD").click();
        trashPage.getDeleteFromTrashButton().click();
        trashPage.getConfirmDeleteButton().click();
    }

}
