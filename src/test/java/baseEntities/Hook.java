package baseEntities;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.*;
import services.BrowsersService;

public class Hook extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;

    public Hook(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    @Before(value = "@UI")
    public void initializeTest(Scenario scenario) {
        baseCucumberTest.driver = new BrowsersService().getDriver();
        baseCucumberTest.jiraAllProjectsPage = new JiraAllProjectsPage(driver);
        baseCucumberTest.jiraSoftwareNavigationPage = new JiraSoftwareNavigationPage(driver);
        baseCucumberTest.jiraWorkPage = new JiraWorkPage(driver);
        baseCucumberTest.startPage = new StartPage(driver);
        baseCucumberTest.boardPage = new BoardPage(driver);
        baseCucumberTest.logoutPage = new LogoutPage(driver);
        baseCucumberTest.loginPage = new LoginPage(driver);
        baseCucumberTest.projectSettingPage = new ProjectSettingPage(driver);
        baseCucumberTest.trashPage = new TrashPage(driver);
        baseCucumberTest.profileSettingsPage = new ProfileSettingsPage(driver);
    }

    @After(value = "@UI")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.getLifecycle().addAttachment(                        //Screenshot for all Test
                    "screenshot", "image/png", "png"
                    , ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

            baseCucumberTest.driver.quit();
        }

        if (baseCucumberTest.driver != null) {
            baseCucumberTest.driver.quit();
        }
    }
}

