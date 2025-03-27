import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import patterns.pageobject.BrowserFactory;
import patterns.pageobject.GaragePage;
import patterns.pageobject.HomePage;

public class AllureReportTest {
    private WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    @Step("Launch the browser")
    public void setup(String browser) {
        BrowserFactory.createDriver(browser);
        driver = BrowserFactory.getDriver();
    }

    @AfterMethod
    @Step("Close the browser")
    public void tearDown() {
        BrowserFactory.getDriver().quit();
    }

    @Test
    @Owner("Dmytro")
    @Description("CheckAddingCarToGarage")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "story", url = "https://example.com/", type = "Jira")
    public void checkAddingCarToGarageWithAllureReport() {
        SoftAssert softAssert = new SoftAssert();
        new HomePage()
                .open()
                .clickGuestLogIn()
                .verifyCorrectPageIsOpened();

        new GaragePage()
                .clickAddCarButton()
                .selectBrandAudi()
                .selectModelTT()
                .setMileage("20")
                .addButtonClick()
                .verifyCarCardIsDisplayed(softAssert)
                .verifyCarDate(softAssert, "27.03.2025")
                .verifyMileage(softAssert, "20")
                .verifyImageIsDisplayed(softAssert)
                .verifyCarImage(softAssert, "audi.png");

        softAssert.assertAll();
    }
}
