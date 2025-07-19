package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private By nameInput = By.name("name");
    private By emailInput = By.name("email");
    private By passwordInput =By.name("Пароль");
    private By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By passwordError = By.className("input__error");
    private By loginButton = By.className("Auth_link__1fOlj");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(String name, String email, String password) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }

    public void clickLoginLink() {
        driver.findElement(loginButton).click();
    }
}

