import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ApiTest {
    @Test
    public void checkResponseFromLogout() {
        Response response = RestAssured
                .given()
                .log()
                .all()
                .when()
                .get("https://qauto.forstudy.space/api/auth/logout");
        String body = response.jsonPath().getString("status");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(body, "ok");
        softAssert.assertAll();
    }
}
