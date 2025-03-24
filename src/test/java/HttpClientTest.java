import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientTest {
    @Test
    public void httpclientTest() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://qauto.forstudy.space/api/cars/brands"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(response.body());
            boolean idExists = jsonResponse.findValuesAsText("id").contains("1");
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(response.statusCode(), 200);
            softAssert.assertTrue(idExists);
            softAssert.assertAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
