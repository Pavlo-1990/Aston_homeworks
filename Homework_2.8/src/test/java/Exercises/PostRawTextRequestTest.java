package Exercises;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class PostRawTextRequestTest {
    @BeforeClass
    public void configureRestAssured() {
        baseURI = "https://postman-echo.com/";
        port = 443;
    }

    @Test
    @DisplayName("Тестирование POST-запроса (raw text) с использованием REST Assured")
    void postRawTextRequestTest() {
        String postRawTextBody = "{\n    \"test\": \"value\"\n}";
        given().log().body()
            .contentType("text/plain")
            .body(postRawTextBody)
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .log().body().contentType("application/json")
            .body("data",  equalTo(postRawTextBody));
    }
}
