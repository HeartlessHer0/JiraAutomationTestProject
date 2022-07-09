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
    private StartPage startPage;
    private JiraAllProjectsPage jiraAllProjectsPage;
    private JiraSoftwareNavigationPage jiraSoftwareNavigationPage;
    private JiraWorkPage jiraWorkPage;
    private BoardPage boardPage;
    private LogoutPage logoutPage;
    private LoginPage loginPage;
    private ProjectSettingPage projectSettingPage;


    public Hook(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;


    }


    @Before
    public void initializeTest(Scenario scenario) {
        baseCucumberTest.driver = new BrowsersService().getDriver();



    }

    @After
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

