package steps.defs;

import baseEntities.BaseCucumberTest;
import configuration.ReadProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import services.WaitsService;

import java.time.Duration;

public class AttachmentUIDefs extends BaseCucumberTest {
   private WaitsService waitsService = new WaitsService(driver, Duration.ofSeconds(ReadProperties.timeout()));
   private String testComment = "Test Comment";

    @When("User add Test Image to Issue")
    public void userAddTestImageToIssue() throws InterruptedException {
        String path= AttachmentUIDefs.class.getClassLoader().getResource("testimage.jpg").getPath().replaceFirst("/", "");

        boardPage.getOpenIssueButtonLocator("ImageAndCommentTest").click();
        boardPage.getFileUploadingLocator().sendKeys(path);
        Thread.sleep(3000);
    }

    @And("User create issue")
    public void userCreateIssue() {
        boardPage.getCreateIssueButton("К выполнению").click();
        boardPage.getIssueNameFieldLocator().sendKeys("ImageAndCommentTest");
        boardPage.getIssueNameFieldLocator().sendKeys(Keys.ENTER);
        boardPage.getCurrentBoardButton().click();
    }

    @Then("User see added Image in Issue")
    public void userSeeAddedImageInIssue() {
        Assert.assertTrue(boardPage.getImageLocator().isDisplayed());
    }

    @When("User open test Issue")
    public void userOpenTestIssue() {
        boardPage.getOpenIssueButtonLocator("ImageAndCommentTest").click();
    }

    @And("User delete Test Image from Issue")
    public void userDeleteTestImageFromIssue() {
        boardPage.getImageActionsLocator().click();
        boardPage.getImageDeleteButton().click();
        boardPage.getConfirmDeleteImageButton().click();
    }

    @Then("User see Issue without Test Image")
    public void userSeeIssueWithoutTestImage() {
       Assert.assertTrue(waitsService.waitForInvisibilityElement(boardPage.getImageInvisLocator()));
    }

    @And("User add comment")
    public void userAddComment() {
        boardPage.getIssueCommentFieldLocator().click();
        boardPage.getActivatedCommentFieldLocator().sendKeys(testComment);
        boardPage.getCommentOrDescriptionSaveButtonLocator().click();
    }

    @Then("User see added comment in Issue")
    public void userSeeAddedCommentInIssue() {
        Assert.assertTrue(boardPage.getCommentLocator(testComment).isDisplayed());
    }

    @And("User delete Test Comment")
    public void userDeleteTestComment() {
        boardPage.getImageDeleteButton().click();
        boardPage.getConfirmDeleteImageButton().click();
    }

    @Then("User see Issue without Test Comment")
    public void userSeeIssueWithoutTestComment() {
        Assert.assertTrue(waitsService.waitForInvisibilityElement(By.xpath(boardPage.getCommentInvLocator(testComment))));
    }
}
