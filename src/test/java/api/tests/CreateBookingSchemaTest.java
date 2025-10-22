package api.tests;

import api.BaseApiSpec;
import api.clients.BookingClient;
import api.models.Booking;
import api.models.BookingDates;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateBookingSchemaTest extends BaseApiSpec {
    private final BookingClient bookingClient = new BookingClient();

    @Test
    @Description("Контракт: POST /booking соответствует JSON-схеме")
    void createBooking_matchesSchema() {
        var req = Booking.builder()
                         .firstname("Schema")
                         .lastname("Check")
                         .totalprice(111)
                         .depositpaid(true)
                         .bookingdates(new BookingDates(
                                 LocalDate.now().plusDays(2).toString(),
                                 LocalDate.now().plusDays(4).toString()))
                         .additionalneeds("Breakfast")
                         .build();

        given()
                .body(req)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/create-booking-response.json"));
    }
}
