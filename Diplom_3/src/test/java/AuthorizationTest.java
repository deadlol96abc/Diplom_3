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
import io.restassured.response.Response;
import static utils.RandomString.randomString;

public class AuthorizationTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    ProfilePage profilePage;
    RegistrationPage registrationPage;
    RestorePasswordPage restorePasswordPage;

    String email;
    String password;
    String name;
    String accessToken;
    UserClient userClient = new UserClient();
    User user;

    @Before
    public void createUser(){
        name = randomString(7);
        email = randomString(6) + "@yandex.ru";
        password = randomString(7);
        user = new User(email, password, name);
        Response createUser = userClient.createUser(user);
        accessToken = createUser.body().path("accessToken").toString().substring(7);
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    @Description("Проверка успешного входа в систему через кнопку 'Войти в аккаунт'")
    public void loginViaHomePageLoginButton() {

        homePage = new HomePage(webDriver);
        homePage.waitForLoginButton();
        homePage.clickLoginButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.login(email, password);

        homePage.waitForOrderButton();

        Assert.assertEquals(Constants.HOME_URL, webDriver.getCurrentUrl());

        homePage.clickProfileButton();

        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();

        Assert.assertEquals(email, profilePage.getEmail());
    }

    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    @Description("Входим ")
    public void loginViaProfileButton() {
        homePage = new HomePage(webDriver);
        homePage.waitForProfileButton();
        homePage.clickProfileButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.login(email, password);

        homePage.waitForOrderButton();
        homePage.clickProfileButton();

        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();

        Assert.assertEquals(email, profilePage.getEmail());
    }

    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    @Description("Входим через кнопку Войти, что в форме регистрации")
    public void loginViaRegistrationFormLink() {

        homePage = new HomePage(webDriver);
        homePage.waitForLoginButton();
        homePage.clickLoginButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegisterLink();

        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.clickLoginLink();

        loginPage.waitForPageLoad();
        loginPage.login(email, password);

        homePage.waitForOrderButton();
        homePage.clickProfileButton();

        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();

        Assert.assertEquals(email, profilePage.getEmail());
    }

    @Test
    @DisplayName("Входчерез ссылку в форме восстановления пароля")
    @Description("Входим через кнопку Войти, что в форме восстановления пароля")
    public void loginViaRestorePasswordFormLink() {
        homePage = new HomePage(webDriver);
        homePage.waitForLoginButton();
        homePage.clickLoginButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRestorePasswordLink();

        restorePasswordPage = new RestorePasswordPage(webDriver);
        restorePasswordPage.waitForPageLoad();
        restorePasswordPage.clickLoginLink();

        loginPage.waitForPageLoad();
        loginPage.login(email, password);

        homePage.waitForOrderButton();
        homePage.clickProfileButton();

        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();

        Assert.assertEquals(email, profilePage.getEmail());
    }

    @After
    public void cleanUp(){
        userClient.deleteUser(accessToken);
    }
}