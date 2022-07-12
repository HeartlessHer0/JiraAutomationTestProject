package steps.defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class IssueCrudUIDefs extends BaseCucumberTest {
    String testBoard = "TestBoard1";
    String issueName = "CRUD_Issue";
    String updName = "CRUD_Update";

    @And("User open the Test Board")
    public void userOpenTheTestBoard() {
        startPage.getJiraSoftwareButton().click();
        jiraAllProjectsPage.getProjectButton(testBoard).click();
    }

    @When("User create issue with some parameters")
    public void userCreateIssueWithSomeParameters() {
        boardPage.getCreateIssueButton("К выполнению").click();
        boardPage.getIssueNameFieldLocator().sendKeys(issueName);
        boardPage.getIssueNameFieldLocator().sendKeys(Keys.ENTER);
        boardPage.getCurrentBoardButton().click();
    }

    @Then("User see created Issue on the Test Board Page")
    public void userSeeCreatedIssueOnTheTestBoardPage() {
        Assert.assertTrue(boardPage.getOpenIssueButtonLocator(issueName).isDisplayed());
    }

    @When("User open created Issue")
    public void userOpenCreatedIssue() {

        boardPage.getOpenIssueButtonLocator(issueName).click();

    }

    @Then("User see Window with created Issue")
    public void userSeeWindowWithCreatedIssue() {
        Assert.assertEquals(boardPage.getIssueOpenNameFieldLocator().getText(), issueName);
    }

    @And("User update Issue parameters")
    public void userUpdateIssueParameters() {
        Actions actions = new Actions(driver);
        actions
                .doubleClick(boardPage.getIssueOpenNameFieldLocator())
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(updName)
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

    }

    @Then("User see Window with updated Issue")
    public void userSeeWindowWithUpdatedIssue() {
        Assert.assertEquals(boardPage.getIssueOpenNameFieldLocator().getText(), updName);
    }

    @When("User delete created and updated Issue")
    public void userDeleteCreatedAndUpdatedIssue() {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(boardPage.getOpenIssueButtonLocator(updName))
                .build()
                .perform();
        boardPage.getIssueActionsButton(updName).click();
        boardPage.getActionDeleteIssueButton().click();
        boardPage.getConfirmDeleteIssueButton().click();

    }

    @Then("User see clean board")
    public void userCleanBoard() {
        Assert.assertEquals(boardPage.getColumnHeaderLocator("К выполнению").getText(), "К ВЫПОЛНЕНИЮ");
    }
}
