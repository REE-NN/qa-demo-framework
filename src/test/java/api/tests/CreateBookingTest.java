package api.tests;

import api.BaseApiSpec;
import api.clients.BookingClient;
import api.models.Booking;
import api.models.BookingDates;
import api.models.CreateBookingResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CreateBookingTest extends BaseApiSpec {

    private final BookingClient bookingClient = new BookingClient();

    @Test
    void createBooking_shouldReturn200_andEchoRequestBody() {
        // Подготовим данные (простые валидные даты)
        String checkin  = LocalDate.now().plusDays(7).toString();
        String checkout = LocalDate.now().plusDays(14).toString();

        Booking request = Booking.builder()
                                 .firstname("John")
                                 .lastname("Doe")
                                 .totalprice(123)
                                 .depositpaid(true)
                                 .bookingdates(BookingDates.builder()
                                                           .checkin(checkin)
                                                           .checkout(checkout)
                                                           .build())
                                 .additionalneeds("Breakfast")
                                 .build();

        // Действие
        CreateBookingResponse resp = bookingClient.createBooking(request);

        // Проверки (минимально достаточные и читаемые)
        assertTrue(resp.getBookingid() > 0, "bookingid должен быть > 0");
        assertNotNull(resp.getBooking(), "booking в ответе не должен быть null");

        Booking echoed = resp.getBooking();
        assertEquals(request.getFirstname(), echoed.getFirstname(), "firstname");
        assertEquals(request.getLastname(),  echoed.getLastname(),  "lastname");
        assertEquals(request.getTotalprice(),echoed.getTotalprice(),"totalprice");
        assertEquals(request.isDepositpaid(),echoed.isDepositpaid(),"depositpaid");
        assertEquals(request.getAdditionalneeds(), echoed.getAdditionalneeds(), "additionalneeds");
        assertEquals(request.getBookingdates().getCheckin(),  echoed.getBookingdates().getCheckin(),  "checkin");
        assertEquals(request.getBookingdates().getCheckout(), echoed.getBookingdates().getCheckout(), "checkout");
    }
}
