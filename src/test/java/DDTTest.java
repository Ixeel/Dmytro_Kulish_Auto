import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import patterns.pageobject.BrowserFactory;
import patterns.pageobject.HomePage;

public class DDTTest {
private WebDriver driver;

    @BeforeMethod
    public void setup() {
        BrowserFactory.createDriver("chrome");
        driver = BrowserFactory.getDriver();
    }
    @AfterMethod
    public void teardown() {
        BrowserFactory.tearDown();
    }

    @Test(dataProviderClass = UserData.class, dataProvider = "users")
    public void checkLoginWithTwoUserData(String email, String password){
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        new HomePage()
                .clickSignInButton()
                .setEmail(email)
                .setPassword(password)
                .clickLoginButton();
        Assert.assertTrue(new HomePage().getAlertMessage().isDisplayed());
    }
}
