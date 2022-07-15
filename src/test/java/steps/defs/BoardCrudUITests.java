package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class BoardCrudUITests extends BaseCucumberTest {
    private static final Logger logger = LogManager.getLogger(BoardCrudUITests.class);

    @When("User go to the Jira Software page and create a new board for CRUD test")
    public void userGoToTheJiraSoftwarePageAndCreateANewBoardForCRUDTest() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira All Projects Page is opened");
        jiraAllProjectsPage.getAddBoardButton().click();
        logger.info("Add Project widget is active");
        jiraAllProjectsPage.getBoardTypeBoardButton("Kanban").click();
        jiraAllProjectsPage.getUsePatternButton().click();
        jiraAllProjectsPage.getTeamManagedButton().click();
        jiraAllProjectsPage.getBoardNameField().sendKeys(boardNames.get(1));
        jiraAllProjectsPage.getBoardKeyField().sendKeys(boardKeys.get(1));
        jiraAllProjectsPage.getCreateBoardWithParametersButton().click();
        logger.info("CRUD Board is created");
    }

    @When("User open the created Board from the Projects page")
    public void userOpenTheCreatedBoardFromTheProjectsPage() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira All Projects Page is opened");
        jiraAllProjectsPage.getProjectButton(boardNames.get(1)).click();
        logger.info("CRUD Board is opened");
    }

    @Then("User see the created Board on Board page")
    public void userSeeTheCreatedBoardOnBoardPage() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(),boardNames.get(1));
        logger.info("CRUD Board parameters is checked");
    }

    @And("User update board parameters")
    public void userUpdateBoardParameters() {
        Actions actions = new Actions(driver);
        actions.moveToElement(boardPage.getProjectSettingButton()).click().build().perform();
        logger.info("Project Settings page is opened");
        projectSettingPage.getProjectNameFieldLocator().clear();
        projectSettingPage.getProjectNameFieldLocator().sendKeys(boardNames.get(5));
        projectSettingPage.getProjectKeyFieldLocator().clear();
        projectSettingPage.getProjectKeyFieldLocator().sendKeys(boardKeys.get(5));
        projectSettingPage.getSaveChangesButton().click();
        projectSettingPage.getBackToProjectButton().click();
        logger.info("CRUD Board parameters is update");
        boardPage.getCurrentBoardButton().click();
    }

    @Then("User see updated Board with entered parameters")
    public void userSeeUpdatedBoardWithEnteredParameters() {
        Assert.assertEquals(boardPage.getCurrentBoardButton().getText(), boardNames.get(5));
        logger.info("Update CRUD Board parameters is checked");
    }

    @When("User delete the created Board from the Projects page")
    public void userDeleteTheCreatedBoardFromTheProjectsPage() {
        startPage.getJiraSoftwareButton().click();
        logger.info("Jira All Projects Page is opened");
        jiraAllProjectsPage.getProjectActionsButton(boardNames.get(5)).click();
        jiraAllProjectsPage.getMoveToTrashProjectButton().click();
        jiraAllProjectsPage.getConfirmMoveToTrashProjectButton().click();
        logger.info("Updated CRUD Board is moved to Trash Page");
    }

    @Then("User see delete notification")
    public void userSeeDeleteNotification() {
        Assert.assertTrue(trashPage.getDeleteNotificationLocator().isDisplayed());
        logger.info("Delete notification is displayed");
    }

    @And("User delete the Board from the Trash page")
    public void userDeleteTheBoardFromTheTrashPage(){
        jiraAllProjectsPage.getGoToTrashPageButton().click();
        logger.info("Trash Page is Opened");
        trashPage.getBoardActionsButton(boardNames.get(5)).click();
        trashPage.getDeleteFromTrashButton().click();
        trashPage.getConfirmDeleteButton().click();
        logger.info("Updated CRUD Board is deleted");
    }
}
