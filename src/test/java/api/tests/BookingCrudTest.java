package api.tests;

import api.BaseApiSpec;
import api.clients.BookingClient;
import api.models.Booking;
import api.models.BookingDates;
import api.models.CreateBookingResponse;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("API Tests")
@Feature("Booking CRUD")
@Story("Create → Get → Delete booking")
@Owner("Ekaterina QA")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Полный CRUD-флоу бронирования")
public class BookingCrudTest extends BaseApiSpec {
    private final BookingClient bookingClient = new BookingClient();

    @Test
    @Description("Создание, получение и удаление бронирования с проверкой 404 после удаления")
    void create_get_delete_flow() {
        // arrange
        String checkin = LocalDate.now().plusDays(3).toString();
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

        // create (ШАГ ВОЗВРАЩАЕТ ID)

        int id = Allure.step("Создание бронирования", () -> {
            CreateBookingResponse created = bookingClient.createBooking(request);
            int bookingId = created.getBookingid();
            assertTrue(bookingId > 0, "bookingid должен быть > 0");
            return bookingId;
        });

        Allure.step("Проверка, что данные совпадают", () -> {
            // get
            Booking actual = bookingClient.getBooking(id);
            assertEquals(request.getFirstname(), actual.getFirstname());
            assertEquals(request.getLastname(), actual.getLastname());
            assertEquals(request.getTotalprice(), actual.getTotalprice());
            assertEquals(request.isDepositpaid(), actual.isDepositpaid());
            assertEquals(request.getBookingdates().getCheckin(), actual.getBookingdates().getCheckin());
            assertEquals(request.getBookingdates().getCheckout(), actual.getBookingdates().getCheckout());
            assertEquals(request.getAdditionalneeds(), actual.getAdditionalneeds());
        });

        Allure.step("Удаление и проверка 404", () -> {
            // delete
            bookingClient.deleteBooking(id);

            // verify 404 after delete
            given()
                    .when()
                    .get("/booking/{id}", id)
                    .then()
                    .statusCode(404);
        });

    }
}