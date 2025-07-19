package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private By nameInput = By.name("name");
    private By emailInput =  By.xpath("//label[text()='Email']/following-sibling::input");
    private By passwordInput =By.name("Пароль");
    private By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By passwordError = By.xpath("//p[contains(@class, 'input__error') and text()='Некорректный пароль']");
    private By loginButton = By.cssSelector("Auth_link__1fOlj");

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

