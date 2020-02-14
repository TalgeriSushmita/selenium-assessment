package browser;

import enums.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static enums.Browsers.CHROME;

public class WebDriverFactory {

    protected static Logger logger = LogManager.getLogger();
    private WebDriver webDriver;
    private Browsers browser;
    private String browserName;
    private String browserVersion;
    private String platform;
    private String browserViewport;
    private WebDriverWait wait;

    private URL getBaseURL() {
        URL baseURL = null;

        try {
            String server = "172.17.243.227";
            String port = "4444";
            baseURL = new URL("http://" + server + ":" + port + "/wd/hub/");
        } catch (MalformedURLException e) {
            logger.debug("Could not get Selenium URL - " + e.getMessage());
        }
        return baseURL;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setProxy(null);
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("start-maximized");
        return chromeOptions;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-private");
        return firefoxOptions;
    }

    private SafariOptions getSafariOptions() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability("safari.cleanSession", true);
        return safariOptions;
    }

    public WebDriver getDriver() {
        try {
            String browserAsString = System.getProperty("browser");
            browser = Browsers.valueOf(browserAsString);
            logger.info("Trying to create an instance of " + browser.name());

            switch (browser) {
                case CHROME:
                default:
                    webDriver = new RemoteWebDriver(getBaseURL(), getChromeOptions());
                    break;

                case SAFARI:
                    webDriver = new RemoteWebDriver(getBaseURL(), getSafariOptions());
                    break;

                case FIREFOX:
                    webDriver = new RemoteWebDriver(getBaseURL(), getFirefoxOptions());
            }
        } catch (SessionNotCreatedException e) {
            logger.debug("Could not create an instance of " + browser.name());
        }
        printDetails(webDriver);
        if (platform.equalsIgnoreCase("MAC") && (browser == CHROME)) {
            maximizeScreen();
        } else {
            webDriver.manage().window().maximize();
        }
        webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, 25);
        return webDriver;
    }

    private void printDetails(WebDriver webDriver) {
        Capabilities capabilities = ((RemoteWebDriver) webDriver).getCapabilities();
        browserName = capabilities.getBrowserName();
        browserVersion = capabilities.getVersion();
        logger.info("Browser: " + browserName);
        logger.info("Version: " + browserVersion);
        platform = capabilities.getPlatform().toString();
    }

    private void maximizeScreen() {
        Point position = new Point(0, 0);
        webDriver.manage().window().setPosition(position);
    }

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            webDriver = getDriver();
        }
        return webDriver;
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public String getBrowserViewport() {
        return browserViewport;
    }

}

