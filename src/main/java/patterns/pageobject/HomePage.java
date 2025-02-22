package patterns.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By GuestLoginButton = By.xpath("//button[text()=\"Guest log in\"]");

    public HomePage (WebDriver driver){
        this.driver = driver;
    }
    public HomePage clickLoginButton(){
        driver.findElement(GuestLoginButton).click();
        return this;
    }
    public HomePage open(){
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        return this;
    }

}
