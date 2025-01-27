package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumTest {

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.chrome.driver", "C://chromedriver/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");

            WebElement element = driver.findElement(By.xpath("//button[contains(@class, 'header-link -guest')]"));
            element.click();

            element = driver.findElement(By.xpath("//a[contains(@routerlink, '/panel/garage')]"));
            System.out.println(element.getText());

            WebElement element2 = driver.findElement(By.xpath("//a[contains(@routerlink, '/panel/expenses')]"));
            System.out.println(element2.getText());

            WebElement element3 = driver.findElement(By.xpath("//a[contains(@routerlink, '/panel/instructions')]"));
            System.out.println(element3.getText());

            WebElement element4 = driver.findElement(By.id("userNavDropdown"));
            System.out.println(element4.getText());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}