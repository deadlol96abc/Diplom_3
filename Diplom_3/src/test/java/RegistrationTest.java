import utils.Constants;
import mainclasspage.HomePage;
import mainclasspage.LoginPage;
import mainclasspage.ProfilePage;
import mainclasspage.RegistrationPage;
import org.junit.Assert;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import static utils.RandomString.randomString;

public class RegistrationTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    ProfilePage profilePage;

    String name =  randomString(10);
    String email = randomString(7) + "@yandex.ru";
    String correctPassword = randomString(6);
    String wrongPassword = randomString(5);

    @Test
    @DisplayName("Успешная регистрация с корректным паролем")
    @Description("Проверка успешной регистрации пользователя с паролем длиной 6 символов")
    public void registerWithValidPassword() {
        homePage = new HomePage(webDriver);
        homePage.waitForProfileButton();
        homePage.clickProfileButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegisterLink();

        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.completeRegistration(name, email, correctPassword);

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();

        Assert.assertEquals("URL должен соответствовать странице входа", Constants.LOGIN_URL, webDriver.getCurrentUrl());

        loginPage.login(email, correctPassword);
        homePage.waitForProfileButton();
        homePage.clickProfileButton();
        profilePage = new ProfilePage(webDriver);
        profilePage.waitForPageLoad();

        Assert.assertEquals("Имя должно совпадать", name, profilePage.getName());
        Assert.assertEquals("Email должен совпадать", email, profilePage.getEmail());
    }

    @Test
    @DisplayName("Регистрация с коротким паролем")
    @Description("Проверка ошибки при регистрации с паролем менее 6 символов")
    public void registerWithShortPassword() {
        homePage = new HomePage(webDriver);
        homePage.waitForProfileButton();
        homePage.clickProfileButton();

        loginPage = new LoginPage(webDriver);
        loginPage.waitForPageLoad();
        loginPage.clickRegisterLink();

        registrationPage = new RegistrationPage(webDriver);
        registrationPage.waitForPageLoad();
        registrationPage.completeRegistration(name, email, wrongPassword);

        Assert.assertEquals("Должно отображаться сообщение об ошибке пароля", "Некорректный пароль", registrationPage.getPasswordErrorText());
        Assert.assertEquals("URL должен соответствовать странице регистрации", Constants.REGISTER_URL, webDriver.getCurrentUrl());
    }
}