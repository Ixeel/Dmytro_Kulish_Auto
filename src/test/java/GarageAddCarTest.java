import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import patterns.pageobject.GaragePage;
import patterns.pageobject.HomePage;

public class GarageAddCarTest {
    private WebDriver driver;
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkAddingCarToGarage() throws InterruptedException {
        new HomePage(driver)
                .open()
                .clickLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");

        new GaragePage(driver)
                .clickAddButton()
                .selectBrandAudi()
                .selectModelTT()
                .setMileage("20")
                .addButtonClick();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(new GaragePage(driver).carCardIsDisplayed());
        softAssert.assertTrue(new GaragePage(driver).getCarDate().contains("23.02.2025"));
        softAssert.assertEquals(new GaragePage(driver).getMileage(), "20");
        softAssert.assertTrue(new GaragePage(driver).imageIsDisplayed());
        softAssert.assertTrue(new GaragePage(driver).getImageSrc().endsWith("audi.png"));
        softAssert.assertAll();
    }
}


