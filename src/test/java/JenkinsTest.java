import org.testng.annotations.Test;

public class JenkinsTest {
    @Test(groups = {"ui"})
    public void LoginSuccess() {
        System.out.println("Login success");
    }
}
