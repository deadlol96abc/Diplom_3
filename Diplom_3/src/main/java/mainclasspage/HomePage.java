package mainclasspage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver webDriver;


    private final By loginButton = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");
    private final By orderButton = By.xpath(".//*[text()='Оформить заказ']");
    private final By profileButton = By.xpath(".//*[text()='Личный Кабинет']");
    private final By bunsSection = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesSection = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath("//span[text()='Начинки']/parent::div");
    private final By activeSection = By.cssSelector(".tab_tab__1SPyG.tab_tab_type_current__2BEPc");

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Нажимаем на кнопку: Войти в аккаунт")
    public void clickLoginButton() {
        webDriver.findElement(loginButton).click();
    }

    @Step("ННажимаем на кнопку: Личный Кабинет")
    public void clickProfileButton() {
        webDriver.findElement(profileButton).click();
    }

    @Step("Выбираем - Булки")
    public void selectBunsSection() {
        webDriver.findElement(bunsSection).click();
    }

    @Step("Выбираем - Соусы")
    public void selectSaucesSection() {
        webDriver.findElement(saucesSection).click();
    }

    @Step("Выбираем - Начинки")
    public void selectFillingsSection() {
        webDriver.findElement(fillingsSection).click();
    }

    @Step("Булки")
    public String getBunsSectionClass() {
        return webDriver.findElement(bunsSection).getAttribute("class");
    }

    @Step("Соусы")
    public String getSaucesSectionClass() {
        return webDriver.findElement(saucesSection).getAttribute("class");
    }

    @Step("Начинки")
    public String getFillingsSectionClass() {
        return webDriver.findElement(fillingsSection).getAttribute("class");
    }

    @Step("Активность раздела Булки")
    public boolean isBunsSectionActive() {
        return getBunsSectionClass().contains("tab_tab_type_current__2BEPc");
    }

    @Step("Активность раздела Соусы")
    public boolean isSaucesSectionActive() {
        return getSaucesSectionClass().contains("tab_tab_type_current__2BEPc");
    }

    @Step("Активность раздела Начинки")
    public boolean isFillingsSectionActive() {
        return getFillingsSectionClass().contains("tab_tab_type_current__2BEPc");
    }

    @Step("Ждём кнопку: Войти в аккаунт")
    public void waitForLoginButton() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Ждём кнопку: Личный Кабинет")
    public void waitForProfileButton() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    @Step("Ждём кнопку: Оформить заказ")
    public void waitForOrderButton() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }
}