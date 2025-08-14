package Exercises;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class PatchRequestTest {
    @BeforeClass
    public void configureRestAssured() {
        baseURI = "https://postman-echo.com/";
        port = 443;
    }

    @Test
    @DisplayName("Тестирование PATCH-запроса с использованием архитектурного стиля REST Assured")
    void patchRequestTest() {
        String patchBody = "This is expected to be sent back as part of response body.";
        given().log().body()
            .contentType("text/plain; charset=ISO-8859-1")
            .body(patchBody)
        .when()
            .patch("/patch")
        .then()
            .statusCode(200)
            .log().body().contentType("application/json")
            .body("data",  equalTo(patchBody));
    }
}
