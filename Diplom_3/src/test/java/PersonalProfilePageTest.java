import io.restassured.response.Response;
import user.User;
import user.UserClient;
import utils.Constants;
import mainclasspage.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import static utils.RandomString.randomString;

public class PersonalProfilePageTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    ProfilePage profilePage;
    UserClient userClient = new UserClient();

    String email, password, name, accessToken;

    @Before
    public void setUp() {
        super.setUp();
        homePage = new HomePage(webDriver);
        loginPage = new LoginPage(webDriver);
        profilePage = new ProfilePage(webDriver);


        name = randomString(7);
        email = randomString(6) + "@yandex.ru";
        password = randomString(7);
        User user = new User(email, password, name);

        Response response = userClient.createUser(user);
        accessToken = response.body().path("accessToken").toString().substring(7);

        homePage.clickLoginButton();
        loginPage.login(email, password);
        homePage.waitForOrderButton();
    }

    @Test
    @DisplayName("Переход в личный кабинет через кнопку профиля")
    @Description("Проверка перехода в личный кабинет и отображения данных пользователя")
    public void openProfileViaProfileButton() {
        homePage.clickProfileButton();
        profilePage.waitForPageLoad();

        Assert.assertEquals("Неверный URL профиля", Constants.PROFILE_URL, webDriver.getCurrentUrl());
        Assert.assertEquals("Неверное имя", name, profilePage.getName());
        Assert.assertEquals("Неверный email", email, profilePage.getEmail());
    }

    @Test
    @DisplayName("Переход к конструктору через кнопку")
    @Description("Проверка перехода из личного кабинета в конструктор через кнопку Конструктор")
    public void navigateToConstructorFromProfile() {
        homePage.clickProfileButton();
        profilePage.clickConstructorButton();

        Assert.assertEquals("Неверный URL главной страницы", Constants.HOME_URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход к конструктору через логотип")
    @Description("Проверка перехода из личного кабинета в конструктор через клик по логотипу")
    public void navigateToConstructorViaLogo() {
        homePage.clickProfileButton();
        profilePage.clickLogo();

        Assert.assertEquals("Неверный URL главной страницы", Constants.HOME_URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода из аккаунта и очистки полей ввода")
    public void logoutFromProfile() {
        homePage.clickProfileButton();
        profilePage.clickLogoutButton();
        loginPage.waitForPageLoad();

        Assert.assertEquals("Неверный URL входа", Constants.LOGIN_URL, webDriver.getCurrentUrl());
        Assert.assertEquals("Email не очищен", "", loginPage.getEmailValue());
        Assert.assertEquals("Пароль не очищен", "", loginPage.getPasswordValue());
    }

    @After
    public void cleanUp() {
        userClient.deleteUser(accessToken);
    }
}