package api.tests;

import api.BaseApiSpec;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SmokeTest extends BaseApiSpec {
    @Test
    void pingRestfulBooker() {
        given()
                .when()
                .get("/ping")
                .then().statusCode(201);
    }
}
