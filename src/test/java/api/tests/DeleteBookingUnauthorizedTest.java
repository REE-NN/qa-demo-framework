package api.tests;

import api.BaseApiSpec;
import api.clients.BookingClient;
import api.models.Booking;
import api.models.BookingDates;
import api.models.CreateBookingResponse;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class DeleteBookingUnauthorizedTest extends BaseApiSpec {
    private final BookingClient bookingClient = new BookingClient();

    @Test
    @Description("DELETE /booking/{id} без авторизации -> 403")
    void deleteWithoutAuth_forbidden() {
        var req = Booking.builder()
                         .firstname("No")
                         .lastname("Auth")
                         .totalprice(50)
                         .depositpaid(false)
                         .bookingdates(new BookingDates(
                                 LocalDate.now().plusDays(1).toString(),
                                 LocalDate.now().plusDays(2).toString()))
                         .build();

        CreateBookingResponse created = bookingClient.createBooking(req);
        int id = created.getBookingid();

        given()
                .when()
                .delete("/booking/{id}", id)
                .then()
                .statusCode(403);
    }
}
