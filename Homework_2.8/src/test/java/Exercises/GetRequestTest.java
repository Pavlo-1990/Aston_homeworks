package Exercises;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetRequestTest {
    @BeforeClass
    public void configureRestAssured() {
        baseURI = "https://postman-echo.com/";
        port = 443;
    }

    @Test
    @DisplayName("Тестирование GET-запроса с использованием REST Assured")
    void getRequestTest() {
        given()
            .queryParam("foo1", "bar1")
            .queryParam("foo2", "bar2")
        .when()
            .get("/get") // или .get("/get?foo1=bar1&foo2=bar2") вместо .queryParam
        .then()
            .log().body().contentType("application/json")
            .statusCode(200)
            .body("args.foo1", equalTo("bar1"))
            .body("args.foo2", equalTo("bar2"));
    }
}


