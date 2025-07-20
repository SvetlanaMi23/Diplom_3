package ru.praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private final WebDriver driver;

    private final By bunsTab = By.xpath("//*[text()='Булки']");
    private final By saucesTab = By.xpath("//*[text()='Соусы']");
    private final By fillingsTab = By.xpath("//*[text()='Начинки']");
    private final By currentTab = By.cssSelector(".tab_tab_type_current__2BEPc");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по булкам")
    public void clickBuns() {
        driver.findElement(bunsTab).click();
    }

    @Step("Клик по соусам")
    public void clickSauces() {
        driver.findElement(saucesTab).click();
    }

    @Step("Клик по начинкам")
    public void clickFillings() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Получить текст текущей вкладки")
    public String getCurrentTabText() {
        return driver.findElement(currentTab).getText();
    }
}


