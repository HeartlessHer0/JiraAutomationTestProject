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
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import services.WaitsService;

import java.time.Duration;

public class AttachmentUIDefs extends BaseCucumberTest {

    private static final Logger logger = LogManager.getLogger(AttachmentUIDefs.class);

    private WaitsService waitsService = new WaitsService(driver, Duration.ofSeconds(ReadProperties.timeout()));

    private String testComment = "Test Comment";

    @When("User add Test Image to Issue")
    public void userAddTestImageToIssue() throws InterruptedException {
        String path = AttachmentUIDefs.class.getClassLoader().getResource("testimage.jpg").getPath().replaceFirst("/", "");

        boardPage.getOpenIssueButtonLocator("ImageAndCommentTest").click();
        boardPage.getFileUploadingLocator().sendKeys(path);
        Thread.sleep(3000);

        logger.info("Test image added to Test Issue");
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
        Actions actions = new Actions(driver);
        actions
                .moveToElement(boardPage.getOpenIssueButtonLocator("ImageAndCommentTest"))
                .click(boardPage.getOpenIssueButtonLocator("ImageAndCommentTest"))
                .build()
                .perform();

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

        logger.info("Comment deleted from the Issue");
    }

    @Then("User see Issue without Test Comment")
    public void userSeeIssueWithoutTestComment() {
        Assert.assertTrue(waitsService.waitForInvisibilityElement(By.xpath(boardPage.getCommentInvLocator(testComment))));

        logger.info("Comment is not on the Issue");
    }

    @When("User delete trash issue")
    public void userDeleteTrashIssue() {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(boardPage.getOpenIssueButtonLocator("ImageAndCommentTest"))
                .build()
                .perform();

        boardPage.getIssueActionsButton("ImageAndCommentTest").click();
        boardPage.getActionDeleteIssueButton().click();
        boardPage.getConfirmDeleteIssueButton().click();

        logger.info("Trash Issue is deleted");
    }

    @Then("Test board is clean")
    public void testBoardIsClean() {
        Assert.assertTrue(waitsService.waitForInvisibilityElement(By.xpath(boardPage.getOpenIssueButtonInvLocator("ImageAndCommentTest"))));

        logger.info("Trash Issue is not displayed");
    }
}
