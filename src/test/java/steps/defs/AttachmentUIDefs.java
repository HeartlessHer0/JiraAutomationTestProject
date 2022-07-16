package steps.defs;

import baseEntities.BaseCucumberTest;
import configuration.ReadProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import services.WaitsService;

import java.time.Duration;

public class AttachmentUIDefs extends BaseCucumberTest {
    private WaitsService waitsService = new WaitsService(driver, Duration.ofSeconds(ReadProperties.timeout()));
    private String testComment = "Test Comment";
    private static final Logger logger = LogManager.getLogger(AttachmentUIDefs.class);

    @When("User add Test Image to Issue")
    public void userAddTestImageToIssue() throws InterruptedException {
        String path = AttachmentUIDefs.class.getClassLoader().getResource("testimage.jpg").getPath().replaceFirst("/", "");

        boardPage.getOpenIssueButtonLocator("ImageAndCommentTest").click();
        boardPage.getFileUploadingLocator().sendKeys(path);
        logger.info("Test image added to Test Issue");
        Thread.sleep(3000);
    }

    @And("User create issue")
    public void userCreateIssue() {
        boardPage.getCreateIssueButton("К выполнению").click();
        boardPage.getIssueNameFieldLocator().sendKeys("ImageAndCommentTest");
        boardPage.getIssueNameFieldLocator().sendKeys(Keys.ENTER);
        logger.info("Test issue created");
        boardPage.getCurrentBoardButton().click();
        logger.info("Page reloaded");
    }

    @Then("User see added Image in Issue")
    public void userSeeAddedImageInIssue() {
        Assert.assertTrue(boardPage.getImageLocator().isDisplayed());
        logger.info("Test image is on the page");
    }

    @When("User open test Issue")
    public void userOpenTestIssue() {
        boardPage.getOpenIssueButtonLocator("ImageAndCommentTest").click();
        logger.info("Test Issue is open");
    }

    @And("User delete Test Image from Issue")
    public void userDeleteTestImageFromIssue() {
        boardPage.getImageActionsLocator().click();
        boardPage.getImageDeleteButton().click();
        boardPage.getConfirmDeleteImageButton().click();
        logger.info("Test Image deleted from the Test Issue");
    }

    @Then("User see Issue without Test Image")
    public void userSeeIssueWithoutTestImage() {
        Assert.assertTrue(waitsService.waitForInvisibilityElement(boardPage.getImageInvisLocator()));
        logger.info("Test Image is not on the Issue");
    }

    @And("User add comment")
    public void userAddComment() {
        boardPage.getIssueCommentFieldLocator().click();
        boardPage.getActivatedCommentFieldLocator().sendKeys(testComment);
        boardPage.getCommentOrDescriptionSaveButtonLocator().click();
        logger.info("Comment added to the Issue");
    }

    @Then("User see added comment in Issue")
    public void userSeeAddedCommentInIssue() {
        Assert.assertTrue(boardPage.getCommentLocator(testComment).isDisplayed());
        logger.info("Comment is on the page");
    }

    @And("User delete Test Comment")
    public void userDeleteTestComment() {
        boardPage.getImageDeleteButton().click();
        boardPage.getConfirmDeleteImageButton().click();
        logger.info("Comment deleted from Issue");
    }

    @Then("User see Issue without Test Comment")
    public void userSeeIssueWithoutTestComment() {
        Assert.assertTrue(waitsService.waitForInvisibilityElement(By.xpath(boardPage.getCommentInvLocator(testComment))));
        logger.info("Comment is not on the Issue");
    }
}
