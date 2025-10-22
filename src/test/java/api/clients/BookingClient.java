package api.clients;

import api.models.Booking;
import api.models.CreateBookingResponse;

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
}
