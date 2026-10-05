package com.airline.dao;

import com.airline.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightDAO {

    // Display all flights
    public void displayFlights() throws SQLException {

        String sql =
                "SELECT f.flight_id, f.flight_number, " +
                "a.airline_name, " +
                "src.city AS source_city, " +
                "dest.city AS destination_city, " +
                "f.departure_time, f.arrival_time, f.total_seats " +
                "FROM flight f " +
                "JOIN airline a ON f.airline_id = a.airline_id " +
                "JOIN airport src ON f.source_airport_id = src.airport_id " +
                "JOIN airport dest ON f.destination_airport_id = dest.airport_id " +
                "ORDER BY f.flight_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println();
            System.out.println(
                    "ID | Flight | Airline | From | To | Departure | Seats"
            );

            while (rs.next()) {

                System.out.println(
                        rs.getInt("flight_id") + " | " +
                        rs.getString("flight_number") + " | " +
                        rs.getString("airline_name") + " | " +
                        rs.getString("source_city") + " | " +
                        rs.getString("destination_city") + " | " +
                        rs.getTimestamp("departure_time") + " | " +
                        rs.getInt("total_seats")
                );
            }
        }
    }

    // Check whether flight exists
    public boolean flightExists(int flightId) throws SQLException {

        String sql =
                "SELECT flight_id, flight_number " +
                "FROM flight " +
                "WHERE flight_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, flightId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    System.out.println(
                            "Flight found: " +
                            rs.getInt("flight_id") +
                            " - " +
                            rs.getString("flight_number")
                    );

                    return true;
                }

                System.out.println(
                        "No flight found with ID: " + flightId
                );

                return false;
            }
        }
    }
}