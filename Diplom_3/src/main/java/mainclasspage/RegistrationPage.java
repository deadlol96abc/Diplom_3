package mainclasspage;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    WebDriver webDriver;

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    private final By pageTitle = By.xpath(".//*[text()='Регистрация']");
    private final By nameField = By.xpath(".//fieldset[1]//input");
    private final By emailField = By.xpath(".//fieldset[2]//input");
    private final By passwordField = By.xpath(".//fieldset[3]//input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath(".//fieldset[3]//p");
    private final By loginLink = By.xpath(".//*[text()='Уже зарегистрированы?']/a");



    @Step("Ожидаем страницы регистрации")
    public void waitForPageLoad() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    }

    @Step("Вводим данные пользователя для регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        webDriver.findElement(nameField).sendKeys(name);
        webDriver.findElement(emailField).sendKeys(email);
        webDriver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажимаем на кнопку: Зарегестрироваться")
    public void clickRegisterButton() {
        webDriver.findElement(registerButton).click();
    }

    @Step("Общий метод регистрации")
    public void completeRegistration(String name, String email, String password) {
        fillRegistrationForm(name, email, password);
        clickRegisterButton();
    }

    @Step("Ошибка пароля")
    public String getPasswordErrorText() {
        return webDriver.findElement(passwordError).getText();
    }



    @Step("Нажимаем на кнопку: Войти")
    public void clickLoginLink() {
        webDriver.findElement(loginLink).click();
    }
}