package baseEntities;

import io.cucumber.java.*;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.*;

import services.BrowsersService;
import services.DataBaseService;
import tables.ProjectsTable;

public class Hook extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;

    public Hook(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    @BeforeAll
    public static void beforeAllInitialize(){
        dataBaseService = new DataBaseService();
        projectsTable = new ProjectsTable(dataBaseService);
        smokeUIboardName = projectsTable.getProjectParameterById("projectName",1).toString();
        smokeUIboardKey = projectsTable.getProjectParameterById("projectKey",1).toString();
        crudUIboardName = projectsTable.getProjectParameterById("projectName",2).toString();
        crudUIBoardKey = projectsTable.getProjectParameterById("projectKey",2).toString();
        crudUIboardNameUPD = projectsTable.getProjectParameterById("projectName",6).toString();
        crudUIBoardKeyUPD = projectsTable.getProjectParameterById("projectKey",6).toString();
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
            Allure.getLifecycle().addAttachment(
                    "screenshot", "image/png", "png"
                    , ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

            baseCucumberTest.driver.quit();
        }

        if (baseCucumberTest.driver != null) {
            baseCucumberTest.driver.quit();
        }
    }

    @AfterAll
    public static void afterAll(){
        dataBaseService.closeConnection();

    }

}

