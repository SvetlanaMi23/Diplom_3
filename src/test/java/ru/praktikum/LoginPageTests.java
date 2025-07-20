package ru.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.model.RegisterUser;
import ru.praktikum.step.UserSteps;
import ru.praktikum.util.RestConfig;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

@DisplayName("Вход")
public class LoginPageTests extends BaseTest {
    private LoginPage loginPage;
    private RegisterUser user;
    private final UserSteps userSteps = new UserSteps();

    @Before
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(RestConfig.HOST)
                .setContentType(ContentType.JSON)
                .build();

        // Создаем пользователя
        user = new RegisterUser()
                .setEmail("test" + System.currentTimeMillis() + "@ya.ru")
                .setPassword("123456")
                .setName("Test User");
        userSteps.createUser(user).statusCode(HttpStatus.SC_OK);
    }

    @After
    public void clean() {
        try {
            String token = userSteps.loginAndGetToken(user);
            if (token != null && token.startsWith("Bearer")) {
                userSteps.deleteUser(token)
                        .statusCode(anyOf(is(HttpStatus.SC_OK), is(HttpStatus.SC_ACCEPTED), is(HttpStatus.SC_NO_CONTENT)));
            }
        } catch (Exception e) {
            System.out.println("Не удалось удалить пользователя: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт'")
    @Description("Проверка входа через кнопку Войти в аккаунт")
    public void loginFromMainPage() {
        driver.get(BASE_URL);
        loginPage.clickLoginToAccountButton();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через Личный кабинет")
    @Description("Проверка входа через Личный кабинет")
    public void loginFromProfile() {
        driver.get(BASE_URL);
        loginPage.clickPersonalCabinetButton();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход из формы регистрации")
    @Description("Проверка входа через форму регистрации")
    public void loginFromRegistrationForm() {
        driver.get(BASE_URL + "register");
        loginPage.clickLoginLink();
        loginFromUrl(driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход из формы восстановления пароля")
    @Description("Проверка входа через форму восстановления пароля")
    public void loginFromResetForm() {
        driver.get(BASE_URL + "forgot-password");
        loginPage.clickLoginLink();
        loginFromUrl(driver.getCurrentUrl());
    }

    //Проверка отображения кнопки заказа
    private void loginFromUrl(String url) {
        driver.get(url);
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(loginPage.isOrderButtonDisplayed());
    }
}
