package patterns.pageobject;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
private static WebDriver driver;
    public static void createDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.default_directory", "C:\\Users\\User\\IdeaProjects\\untitled10\\target");
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
//                driver = new ChromeDriver(options);
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver.manage().window().maximize();
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), firefoxOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver.manage().window().maximize();
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
