package common;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static common.Config.BROWSER;
import static constants.Constants.TimeoutVariables.IMPLICIT_WAIT;

public class CommonActions {

    public WebDriver driver;
    public static CommonActions instance = null;

    public CommonActions() {
        switch (BROWSER) {
            case "chrome" :
                if (System.getProperty("os.name").contains("Windows")) {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                }
                driver = new ChromeDriver();
                break;
            case "firefox" :
                if (System.getProperty("os.name").contains("Windows")) {
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/geckodriver");
                }
                driver = new FirefoxDriver();
                break;
            default:
                Assertions.fail("INCORRECT BROWSER NAME " + BROWSER);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
//        driver.manage().window().maximize();
    }

    public static CommonActions getInstance() {
        if (instance == null)
            instance = new CommonActions();
        return instance;
    }
}