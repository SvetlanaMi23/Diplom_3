package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@DisplayName("Раздел Конструктор")
public class ConstructorPageTests extends BaseTest {
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        super.setUp();
        driver.get("https://stellarburgers.nomoreparties.site/");
        constructorPage = new ConstructorPage(driver);
    }

    @Test
    @DisplayName("Переход к вкладке 'Соусы'")
    public void testSaucesTab() {
        constructorPage.clickSauces();
        assertEquals("Соусы", constructorPage.getCurrentTabText());
    }

    @Test
    @DisplayName("Переход к вкладке 'Начинки'")
    public void testFillingsTab() {
        constructorPage.clickFillings();
        assertEquals("Начинки", constructorPage.getCurrentTabText());
    }

    @Test
    @DisplayName("Переход к вкладке 'Булки'")
    public void testBunsTab() {
        constructorPage.clickSauces(); // сначала в другую вкладку
        constructorPage.clickBuns();
        assertEquals("Булки", constructorPage.getCurrentTabText());
    }
}

