package pages.qa_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class QaTestPage extends BasePage {

    public QaTestPage(WebDriver driver) {
        super(driver);
    }

    public void setLogin(String str) {
        clearAndType(driver.findElement(loginEmail), str);
    }

    public void setPassword(String str) {
        clearAndType(driver.findElement(loginPassword), str);
    }

    public void clickAuthButton() {
        driver.findElement(authButton).click();
    }

    public void checkEmailFormatError() {
        waitElementIsVisible(driver.findElement(emailFormatError));
    }

    public void checkInvalidEmailPassword() {
        waitElementIsVisible(driver.findElement(invalidEmailPassword));
    }

    public void setDataEmail(String str) {
        clearAndType(driver.findElement(dataEmail), str);
    }

    public void setDataName(String str) {
        clearAndType(driver.findElement(dataName), str);
    }

    public void checkBlankNameError() {
        waitElementIsVisible(driver.findElement(blankNameError));
    }

    public void checkDataEmail(String email) {
        waitElementIsVisible(checkTableEmail(email));
    }

    public void checkDataName(String name) {
        waitElementIsVisible(checkTableName(name));
    }

    public void checkGender(String gender) {
        waitElementIsVisible(checkTableGender(gender));
    }

    public void checkChoice1(String choice) {
        waitElementIsVisible(checkTableChoice1(choice));
    }

    public void checkChoice2(String choice) {
        waitElementIsVisible(checkTableChoice2(choice));
    }

    public void setGender(String gender) {
        driver.findElement(dataGender).click();
        switch (gender) {
            case "Мужской":
                driver.findElement(optionGenderMale).click();
                break;
            case "Женский":
                driver.findElement(optionGenderFemale).click();
                break;
        }
    }

    public void setChoice2(String choice) {
        switch (choice) {
            case "2.1":
                driver.findElement(dataSelect21).click();
                break;
            case "2.2":
                driver.findElement(dataSelect22).click();
                break;
            case "2.3":
                driver.findElement(dataSelect23).click();
                break;
        }
    }

    public void setChoice1(String choice, boolean enabled) {
        switch (choice) {
            case "1.1":
                setCheckBox(driver.findElement(dataCheck11), enabled);
                break;
            case "1.2":
                setCheckBox(driver.findElement(dataCheck12), enabled);
                break;
        }
    }

    public void dataSend() {
        driver.findElement(dataSend).click();
    }

    public void submitData() {
        driver.findElement(submitData).click();
    }

    private final By loginEmail = By.id("loginEmail");
    private final By loginPassword = By.id("loginPassword");
    private final By authButton = By.id("authButton");
    private final By emailFormatError = By.id("emailFormatError");
    private final By invalidEmailPassword = By.id("invalidEmailPassword");

    private final By dataEmail = By.id("dataEmail");
    private final By dataName = By.id("dataName");
    private final By blankNameError = By.id("blankNameError");

    private final By dataGender = By.id("dataGender");
    private final By optionGenderMale = By.xpath("//select[@id='dataGender']/option[1]");
    private final By optionGenderFemale = By.xpath("//select[@id='dataGender']/option[2]");

    private final By dataCheck11 = By.id("dataCheck11");
    private final By dataCheck12 = By.id("dataCheck12");

    private final By dataSelect21 = By.id("dataSelect21");
    private final By dataSelect22 = By.id("dataSelect22");
    private final By dataSelect23 = By.id("dataSelect23");

    private final By dataSend = By.id("dataSend");
    private final By submitData = By.xpath("//button[@class='uk-button uk-button-primary uk-modal-close']");

    private WebElement checkTableEmail(String email) {
        return driver.findElement(By.xpath(String.format("//table[@id='dataTable']//td[1][text()='%s']", email)));
    }

    private WebElement checkTableName(String name) {
        return driver.findElement(By.xpath(String.format("//table[@id='dataTable']//td[2][text()='%s']", name)));
    }

    private WebElement checkTableGender(String gender) {
        return driver.findElement(By.xpath(String.format("//table[@id='dataTable']//td[3][text()='%s']", gender)));
    }

    private WebElement checkTableChoice1(String choice) {
        return driver.findElement(By.xpath(String.format("//table[@id='dataTable']//td[4][text()='%s']", choice)));
    }

    private WebElement checkTableChoice2(String choice) {
        return driver.findElement(By.xpath(String.format("//table[@id='dataTable']//td[5][text()='%s']", choice)));
    }
}