package Exercises;

import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class PostFormDataRequestTest {
    @BeforeClass
    public void configureRestAssured() {
        baseURI = "https://postman-echo.com/";
        port = 443;
    }

    @Test
    @DisplayName("Тестирование POST-запроса (form data) с использованием REST Assured")
    void postFormDataRequestTest() {
        String postFormDataBody = "foo1=bar1&foo2=bar2";

        given()
            .log().body().contentType("application/x-www-form-urlencoded; charset=utf-8")
            .body(postFormDataBody)
        .when()
            .post("/post")
        .then()
            .statusCode(200)
            .log().body().contentType("application/json")
            .body("form.foo1", equalTo("bar1"))
            .body("form.foo2", equalTo("bar2"));
    }
}
