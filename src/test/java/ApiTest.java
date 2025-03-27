import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
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

    @Test
    public void checkBrandsResponse() {
        Response response = RestAssured
                .given()
                .log()
                .all()
                .when()
                .get("https://qauto.forstudy.space/api/cars/brands");
        JsonPath jsonPath = response.jsonPath();
        String title = jsonPath.getString("data.title[0]");
        int id = jsonPath.getInt("data.id[1]");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(title, "Audi");
        Assert.assertEquals(id, 2);
    }
}
