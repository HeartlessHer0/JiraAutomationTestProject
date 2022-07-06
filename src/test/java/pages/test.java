package pages;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import services.BrowsersService;

public class test  {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @Test
    public void unitLoginTest() throws InterruptedException {
        driver = new BrowsersService().getDriver();

       loginPage = new LoginPage(driver);

        driver.get("https://id.atlassian.com/login");

        loginPage.getEmailInput().sendKeys("jedai38@gmail.com");
        loginPage.getSubmitButton().click();
        loginPage.getPswInput().sendKeys("MyPass4RegTrello2022");
        loginPage.getSubmitButton().click();
        Thread.sleep(4000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
