package mainclasspage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    WebDriver webDriver;

    public ProfilePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Локаторы
    private final By welcomeMessage = By.xpath(".//*[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By nameField = By.xpath(".//li[1]//input");
    private final By emailField = By.xpath(".//li[2]//input");
    private final By constructorButton = By.xpath(".//*[text()='Конструктор']");
    private final By logo = By.xpath(".//nav/div[@class='AppHeader_header__logo__2D0X2']");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");

    @Step("Данные профмиля")
    public String getEmail() {
        return webDriver.findElement(emailField).getAttribute("value");
    }

    @Step("Данные профиля")
    public String getName() {
        return webDriver.findElement(nameField).getAttribute("value");
    }

    @Step("Нажимаем на Конструктор")
    public void clickConstructorButton() {
        webDriver.findElement(constructorButton).click();
    }

    @Step("Нажимаем на логотип")
    public void clickLogo() {
        webDriver.findElement(logo).click();
    }

    @Step("Нажимаем на кнопку: Вхюав")
    public void clickLogoutButton() {
        webDriver.findElement(logoutButton).click();
    }

    @Step("Ждём страницу профиля")
    public void waitForPageLoad() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
    }
}