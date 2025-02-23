import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import patterns.pageobject.BrowserFactory;
import patterns.pageobject.GaragePage;
import patterns.pageobject.HomePage;

public class GarageAddCarTest {
    private WebDriver driver;
    @BeforeMethod
    public void setup() {
     BrowserFactory.createDriver("chrome");
     driver = BrowserFactory.getDriver();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkAddingCarToGarage() throws InterruptedException {
        new HomePage()
                .open()
                .clickLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");

        new GaragePage()
                .clickAddButton()
                .selectBrandAudi()
                .selectModelTT()
                .setMileage("20")
                .addButtonClick();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(new GaragePage().carCardIsDisplayed());
        softAssert.assertTrue(new GaragePage().getCarDate().contains("23.02.2025"));
        softAssert.assertEquals(new GaragePage().getMileage(), "20");
        softAssert.assertTrue(new GaragePage().imageIsDisplayed());
        softAssert.assertTrue(new GaragePage().getImageSrc().endsWith("audi.png"));
        softAssert.assertAll();
    }
}


