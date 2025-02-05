import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AssertTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void checkIfLogoIsDisplayed() {
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        WebElement logo = driver.findElement(By.className("header_logo"));
        boolean ifLogoIsDisplayed = logo.isDisplayed();
        Assert.assertTrue(ifLogoIsDisplayed, "Logo does not displayed");
    }

    @Test
    public void checkBackgroundColor() {
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        WebElement button = driver.findElement(By.xpath("//button[text()=\"Sign up\"]"));
        String color = button.getCssValue("background-color");
        String hex = Color.fromString(color).asHex();
        Assert.assertEquals(hex, "#0275d8", "Background color of Sign up button is incorrect");
    }
}
