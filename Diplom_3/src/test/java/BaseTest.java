

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.WebDriverCreator;

public class BaseTest {
    protected WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = WebDriverCreator.createWebDriver();
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}