import org.testng.annotations.DataProvider;

public class UserData {

    @DataProvider
    public Object[][] users() {
        return new Object[][]{
                {"test@hillel.ua", "1111"},
                {"test@hillel.ua", "1234"}
        };
    }
}
