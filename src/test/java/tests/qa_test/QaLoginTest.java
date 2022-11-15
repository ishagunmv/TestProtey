package tests.qa_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.base.BaseTest;

public class QaLoginTest extends BaseTest {

    @BeforeEach
    void beforeEach() {
        basePage.goToQaTestHtml();
    }

    @DisplayName("Проверка login поля - невалидные форматы")
    @ParameterizedTest
    @ValueSource(strings = {
            "~!#$%^&*()_+-=.,",
            "123тест",
            ":;{}[]><~`'",
            "@",
            "login@",
            "@mail.ru",
            "qwerty",
            ""
    })
    void errorLoginFormatTest(String login) {
        qaTestPage.setLogin(login);
        qaTestPage.clickAuthButton();
        qaTestPage.checkEmailFormatError();
    }

    @DisplayName("Проверка неверный email/password")
    @ParameterizedTest
    @CsvSource({
            "12345@login.ruuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu,test",
            "test@protei.ru,",
            "test@protei.ru,ABC_abc123",
            "test@protei.ru,!@#$%^&*()_+=",
            "абсфв!#$%^&*()@mail.,"
    })
    void invalidEmailPasswordTest(String login, String password) {
        qaTestPage.setLogin(login);
        qaTestPage.setPassword(password);
        qaTestPage.clickAuthButton();
        qaTestPage.checkInvalidEmailPassword();
    }
}
