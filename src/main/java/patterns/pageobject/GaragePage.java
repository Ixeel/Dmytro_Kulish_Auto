package patterns.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class GaragePage {
    private WebDriver driver;
    private By BrandSelect = By.id("addCarBrand");
    private By ModelSelect = By.id("addCarModel");
    private By AddCarButton = By.xpath("//button[text()=\"Add car\"]");
    private By AddButton = By.xpath("//button[text()=\"Add\"]");
    private By MileageField = By.xpath("//input[@id=\"addCarMileage\"]");
    private By GarageCarCard = By.xpath("//p[text()=\"Audi TT\"]");
    private By Date = By.cssSelector("p.car_update-mileage");
    private By Mileage = By.xpath("//input[@name='miles']");
    private By Image = By.cssSelector("img.car-logo_img");
    private By InstructionsButton = By.cssSelector("span.icon.icon-instructions");

    public GaragePage() {
        this.driver = BrowserFactory.getDriver();
    }

    public GaragePage clickAddCarButton() {
        driver.findElement(AddCarButton).click();
        return this;
    }

    public GaragePage selectBrandAudi() {
        WebElement brandDropdown = driver.findElement(BrandSelect);
        Select select = new Select(brandDropdown);
        select.selectByVisibleText("Audi");
        return this;
    }

    public GaragePage selectModelTT() {
        WebElement modelDropdown = driver.findElement(ModelSelect);
        Select select = new Select(modelDropdown);
        select.selectByVisibleText("TT");
        return this;
    }

    public GaragePage setMileage(String mileage) {
        driver.findElement(MileageField).sendKeys(mileage);
        return this;

    }

    public GaragePage addButtonClick() {
        driver.findElement(AddButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        return this;
    }

    public boolean carCardIsDisplayed() {
        return driver.findElement(GarageCarCard).isDisplayed();
    }

    public String getCarDate() {
        return driver.findElement(Date).getText();
    }

    public String getMileage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value;", driver.findElement(Mileage));
    }

    public boolean imageIsDisplayed() {
        return driver.findElement(Image).isDisplayed();
    }

    public String getImageSrc() {
        return driver.findElement(Image).getDomAttribute("src");
    }

    public GaragePage clickInstructionsButton() {
        driver.findElement(InstructionsButton).click();
        return this;

    }
    //Test methods
    @Step("Check that car card is displayed")
    public GaragePage verifyCarCardIsDisplayed(SoftAssert softAssert) {
        softAssert.assertTrue(carCardIsDisplayed(), "Car card is not displayed");
        return this;
    }

    @Step("Check that car date is correct")
    public GaragePage verifyCarDate(SoftAssert softAssert, String expectedDate) {
        softAssert.assertTrue(getCarDate().contains(expectedDate), "Car date is incorrect");
        return this;
    }

    @Step("Check that car mileage is correct")
    public GaragePage verifyMileage(SoftAssert softAssert, String expectedMileage) {
        softAssert.assertEquals(getMileage(), expectedMileage, "Mileage is incorrect");
        return this;
    }

    @Step("Check that car image is displayed")
    public GaragePage verifyImageIsDisplayed(SoftAssert softAssert) {
        softAssert.assertTrue(imageIsDisplayed(), "Car image is not displayed");
        return this;
    }

    @Step("Check that image source is correct}")
    public GaragePage verifyCarImage(SoftAssert softAssert, String expectedImage) {
        softAssert.assertTrue(getImageSrc().endsWith(expectedImage), "Image source is incorrect");
        return this;
    }
}

