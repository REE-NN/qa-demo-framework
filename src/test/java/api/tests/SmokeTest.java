package api.tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SmokeTest {
    @Test
    void pingRestfulBooker() {
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/ping")
                .then().statusCode(201);
    }
}
