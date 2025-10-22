package api.tests;

import api.BaseApiSpec;
import api.clients.BookingClient;
import api.models.Booking;
import api.models.BookingDates;
import api.models.CreateBookingResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingCrudTest extends BaseApiSpec {
    private final BookingClient bookingClient = new BookingClient();

    @Test
    void create_get_delete_flow() {
        // arrange
        String checkin  = LocalDate.now().plusDays(3).toString();
        String checkout = LocalDate.now().plusDays(6).toString();

        Booking request = Booking.builder()
                                 .firstname("Jane")
                                 .lastname("Doe")
                                 .totalprice(200)
                                 .depositpaid(false)
                                 .bookingdates(BookingDates.builder()
                                                           .checkin(checkin)
                                                           .checkout(checkout)
                                                           .build())
                                 .additionalneeds("Late checkout")
                                 .build();

        // create
        CreateBookingResponse created = bookingClient.createBooking(request);
        int id = created.getBookingid();
        assertTrue(id > 0, "bookingid должен быть > 0");

        // get
        Booking actual = bookingClient.getBooking(id);
        assertEquals(request.getFirstname(), actual.getFirstname());
        assertEquals(request.getLastname(),  actual.getLastname());
        assertEquals(request.getTotalprice(),actual.getTotalprice());
        assertEquals(request.isDepositpaid(),actual.isDepositpaid());
        assertEquals(request.getBookingdates().getCheckin(),  actual.getBookingdates().getCheckin());
        assertEquals(request.getBookingdates().getCheckout(), actual.getBookingdates().getCheckout());
        assertEquals(request.getAdditionalneeds(), actual.getAdditionalneeds());

        // delete
        bookingClient.deleteBooking(id);

        // verify 404 after delete
        given()
                .when()
                .get("/booking/{id}", id)
                .then()
                .statusCode(404);
    }
}
