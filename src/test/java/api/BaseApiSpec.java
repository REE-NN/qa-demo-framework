package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiSpec {

    protected static RequestSpecification spec;

    @BeforeAll
    static void setUpApi() {
        // Временный способ задавать baseUrl (пока без Owner):
        // можно переопределить через -DbaseUrl=... при запуске.
        String baseUrl = System.getProperty(
                "baseUrl",
                "https://restful-booker.herokuapp.com"
        );

        // Глобальные настройки Rest-Assured
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();

        // Сделаем spec дефолтным для given()
        RestAssured.requestSpecification = spec;

    }
}
