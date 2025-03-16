package patterns.pageobject.selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.Select;
import static com.codeborne.selenide.Selenide.*;

public class GaragePage {
    private SelenideElement AddCarButton = $x("//button[text()=\"Add car\"]");
    private SelenideElement BrandSelect = $("#addCarBrand");
    private SelenideElement ModelSelect = $("#addCarModel");
    private SelenideElement Mileage =$x("//input[@name='miles']");
    private SelenideElement MileageField = $x("//input[@id=\"addCarMileage\"]");
    private SelenideElement AddButton = $x("//button[text()=\"Add\"]");
    private SelenideElement Image = $("img.car-logo_img");
    private SelenideElement GarageCarCard = $x("//p[text()=\"Audi TT\"]");
    private SelenideElement Date = $("p.car_update-mileage");

    public GaragePage clickAddCarButton() {
        AddCarButton.click();
        return this;
    }
    public GaragePage selectBrandAudi() {
        SelenideElement brandDropdown = $(BrandSelect);
        Select select = new Select(brandDropdown);
        select.selectByVisibleText("Audi");
        return this;
    }

    public GaragePage selectModelTT() {
        SelenideElement modelDropdown = $(ModelSelect);
        modelDropdown.selectOptionContainingText("TT");
        return this;
    }

    public GaragePage setMileage(String mileage) {
      $(MileageField).sendKeys(mileage);
        return this;
    }
    public GaragePage addButtonClick() {
       $(AddButton).click();
        return this;
    }
    public SelenideElement getCarCard() {
        return $(GarageCarCard);
    }

    public SelenideElement getCarDate() {
        return $(Date);
    }

    public String getMileage() {
        return executeJavaScript("return arguments[0].value;", $(Mileage));
    }

    public SelenideElement getImage() {
        return $(Image);
    }
}
