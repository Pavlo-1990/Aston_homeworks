package Exercises;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequestTest {
    @BeforeClass
    public void configureRestAssured() {
        baseURI = "https://postman-echo.com/";
        port = 443;

    }

    @Test
    @DisplayName("Тестирование DELETE-запроса с использованием REST Assured")
    void deleteRequestTest() {
        String deleteBody = "This is expected to be sent back as part of response body.";
        given()
            .log().body().contentType("text/plain; charset=ISO-8859-1")
            .body(deleteBody)
        .when()
            .delete("/delete")
        .then()
            .statusCode(200)
            .log().body().contentType("application/json; charset=utf-8")
            .body("data",  equalTo(deleteBody));
    }
}
