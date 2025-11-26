package mainclasspage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPage {
    WebDriver webDriver;

    public RestorePasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    private final By pageTitle = By.xpath(".//*[text()='Восстановление пароля']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    @Step("Ждём страницу восстановления пароля")
    public void waitForPageLoad() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    }

    @Step("Нажимаем кнопку: Войти")
    public void clickLoginLink() {
        webDriver.findElement(loginLink).click();
    }
}