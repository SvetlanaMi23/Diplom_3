package ru.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final By nameInput = By.name("name");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.name("Пароль");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath("//p[contains(@class, 'input__error') and text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение формы регистрации")
    public void fillForm(String name, String email, String password) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие кнопки Зарегистрироваться")
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    @Step("Ожидание страницы логина")
    public void waitLoginPage() {
        new WebDriverWait(driver, Duration.ofMillis(1000)).until(ExpectedConditions.urlContains("login"));
    }

    @Step("Получение ошибки невалидного пароля")
    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }
}

