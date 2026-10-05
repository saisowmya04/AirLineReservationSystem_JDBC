
package com.airline.dao;

import com.airline.model.Booking;
import com.airline.util.DBConnection;

import java.sql.*;

public class BookingDAO {

    public int createBooking(Booking b) throws SQLException {

        String bookingSql =
            "INSERT INTO booking " +
            "(passenger_id, flight_id, seat_number, amount, booking_status) " +
            "VALUES (?, ?, ?, ?, ?)";

        String paymentSql =
            "INSERT INTO payment " +
            "(booking_id, amount, payment_status) " +
            "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try {
                int bookingId;

                // Step 1: Insert booking
                try (PreparedStatement ps = con.prepareStatement(
                        bookingSql, Statement.RETURN_GENERATED_KEYS)) {

                    ps.setInt(1, b.getPassengerId());
                    ps.setInt(2, b.getFlightId());
                    ps.setString(3, b.getSeatNumber());
                    ps.setBigDecimal(4, b.getPaymentAmount());
                    ps.setString(5, b.getBookingStatus());

                    ps.executeUpdate();

                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (!keys.next()) {
                            throw new SQLException(
                                "Booking ID was not generated."
                            );
                        }

                        bookingId = keys.getInt(1);
                    }
                }

                // Step 2: Insert payment using generated booking ID
                try (PreparedStatement ps =
                         con.prepareStatement(paymentSql)) {

                    ps.setInt(1, bookingId);
                    ps.setBigDecimal(2, b.getPaymentAmount());
                    ps.setString(3, "PAID");

                    ps.executeUpdate();
                }

                // Step 3: Save both inserts
                con.commit();

                System.out.println(
                    "Booking and payment saved successfully."
                );

                return bookingId;

            } catch (SQLException | RuntimeException e) {

                con.rollback();
                throw e;

            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public void displayBookings() throws SQLException {

        String sql =
            "SELECT b.booking_id, p.passenger_name, " +
            "f.flight_number, b.seat_number, b.booking_date, " +
            "b.booking_status, b.amount AS booking_amount, " +
            "pay.amount AS payment_amount, pay.payment_status " +
            "FROM booking b " +
            "JOIN passenger p ON b.passenger_id = p.passenger_id " +
            "JOIN flight f ON b.flight_id = f.flight_id " +
            "LEFT JOIN payment pay ON b.booking_id = pay.booking_id " +
            "ORDER BY b.booking_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    "Booking ID: " + rs.getInt("booking_id") +
                    ", Passenger: " + rs.getString("passenger_name") +
                    ", Flight: " + rs.getString("flight_number") +
                    ", Seat: " + rs.getString("seat_number") +
                    ", Status: " + rs.getString("booking_status") +
                    ", Booking Amount: " +
                        rs.getBigDecimal("booking_amount") +
                    ", Payment Amount: " +
                        rs.getBigDecimal("payment_amount") +
                    ", Payment Status: " +
                        rs.getString("payment_status")
                );
            }
        }
    }
}

