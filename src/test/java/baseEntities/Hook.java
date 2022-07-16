package baseEntities;

import io.cucumber.java.*;

import io.qameta.allure.Allure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.*;

import services.BrowsersService;
import services.DataBaseService;
import tables.ProjectsTable;

import java.sql.SQLException;
import java.util.ArrayList;

public class Hook extends BaseCucumberTest {
    private BaseCucumberTest baseCucumberTest;

    public Hook(BaseCucumberTest baseCucumberTest) {
        this.baseCucumberTest = baseCucumberTest;
    }

    private static final Logger logger = LogManager.getLogger(Hook.class);

    @BeforeAll
    public static void beforeAllInitialize() {
        dataBaseService = new DataBaseService();
        projectsTable = new ProjectsTable(dataBaseService);

        logger.info("The connection to the database is open");

        boardNames = new ArrayList<String>();
        boardKeys = new ArrayList<String>();

        try {
            rs = projectsTable.getProjects();
            while (rs.next()) {
                boardNames.add(rs.getString(2));
                boardKeys.add(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.info("Values from the database are imported into the Test Framework");
    }

    @Before(value = "@UI")
    public void initializeUITest(Scenario scenario) {
        baseCucumberTest.driver = new BrowsersService().getDriver();

        logger.info("WebDriver initialized");

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

        logger.info("All pages initialized");
    }

    @After(value = "@UI")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.info("Scenario failed");

            Allure.getLifecycle().addAttachment(
                    "screenshot", "image/png", "png",
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
            );

            logger.info("Screenshot taken");

            baseCucumberTest.driver.quit();
        }

        if (baseCucumberTest.driver != null) {
            logger.info("Scenario passed");

            baseCucumberTest.driver.quit();
        }
    }

    @AfterAll
    public static void afterAll() {
        logger.info("All tests passed");

        dataBaseService.closeConnection();
    }
}
