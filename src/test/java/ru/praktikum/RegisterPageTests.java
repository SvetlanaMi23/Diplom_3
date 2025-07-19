package ru.praktikum;

import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegisterPageTests extends BaseTest {

        private RegisterPage registerPage;

        @Before
        public void setUp() {
            super.setUp();
            driver.get("https://stellarburgers.nomoreparties.site/register");
            registerPage = new RegisterPage(driver);
        }

        @Test
        @Description("Успешная регистрация с валидным паролем")
        public void testSuccessfulRegistration() {
            String email = "test" + System.currentTimeMillis() + "@ya.ru";
            registerPage.fillForm("Test User", email, "123456");
            registerPage.clickRegister();
            new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.urlContains("login"));
            assertTrue(driver.getCurrentUrl().contains("/login"));
        }

        @Test
        @Description("Ошибка при регистрации с коротким паролем")
        public void testPasswordTooShort() {
            registerPage.fillForm("Short Pass", "short@ya.ru", "12345");
            registerPage.clickRegister();
            assertTrue(registerPage.getPasswordErrorText().contains("Некорректный пароль"));
        }
}
