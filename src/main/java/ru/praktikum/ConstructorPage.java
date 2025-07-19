package ru.praktikum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private WebDriver driver;

    private By bunsTab = By.xpath("//*[text()='Булки']");
    private By saucesTab = By.xpath("//*[text()='Соусы']");
    private By fillingsTab = By.xpath("//*[text()='Начинки']");
    private By currentTab = By.cssSelector(".tab_tab_type_current");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickBuns() {
        driver.findElement(bunsTab).click();
    }

    public void clickSauces() {
        driver.findElement(saucesTab).click();
    }

    public void clickFillings() {
        driver.findElement(fillingsTab).click();
    }

    public String getCurrentTabText() {
        return driver.findElement(currentTab).getText();
    }
}


