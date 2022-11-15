package tests.qa_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.base.BaseTest;

import static constants.Constants.TestDataAccount.EMAIL;
import static constants.Constants.TestDataAccount.PASSWORD;

public class QaFormTest extends BaseTest {

    @BeforeEach
    void beforeEach() {
        basePage.goToQaTestHtml();
        qaTestPage.setLogin(EMAIL);
        qaTestPage.setPassword(PASSWORD);
        qaTestPage.clickAuthButton();
    }

    @DisplayName("Проверка email поля - невалидные форматы")
    @ParameterizedTest
    @ValueSource(strings = {
            "~!#$%^&*()_+-=.,/?|",
            "123тест",
            "AbcАбс",
            ":;{}[]><~`'",
            "@",
            "login@",
            "@mail.ru",
            "qwerty",
            "login @ mail.ru",
            "login@login",
            "русcкий@mail.ru",
            " ",
            ""
    })
    void errorLoginFormatTest(String email) {
        qaTestPage.setDataEmail(email);
        qaTestPage.dataSend();
        qaTestPage.checkEmailFormatError();
    }

    @DisplayName("Проверка имени - невалидные данные")
    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            ")(*(&*^%$/|?<>",
            "123"
    })
    void invalidNameTest(String name) {
        qaTestPage.setDataEmail(EMAIL);
        qaTestPage.setDataName(name);
        qaTestPage.dataSend();
        qaTestPage.checkBlankNameError();
    }

    @DisplayName("Проверка имени - валидные данные")
    @ParameterizedTest
    @ValueSource(strings = {
            "name",
            "тест",
            "Имяяяя Фамилия Отчество",
            "First.Name Last Na-me OK"
    })
    void checkNameTest(String pass) {
        qaTestPage.setDataEmail(EMAIL);
        qaTestPage.setDataName(pass);
        qaTestPage.dataSend();
        qaTestPage.submitData();
        qaTestPage.checkDataEmail(EMAIL);
        qaTestPage.checkDataName(pass);
    }

    @DisplayName("Проверка гендера")
    @ParameterizedTest
    @ValueSource(strings = {"Мужской", "Женский"})
    void checkGender(String gender) {
        qaTestPage.setDataEmail(EMAIL);
        qaTestPage.setDataName("test");
        qaTestPage.setGender(gender);
        qaTestPage.dataSend();
        qaTestPage.submitData();
        qaTestPage.checkGender(gender);
    }

    @DisplayName("Проверка выбор 1")
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "false|false|Нет",
            "true|false|1.1",
            "false|true|1.2",
            "true|true|1.1, 1.2"
    })
    void checkChoice1(boolean check11, boolean check12, String table) {
        qaTestPage.setDataEmail(EMAIL);
        qaTestPage.setDataName("test");
        qaTestPage.setChoice1("1.1", check11);
        qaTestPage.setChoice1("1.2", check12);
        qaTestPage.dataSend();
        qaTestPage.submitData();
        qaTestPage.checkChoice1(table);
    }

    @DisplayName("Проверка выбор 2")
    @ParameterizedTest
    @ValueSource(strings = {"2.1", "2.2", "2.3"})
    void checkChoice2(String choice) {
        qaTestPage.setDataEmail(EMAIL);
        qaTestPage.setDataName("test");
        qaTestPage.setChoice2(choice);
        qaTestPage.dataSend();
        qaTestPage.submitData();
        qaTestPage.checkChoice2(choice);
    }

    @DisplayName("Проверка записи целиком")
    @ParameterizedTest
    @CsvSource("email@email,test,Женский,1.1,2.1")
    void checkTableRow(String email, String name, String gender, String choice1, String choice2) {
        qaTestPage.setDataEmail(email);
        qaTestPage.setDataName(name);
        qaTestPage.setGender(gender);
        qaTestPage.setChoice1(choice1, true);
        qaTestPage.setChoice2(choice2);
        qaTestPage.dataSend();
        qaTestPage.submitData();
        qaTestPage.checkDataEmail(email);
        qaTestPage.checkDataName(name);
        qaTestPage.checkGender(gender);
        qaTestPage.checkChoice1(choice1);
        qaTestPage.checkChoice2(choice2);
    }

    @DisplayName("Проверка двух записей")
    @ParameterizedTest
    @CsvSource("email@email,test,admin@admin,mikhail")
    void checkTableUsers(String email0, String name0, String email1, String name1) {
        qaTestPage.setDataEmail(email0);
        qaTestPage.setDataName(name0);
        qaTestPage.dataSend();
        qaTestPage.submitData();
        qaTestPage.setDataEmail(email1);
        qaTestPage.setDataName(name1);
        qaTestPage.dataSend();
        qaTestPage.submitData();
        qaTestPage.checkDataEmail(email0);
        qaTestPage.checkDataName(name0);
        qaTestPage.checkDataEmail(email1);
        qaTestPage.checkDataName(name1);
    }
}