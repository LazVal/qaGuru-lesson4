package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setupSelenideEnv() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://simplewine.ru";
        Configuration.browser = "chrome";
    }

    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }
}
