package automation.api.payments;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Example API automation test – AI-generated tests will follow similar patterns.
 */
public class Payments_AutoTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = System.getProperty("payments.api.baseUrl", "https://api.example.com");
    }

    @Test
    public void createPayment_shouldReturn200AndSuccessStatus() {
        String requestBody = "{\n" +
                "  \"amount\": 1000,\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"sourceAccount\": \"1234567890\",\n" +
                "  \"destinationAccount\": \"0987654321\"\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/v1/payments")
        .then()
            .statusCode(200)
            .body("status", equalTo("SUCCESS"));
    }

    @Test
    public void createPayment_withInvalidAmount_shouldReturn400() {
        String requestBody = "{\n" +
                "  \"amount\": -1,\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"sourceAccount\": \"1234567890\",\n" +
                "  \"destinationAccount\": \"0987654321\"\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/v1/payments")
        .then()
            .statusCode(400)
            .body("errorCode", equalTo("INVALID_AMOUNT"));
    }
}
