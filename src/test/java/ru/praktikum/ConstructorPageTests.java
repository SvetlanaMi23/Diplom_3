package ru.praktikum;
import io.qameta.allure.*;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class ConstructorPageTests extends BaseTest {
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        super.setUp();
        driver.get("https://stellarburgers.nomoreparties.site/");
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @Description("Переход к вкладке 'Соусы'")
    public void testSaucesTab() {
        constructorPage.clickSauces();
        assertEquals("Соусы", constructorPage.getCurrentTabText());
    }

    @Test
    @Description("Переход к вкладке 'Начинки'")
    public void testFillingsTab() {
        constructorPage.clickFillings();
        assertEquals("Начинки", constructorPage.getCurrentTabText());
    }

    @Test
    @Description("Переход к вкладке 'Булки'")
    public void testBunsTab() {
        constructorPage.clickSauces(); // сначала в другую вкладку
        constructorPage.clickBuns();
        assertEquals("Булки", constructorPage.getCurrentTabText());
    }
}

