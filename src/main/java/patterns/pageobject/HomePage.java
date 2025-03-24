package patterns.pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By GuestLoginButton = By.xpath("//button[text()=\"Guest log in\"]");
    private By LoginButton = By.xpath("//button[text()=\"Login\"]");
    private By SignInButton = By.xpath("//button[text()=\"Sign In\"]");
    private By EmailField = By.id("signinEmail");
    private By PasswordField = By.id("signinPassword");
    private By AlertMessage = By.xpath("//p[text()=\"Wrong email or password\"]");

    public HomePage () {
        this.driver = BrowserFactory.getDriver();
    }
    public HomePage clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(LoginButton)).click();
        return this;
    }
    public HomePage clickSignInButton() {
        driver.findElement(SignInButton).click();
        return this;
    }
    public HomePage open() {
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        return this;
    }
    public WebElement getAlertMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AlertMessage));
    }

    public HomePage setEmail(String email) {
        driver.findElement(EmailField).sendKeys(email);
        return this;
    }
    public HomePage setPassword(String password) {
        driver.findElement(PasswordField).sendKeys(password);
        return this;
    }

    @Step("Check that panel/garage page is opened")
    public HomePage verifyCorrectPageIsOpened() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");
        return this;
    }
}
