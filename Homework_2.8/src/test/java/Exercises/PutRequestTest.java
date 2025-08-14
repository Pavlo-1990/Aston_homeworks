package Exercises;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class PutRequestTest {
    @BeforeClass
    public void configureRestAssured() {
        baseURI = "https://postman-echo.com/";
        port = 443;
    }

    @Test
    @DisplayName("Тестирование PUT-запроса с использованием REST Assured")
    void putRawDataRequestTest() {
        String putRawTextBody = "This is expected to be sent back as part of response body.";

        given()
            .log().body().contentType("text/plain")
            .body(putRawTextBody)
        .when()
            .put("/put")
        .then()
            .statusCode(200)
            .log().body().contentType("application/json")
            .body("data", equalTo(putRawTextBody));
    }
}
