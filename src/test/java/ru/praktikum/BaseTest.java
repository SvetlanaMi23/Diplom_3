package ru.praktikum;

import io.qameta.allure.internal.shadowed.jackson.databind.MapperFeature;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public abstract class BaseTest {

    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final Config CONFIG = readConfiguration();

    protected WebDriver driver;

    public void setUp() {
        switch (CONFIG.getBrowser()) {
            case YANDEX:
                // Создаем драйвер для браузера Yandex
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                break;
            case CHROME:
                // Создаем драйвер для браузера Chrome
                break;
        }
        String[] arguments = new String[]{"--no-sandbox", "--headless", "--window-size=1200,800", "--disable-dev-shm-usage"};
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(arguments);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //Неявное ожидание
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static Config readConfiguration() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
            return mapper.readValue(new File("src/test/resources/config.json"), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Config().setBrowser(Browser.CHROME);
        }
    }

    static class Config {
        Browser browser;

        public Browser getBrowser() {
            return browser;
        }

        public Config setBrowser(Browser browser) {
            this.browser = browser;
            return this;
        }
    }

    enum Browser {
        YANDEX,
        CHROME
    }
}

