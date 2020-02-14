package pages.actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import exceptions.LoggedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public abstract class BasicPage {

    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger();
    private WebDriver webDriver;
    private JavascriptExecutor javascriptExecutor;
    private URL url;

    public boolean isDisplayed() {
        return waitForPageToLoad() && load() && isUrlCorrect() && isTitlePresent();
    }

    protected boolean waitForPageToLoad() {
        ExpectedCondition<Boolean> pageLoaded = webDriver -> javascriptExecutor.executeScript("return document.readyState")
                .toString().equals("complete");
        try {
            return wait.until(pageLoaded);
        } catch (Exception e) {
            logger.debug("Could not verify that the page loaded - " + e.getMessage());
            return false;
        }
    }

    public boolean isTitlePresent() {
        return !webDriver.getTitle().isEmpty();
    }

    protected boolean isUrlCorrect() {
        try {
            String shortenedUrl = url.getHost() + url.getPath();
            wait.until(urlContains(shortenedUrl));
            logger.info("Shortened URL: " + shortenedUrl);
            logger.info("Full URL: " + webDriver.getCurrentUrl());
            return true;
        } catch (Exception e) {
            logger.debug("Incorrect URL - " + e.getMessage());
            return false;
        }
    }

    public String getURL() {
        return webDriver.getCurrentUrl();
    }

    public boolean load() {
        webDriver.get(url.toString());
        return isDisplayed();
    }

    protected WebElement waitFor(WebElement webElement) {
        try {
            webElement = wait.ignoring(ElementNotVisibleException.class).ignoring(ElementClickInterceptedException.class).ignoring(ElementNotInteractableException.class).
                    ignoring(ElementNotSelectableException.class).ignoring(ElementNotFoundException.class).ignoring(StaleElementReferenceException.class).ignoring(ElementNotFoundException.class).
                    until(elementToBeClickable(webElement));
        } catch (Exception e) {
            throw new LoggedException("Element not returned - " + e.getMessage());
        }
        return webElement;
    }

}
