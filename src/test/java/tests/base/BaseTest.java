package tests.base;

import common.CommonActions;
import common.Config;
import common.Listener;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.base.BasePage;
import pages.qa_test.QaTestPage;

import java.io.File;
import java.time.LocalTime;
import java.util.Objects;

@ExtendWith(Listener.class)
public class BaseTest {

    protected CommonActions commonActions = CommonActions.getInstance();
    protected WebDriver driver = commonActions.driver;
    protected BasePage basePage = new BasePage(driver);
    protected QaTestPage qaTestPage = new QaTestPage(driver);
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    static {
        LOGGER.info("START TIME - " + LocalTime.now());
        LOGGER.info("Clear reports directory build/reports/ ...");
        File allureResults = new File("allure-results");
        if (allureResults.isDirectory()) {
            for (File item : Objects.requireNonNull(allureResults.listFiles())) {
                item.delete();
            }
        }
        if(Config.CLEAR_REPORTS_DIR) {
            File reports = new File("build/reports/tests/");
            if (reports.isDirectory()) {
                for (File item : Objects.requireNonNull(reports.listFiles())) {
                    item.delete();
                }
            }
            File downloads = new File("build/downloads/");
            if (downloads.isDirectory()) {
                for (File item : Objects.requireNonNull(reports.listFiles())) {
                    item.delete();
                }
            }
        }
    }
}