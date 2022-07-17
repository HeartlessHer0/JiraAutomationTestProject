package steps.defs;

import baseEntities.BaseCucumberTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import static baseEntities.AuthEntities.LOGIN;
import static baseEntities.AuthEntities.PASSWORD;

public class LoginUIDefs extends BaseCucumberTest {

    public BaseCucumberTest baseCucumberTest;

    private static final Logger logger = LogManager.getLogger(LoginUIDefs.class);

    @Given("Atlassian login page is open")
    public void atlassianLoginPageIsOpen() {
        baseCucumberTest.driver.get("https://id.atlassian.com/login");

        logger.info("Atlassian Login Page is opened");
    }

    @When("User enter correct email and password")
    public void userEnterCorrectEmailAndPassword() {
        loginPage.getEmailInput().sendKeys(LOGIN);
        loginPage.getSubmitButton().click();

        logger.info("Correct email is entered");

        loginPage.getPswInput().sendKeys(PASSWORD);
        loginPage.getSubmitButton().click();

        logger.info("Correct password is entered");
    }

    @Then("User come to the Atlassian Start Page")
    public void userComeToTheAtlassianStartPage() {
        Assert.assertTrue(startPage.isPageOpened());

        logger.info("Start Page is opened");
    }

    @When("User enter correct email and incorrect password")
    public void userEnterCorrectEmailAndIncorrectPassword() {
        loginPage.getEmailInput().sendKeys(LOGIN);
        loginPage.getSubmitButton().click();

        logger.info("Correct email is entered");

        loginPage.getPswInput().sendKeys("12345567");
        loginPage.getSubmitButton().click();

        logger.info("Incorrect password is entered");
    }

    @Then("User see error text area")
    public void userSeeErrorTextArea() {
        Assert.assertTrue(loginPage.getIncorrectPasswordOrEmailElement().isDisplayed());

        logger.info("Login error message is displayed");
    }

    @When("User enter correct email and password is empty")
    public void userEnterCorrectEmailAndPasswordIsEmpty() {
        loginPage.getEmailInput().sendKeys(LOGIN);
        loginPage.getSubmitButton().click();

        logger.info("Correct email is entered");

        loginPage.getPswInput().click();
        loginPage.getSubmitButton().click();

        logger.info("Password is empty");
    }

    @Then("User see password error message")
    public void userSeePasswordErrorMessage() {
        Assert.assertTrue(loginPage.getPasswordIsNullElement().isDisplayed());

        logger.info("Password error message is displayed");
    }

    @When("User enter unregistered email")
    public void userEnterUnregisteredEmail() {
        loginPage.getEmailInput().sendKeys("some@mail");
        loginPage.getSubmitButton().click();

        logger.info("Unregistered email is entered");
    }

    @Then("User see Registration button")
    public void userSeeRegistrationButton() {
        Assert.assertTrue(loginPage.getSignupSubmitButton().isDisplayed());

        logger.info("Registration button is displayed");
    }

    @When("User dont enter email")
    public void userDontEnterEmail() {
        loginPage.getSubmitButton().click();

        logger.info("Email is empty");
    }

    @Then("User see Email error message")
    public void userSeeEmailErrorMessage() {
        Assert.assertTrue(loginPage.getEmailIsNull().isDisplayed());

        logger.info("Email error message is displayed");
    }
}
