package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createWebDriver() {
        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equals(browser)) {
            return createYandexDriver();
        } else {
            return createChromeDriver();
        }
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program File/Mistress/home/newProject/Yandex/YandexBrowser/Application/browser.exe");
        return new ChromeDriver(options);
    }
}