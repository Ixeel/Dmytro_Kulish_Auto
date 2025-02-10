import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class iFrameWindowsHandleTest {
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
    public void checkIframe() {
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        WebElement frame = driver.findElement(By.cssSelector(".hero-video_frame"));
        driver.switchTo().frame(frame);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String iframeTitle = (String) js.executeScript("return document.title;");
        Assert.assertEquals(iframeTitle, "Як потрапити у майбутнє? Трансформація навчання. - YouTube");
        driver.switchTo().defaultContent();
    }

    @Test
    public void checkSocialsInFooter() {
        driver.get("https://guest:welcome2qauto@qauto.forstudy.space/");
        List<WebElement> links = driver.findElements(By.cssSelector("a.socials_link"));
        Assert.assertEquals(links.size(), 5, "Social network block doesn’t contain 5 items ");
        String url;
        for (WebElement link : links) {
            String pageUrl = link.getDomAttribute("href");
            link.click();
            Set<String> handles = driver.getWindowHandles();
            List<String> listHandles = new ArrayList<>(handles);
            Assert.assertEquals(listHandles.size(), 2, "New tab did not open after clicking");
            driver.switchTo().window(listHandles.get(1));
            url = driver.getCurrentUrl();
            if(pageUrl.equals("https://www.youtube.com/user/HillelITSchool?sub_confirmation=1")){
                Assert.assertTrue(url.contains("https://www.youtube.com/user/HillelITSchool"), "Incorrect url of Social network");
            }
            else if (pageUrl.equals("https://www.linkedin.com/school/ithillel/")) {
                Assert.assertTrue(url.contains("linkedin.com/school/ithillel") || url.contains("linkedin.com/authwall"),"Incorrect url of Social network");
            }
            else {
                Assert.assertEquals(url, pageUrl, "Incorrect url of Social network");
            }
            driver.close();
            driver.switchTo().window(listHandles.get(0));
        }
    }
}
