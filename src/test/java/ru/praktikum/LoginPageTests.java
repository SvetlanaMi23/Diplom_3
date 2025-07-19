package ru.praktikum;

import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class LoginPageTests extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
    }

    private void loginFromUrl(String url) {
        driver.get(url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("validUser@yandex.ru", "123456");
        assertTrue(driver.findElement(By.xpath("//button[text()='Оформить заказ']")).isDisplayed());
    }

    @Test
    @Description("Вход через кнопку 'Войти в аккаунт'")
    public void loginFromMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.findElement(By.xpath("//button[text()='Войти в аккаунт']")).click();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @Description("Вход через Личный кабинет")
    public void loginFromProfile() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.findElement(By.xpath("//p[text()='Личный Кабинет']")).click();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @Description("Вход из формы регистрации")
    public void loginFromRegistrationForm() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        driver.findElement(By.linkText("Войти")).click();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @Description("Вход из формы восстановления пароля")
    public void loginFromResetForm() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        driver.findElement(By.linkText("Войти")).click();
        loginFromUrl(driver.getCurrentUrl());
    }
}
