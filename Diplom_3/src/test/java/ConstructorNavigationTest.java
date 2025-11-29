import mainclasspage.HomePage;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import static org.hamcrest.CoreMatchers.not;

public class ConstructorNavigationTest extends BaseTest {

    HomePage homePage;

    @Test
    @DisplayName("Переход к Булкам")
    @Description("Проверка перехода и активации раздела Булки в конструкторе")
    public void navigateToBunsSection() {
        homePage = new HomePage(webDriver);
        homePage.waitForLoginButton();
        homePage.selectSaucesSection();

        Assert.assertFalse("Раздел 'Булки' не должен быть активен", homePage.isBunsSectionActive());

        homePage.selectBunsSection();

        Assert.assertTrue("Раздел 'Булки' должен быть активен", homePage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Переход Соусам")
    @Description("Проверка перехода и активации раздела Соусы в конструкторе")
    public void navigateToSaucesSection() {
        homePage = new HomePage(webDriver);
        homePage.waitForLoginButton();

        Assert.assertFalse("Раздел 'Соусы' не должен быть активен", homePage.isSaucesSectionActive());

        homePage.selectSaucesSection();

        Assert.assertTrue("Раздел 'Соусы' должен быть активен", homePage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход к Начинке")
    @Description("Проверка перехода и активации раздела Начинки в конструкторе")
    public void navigateToFillingsSection() {
        homePage = new HomePage(webDriver);
        homePage.waitForLoginButton();

        Assert.assertFalse("Раздел 'Начинки' не должен быть активен", homePage.isFillingsSectionActive());

        homePage.selectFillingsSection();

        Assert.assertTrue("Раздел 'Начинки' должен быть активен", homePage.isFillingsSectionActive());
    }
}