import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import patterns.pageobject.BrowserFactory;
import patterns.pageobject.HomePage;

public class LoginTest {
    private WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        BrowserFactory.createDriver(browser);
        driver = BrowserFactory.getDriver();
    }
    @AfterMethod
    public void teardown() {
        BrowserFactory.tearDown();
    }

    @Test
    public void checkLoginUser(){
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        new HomePage()
                .clickSignInButton()
                .setEmail("test@hillel.ua")
                .setPassword("1111")
                .clickLoginButton();
        Assert.assertTrue(new HomePage().getAlertMessage().isDisplayed());
    }

    @Test(groups = {"ui"})
    public void LoginSuccess() {
        System.out.println("Login success");
    }
}