package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTest {
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
    public void checkHeaderLogo(){
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        WebElement headerLogo = driver.findElement(By.className("header_logo"));
        boolean logo =  headerLogo.isDisplayed();
        if(logo){
            System.out.println("Logo displayed");
        }
        else {
            System.out.println("Logo does not displayed");
        }
    }

    @Test
    public void checkSignUpButtonColor(){
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        WebElement button = driver.findElement(By.xpath("//button[text()='Sign up']"));
        String color = button.getCssValue("background-color");
        String hex = Color.fromString(color).asHex();
        if(hex.equals("#0275d8")){
            System.out.println("Background color of Sign up button is correct");
        }
        else{
            System.out.println("Background color of Sign up button is incorrect");
        }
    }
}