package api.clients;

import api.models.Booking;
import api.models.CreateBookingResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingClient {
    public CreateBookingResponse createBooking(Booking booking) {
        return given()
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(CreateBookingResponse.class);
    }

    public Booking getBooking(int id) {
        return given()
                .when()
                .get("/booking/{id}", id)
                .then()
                .statusCode(200)
                .extract()
                .as(Booking.class);
    }

    public void deleteBooking(int id) {
        String token = createToken("admin", "password123");
        given()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/{id}", id)
                .then()
                // У Restful-Booker успешный DELETE возвращает 201
                .statusCode(201);
    }

    private String createToken(String username, String password) {
        return given()
                .body(Map.of("username", username, "password", password))
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

}
