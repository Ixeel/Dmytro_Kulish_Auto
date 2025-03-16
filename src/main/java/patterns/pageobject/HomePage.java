package patterns.pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;
    private By GuestLoginButton = By.xpath("//button[text()=\"Guest log in\"]");

    public HomePage () {
        this.driver = BrowserFactory.getDriver();
    }
    public HomePage clickLoginButton() {
        driver.findElement(GuestLoginButton).click();
        return this;
    }
    public HomePage open() {
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        return this;
    }
    @Step("Check that panel/garage page is opened")
    public HomePage verifyCorrectPageIsOpened() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");
        return this;
    }
}
