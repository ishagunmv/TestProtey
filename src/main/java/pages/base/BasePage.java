package pages.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

import static constants.Constants.TimeoutVariables.EXPLICIT_WAIT;
import static constants.Constants.URL.QA_TEST_HTML;

public class BasePage {

    protected final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    public WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void goToQaTestHtml() {
        driver.get(getCurrentPath());
    }

    private String getCurrentPath() {
        try {
            return new java.io.File(QA_TEST_HTML).getCanonicalPath();
        } catch (IOException e) {
            LOGGER.info("Failed getCurrentPath() - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    protected void waitElementIsVisible(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOf(element));
    }

    protected void setCheckBox(WebElement element, boolean enabled) {
        if (enabled) {
            if (!element.isSelected()) element.click();
            } else {
            if (element.isSelected()) element.click();
        }
    }

    protected void clearAndType(WebElement element, String value) {
        element.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        if (value != null) {
            element.sendKeys(value);
        }
    }
}