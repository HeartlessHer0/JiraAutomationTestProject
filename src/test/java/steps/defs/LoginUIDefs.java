package steps.defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginUIDefs extends BaseCucumberTest {
    BaseCucumberTest baseCucumberTest;
    
    @Given("Atlassian login page is open")
    public void atlassianLoginPageIsOpen() {
        baseCucumberTest.driver.get("https://id.atlassian.com/login");
    }

    @When("User enter correct email and password")
    public void userEnterCorrectEmailAndPassword() {
        loginPage.getEmailInput().sendKeys("jedai38@gmail.com");
        loginPage.getSubmitButton().click();
        loginPage.getPswInput().sendKeys("MyPass4RegTrello2022");
        loginPage.getSubmitButton().click();
    }

    @Then("User come to the Atlassian Start Page")
    public void userComeToTheAtlassianStartPage() {
        Assert.assertTrue(startPage.isPageOpened());
    }

    @When("User enter correct email and incorrect password")
    public void userEnterCorrectEmailAndIncorrectPassword() {
        loginPage.getEmailInput().sendKeys("jedai38@gmail.com");
        loginPage.getSubmitButton().click();
        loginPage.getPswInput().sendKeys("12345567");
        loginPage.getSubmitButton().click();
    }

    @Then("User see error text area")
    public void userSeeErrorTextArea() {
        Assert.assertTrue(loginPage.getIncorrectPasswordOrEmailElement().isDisplayed());
    }

    @When("User enter correct email and password is null")
    public void userEnterCorrectEmailAndPasswordIsNull() {
        loginPage.getEmailInput().sendKeys("jedai38@gmail.com");
        loginPage.getSubmitButton().click();
        loginPage.getPswInput().click();
        loginPage.getSubmitButton().click();
    }

    @Then("User see password error message")
    public void userSeePasswordErrorMessage() {
        Assert.assertTrue(loginPage.getPasswordIsNullElement().isDisplayed());
    }

    @When("User enter unregistered email")
    public void userEnterUnregisteredEmail() {
        loginPage.getEmailInput().sendKeys("some@mail");
        loginPage.getSubmitButton().click();
    }

    @Then("User see Registration button")
    public void userSeeRegistrationButton() {
        Assert.assertTrue(loginPage.getSignupSubmitButton().isDisplayed());
    }

    @When("User dont enter email")
    public void userDontEnterEmail() {
        loginPage.getSubmitButton().click();
    }

    @Then("User see Email error message")
    public void userSeeEmailErrorMessage() {
        Assert.assertTrue(loginPage.getEmailIsNull().isDisplayed());
    }
}
