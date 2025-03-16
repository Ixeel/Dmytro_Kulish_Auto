import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import patterns.pageobject.BrowserFactory;
import patterns.pageobject.selenide.GaragePage;
import patterns.pageobject.selenide.HomePage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class SelenideTest {
    @BeforeMethod
    public void setup() {
        BrowserFactory.createDriver("chrome");
    }
    @AfterMethod
    public void teardown() {
        BrowserFactory.tearDown();
    }

    @Test
    public void checkAddingCarToGarage() {
        new HomePage()
                .open()
                .clickLoginButton();
        webdriver().shouldHave(url("https://guest:welcome2qauto@qauto.forstudy.space/panel/garage"));
        new GaragePage()
                .clickAddCarButton()
                .selectBrandAudi()
                .selectModelTT()
                .setMileage("20")
                .addButtonClick()
                .getCarCard().shouldBe(visible);
        new GaragePage().getCarDate().shouldHave(text("16.03.2025"));
        Assert.assertEquals(new GaragePage().getMileage(), "20");
        new GaragePage().getImage().shouldBe(visible).shouldHave(attributeMatching("src", ".*audi\\.png$"));
    }
}