package patterns.pageobject;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class BrowserFactory {
private static WebDriver driver;
    public static void createDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                WebDriverRunner.setWebDriver(driver);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser type not supported: " + browserName);
        }
    }
    public static void tearDown() {
        driver.quit();
    }
    public static WebDriver getDriver() {
        return driver;
    }
}