package patterns.pageobject.selenide;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private SelenideElement GuestLoginButton = $(byXpath("//button[text()=\"Guest log in\"]"));
    public HomePage open() {
        Selenide.open("https://guest:welcome2qauto@qauto.forstudy.space/");
        return this;
    }
    public HomePage clickLoginButton() {
        $(GuestLoginButton).click();
        return this;
    }
}