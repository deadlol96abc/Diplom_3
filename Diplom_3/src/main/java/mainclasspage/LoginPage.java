package mainclasspage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Локаторы
    private final By pageTitle = By.xpath(".//*[text()='Вход']");
    private final By emailField = By.xpath(".//fieldset[1]//input");
    private final By passwordField = By.xpath(".//fieldset[2]//input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.className("Auth_link__1fOlj");
    private final By restorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    @Step("Вводим почту")
    public void enterEmail(String email) {
        webDriver.findElement(emailField).sendKeys(email);
    }

    @Step("Вводим пароль")
    public void enterPassword(String password) {
        webDriver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем на кнопку: Войти")
    public void clickLoginButton() {
        webDriver.findElement(loginButton).click();
    }

    @Step("Общий метод для логина")
    public void fillLoginData(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Общий метод для входа")
    public void login(String email, String password) {
        fillLoginData(email, password);
        clickLoginButton();
    }

    @Step("Нажимаем на кнопку: Зарегистрироваться")
    public void clickRegisterLink() {
        webDriver.findElement(registerLink).click();
    }

    @Step("Нажимаем на кнопку: Восстановить пароль")
    public void clickRestorePasswordLink() {
        webDriver.findElement(restorePasswordLink).click();
    }

    @Step("Получить значение почты")
    public String getEmailValue() {
        return webDriver.findElement(emailField).getAttribute("value");
    }

    @Step("Получаем пароль")
    public String getPasswordValue() {
        return webDriver.findElement(passwordField).getAttribute("value");
    }

    @Step("Ждём загрузки страницы входа")
    public void waitForPageLoad() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    }
}