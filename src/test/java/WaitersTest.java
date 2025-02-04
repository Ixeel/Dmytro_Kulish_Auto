import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class WaitersTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void checkTitleOfPage()  {
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
        Assert.assertEquals(driver.getTitle(), "Hillel Qauto");

    }
    @Test
    public void checkButtonToBeClickable()  {
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsByClassName('header-link -guest')[0].click();");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Add car\"]")));
    }
}
