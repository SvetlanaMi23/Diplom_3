package ru.praktikum;
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


