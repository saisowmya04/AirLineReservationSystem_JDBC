
package com.airline.service;

import com.airline.dao.BookingDAO;
import com.airline.dao.FlightDAO;
import com.airline.dao.PassengerDAO;
import com.airline.model.Booking;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BookingService {

    private final BookingDAO bookingDAO = new BookingDAO();
    private final PassengerDAO passengerDAO = new PassengerDAO();
    private final FlightDAO flightDAO = new FlightDAO();

    public int bookFlight(
            int passengerId,
            int flightId,
            String seatNumber,
            BigDecimal amount) throws SQLException {

        if (passengerDAO.getPassengerById(passengerId) == null) {
            throw new IllegalArgumentException("Passenger not found.");
        }

        if (!flightDAO.flightExists(flightId)) {
            throw new IllegalArgumentException("Flight not found.");
        }

        if (seatNumber == null || seatNumber.isBlank()) {
            throw new IllegalArgumentException("Seat number is required.");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                "Amount must be greater than zero."
            );
        }

        Booking booking = new Booking();

        booking.setPassengerId(passengerId);
        booking.setFlightId(flightId);
        booking.setSeatNumber(seatNumber);
        booking.setBookingStatus("CONFIRMED");
        booking.setPaymentAmount(amount);

        System.out.println(
            "DEBUG amount received: " + booking.getPaymentAmount()
        );

        return bookingDAO.createBooking(booking);
    }

    public void showAllBookings() throws SQLException {
        bookingDAO.displayBookings();
    }
}

