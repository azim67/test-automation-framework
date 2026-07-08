package api;

import api.models.Booking;
import api.models.BookingDates;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.notNullValue;

public class BookingApiTest extends BaseApi {

    @Test
    public void userCanGetBookingById() {
        RestAssured
                .given()
                .baseUri(BASE_URI)
                .when()
                .get("/booking/1")
                .then()
                .statusCode(200)
                .body("firstname", notNullValue())
                .body("lastname", notNullValue());
    }

    @Test
    public void bookingNotFoundReturns404() {
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .when()
                .get("/booking/999999999")
                .then()
                .statusCode(404);
    }

    @Test
public void userCanCreateBooking() {

    BookingDates bookingDates =
            new BookingDates("2026-07-10", "2026-07-15");

    Booking booking =
            new Booking(
                    "Azim",
                    "Wafi",
                    150,
                    true,
                    bookingDates,
                    "Breakfast"
            );

    RestAssured
            .given()
            .baseUri(BASE_URI)
            .contentType("application/json")
            .body(booking)
            .when()
            .post("/booking")
            .then()
            .statusCode(200)
            .body("bookingid", notNullValue());
}
}