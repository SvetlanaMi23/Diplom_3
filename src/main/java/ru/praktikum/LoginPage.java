package ru.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.name("Пароль");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By personalCabinetButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By loginToAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By orderButton = By.xpath("//button[contains(@class, 'button_button__33qZ0') and text()='Оформить заказ']");
    private final By loginLink = By.linkText("Войти");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Зарегистрироваться")
    public void login(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие кнопки Войти в аккаунт")
    public void clickLoginToAccountButton() {
        driver.findElement(loginToAccountButton).click();
    }

    @Step("Нажатие кнопки Личный кабинет")
    public void clickPersonalCabinetButton() {
        driver.findElement(personalCabinetButton).click();
    }

    @Step("Нажатие кнопки Войти")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Видна ли кнопка Оформить заказ")
    public boolean isOrderButtonDisplayed() {
        return driver.findElement(orderButton).isDisplayed();
    }
}
