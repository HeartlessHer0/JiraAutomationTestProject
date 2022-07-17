package services;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static baseEntities.AuthEntities.BROWSER;

public class BrowsersService {

    private WebDriver driver = null;
    private DriverManagerType driverManagerType = null;

    Logger logger = LoggerFactory.getLogger(BrowsersService.class);

    public BrowsersService() {
        switch (BROWSER) {
            case "chrome":
                driverManagerType = DriverManagerType.CHROME;
                WebDriverManager.getInstance(driverManagerType).setup();

                driver = new ChromeDriver(getChromeOptions());
                break;

            case "firefox":
                driverManagerType = DriverManagerType.FIREFOX;
                WebDriverManager.getInstance(driverManagerType).setup();

                driver = new FirefoxDriver(getFirefoxOptions());
                break;

            case "edge":
                driverManagerType = DriverManagerType.EDGE;
                WebDriverManager.getInstance(driverManagerType).setup();

                driver = new EdgeDriver(getEdgeOptions());
                break;

            default:
                logger.error("Browser " + System.getProperty("user_browser") + " is not support!");
        }
    }

    public WebDriver getDriver() {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));

        return this.driver;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--silent");
        //chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--window-size=1920,1200");

        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.addArguments("--disable-gpu");
//        firefoxOptions.addArguments("--ignore-certificate-errors");
//        firefoxOptions.addArguments("--silent");
//        firefoxOptions.addArguments("--start-maximized");
//        firefoxOptions.addArguments("--incognito");

        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.addArguments("--disable-gpu");
//        edgeOptions.addArguments("--ignore-certificate-errors");
//        edgeOptions.addArguments("--silent");
//        edgeOptions.addArguments("--start-maximized");
//        edgeOptions.addArguments("--incognito");

        return edgeOptions;
    }
}
