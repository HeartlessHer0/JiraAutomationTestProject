package baseEntities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import services.BrowsersService;

public class Hook extends BaseCucumberTest {
//    private BaseCucumberTest baseCucumberTest;
//
//    public Hook(BaseCucumberTest baseCucumberTest) {
//        this.baseCucumberTest = baseCucumberTest;
//    }
//
//    @Before
//    public void initializeTest(Scenario scenario) {
//        System.out.println("Hook start browser.... ");
//        baseCucumberTest.driver = new BrowsersService().getDriver();
//    }
//
//    @After
//    public void tearDown(Scenario scenario) {
//        if (scenario.isFailed()) {
//            System.out.println("Make a screenshot");
//        }
//        if (baseCucumberTest.driver != null) {
//            baseCucumberTest.driver.quit();
//        }
//    }
}
