package Common;

import browser.WebDriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static Logger logger = LogManager.getLogger();
    private WebDriverFactory webDriverFactory;
    private WebDriver webDriver;

    public Hooks(WebDriverFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
    }

    @Before(order = 1, value = "@web")
    public void startDriver(Scenario scenario) {
        logger.info("Starting scenario: " + scenario.getName());
        this.webDriver = webDriverFactory.getWebDriver();
        scenario.write(webDriverFactory.getBrowserName());
        scenario.write(webDriverFactory.getBrowserVersion());
        scenario.write(webDriverFactory.getPlatform());
        scenario.write(webDriverFactory.getBrowserViewport());
    }

    @After(order = 1, value = "@web")
    public void logResultsAndTakeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("Finished Scenario: " + scenario.getName() + " - STATUS: " + scenario.getStatus());
        } else {
            logger.info("Finished Scenario: " + scenario.getName() + " - STATUS: " + scenario.getStatus());
        }
    }
}
