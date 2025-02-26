import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import patterns.pageobject.BrowserFactory;

public class FilesTest {
    private WebDriver driver;
    @BeforeMethod
    public void setup() {
        BrowserFactory.createDriver("chrome");
        driver = BrowserFactory.getDriver();
    }

    @AfterMethod
    public void afterMethod() {
        BrowserFactory.getDriver().quit();
    }
}
