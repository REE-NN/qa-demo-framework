package api;

import common.config.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.config.HttpClientConfig.httpClientConfig;

public class BaseApiSpec {

    protected static RequestSpecification spec;

    @BeforeAll
    static void setUpApi() {
        var cfg = ConfigLoader.cfg();

        // Глобальные настройки Rest-Assured
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        spec = new RequestSpecBuilder()
                .setBaseUri(cfg.baseUrl())
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();

        // Таймауты HTTP-клиента (важно для нестабильных демо-API)
        RestAssured.config = RestAssured.config()
                                        .httpClient(httpClientConfig()
                                                .setParam("http.socket.timeout", cfg.httpTimeoutMs())
                                                .setParam("http.connection.timeout", cfg.httpTimeoutMs())
                                                .setParam("http.connection-manager.timeout",
                                                        (long) cfg.httpTimeoutMs()));

        // Сделаем spec дефолтным для given()
        RestAssured.requestSpecification = spec;
    }
}
