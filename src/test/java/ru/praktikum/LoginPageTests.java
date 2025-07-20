package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@DisplayName("Вход")
public class LoginPageTests extends BaseTest {
    private LoginPage loginPage;

    @Before
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
    }

    private void loginFromUrl(String url) {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegisterPage registerPage = new RegisterPage(driver);
        String email = "test" + System.currentTimeMillis() + "@ya.ru";
        registerPage.fillForm("Test User", email, "123456");
        registerPage.clickRegister();
        driver.get(url);
        loginPage.login(email, "123456");
        assertTrue(loginPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт'")
    public void loginFromMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        loginPage.clickLoginToAccountButton();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    public void loginFromProfile() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        loginPage.clickPersonalCabinetButton();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход из формы регистрации")
    public void loginFromRegistrationForm() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        loginPage.clickLoginLink();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход из формы восстановления пароля")
    public void loginFromResetForm() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        loginPage.clickLoginLink();
        loginFromUrl(driver.getCurrentUrl());
    }
}
