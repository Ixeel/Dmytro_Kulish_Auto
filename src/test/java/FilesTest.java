import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import patterns.pageobject.GaragePage;
import patterns.pageobject.HomePage;
import patterns.pageobject.InstructionsPage;

import java.io.IOException;
import java.nio.file.Files;

public class FilesTest {
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
    public void checkFileDownloading() {
        new HomePage()
                .open()
                .clickLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");
        new GaragePage()
                .clickInstructionsButton();
        new InstructionsPage()
                .clickDownloadButton();
        Assert.assertTrue(Files.exists(new InstructionsPage().getPDFFilePath()));
    }

    @Test
    public void checkWritingAvailableCarsInFile() throws IOException {
        new HomePage()
                .open()
                .clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://guest:welcome2qauto@qauto.forstudy.space/panel/garage");
        new GaragePage()
                .clickInstructionsButton();
        try {
            new InstructionsPage()
                    .clickButtonCarOption()
                    .GetElementsAndWriteInFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(new InstructionsPage().readFile());
    }
}

