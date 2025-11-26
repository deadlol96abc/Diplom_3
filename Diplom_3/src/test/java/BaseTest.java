import utils.Constants;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import static utils.WebDriverCreator.createWebDriver;

public class BaseTest  {
    WebDriver webDriver;
    @Before
    public void setUp(){
        webDriver = createWebDriver();
        webDriver.get(Constants.HOME_URL);
    }
    @After
    public void tearDown(){
        webDriver.quit();
    }
}