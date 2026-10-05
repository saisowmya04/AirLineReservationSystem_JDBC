
package com.airline.dao;

import com.airline.model.Passenger;
import com.airline.util.DBConnection;

import java.sql.*;

public class PassengerDAO {

    public int addPassenger(Passenger p) throws SQLException {
        String sql = "INSERT INTO passenger "
            + "(passenger_name, email, phone, date_of_birth) "
            + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getPassengerName());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getPhone());

            if (p.getDateOfBirth() == null) {
                ps.setNull(4, Types.DATE);
            } else {
                ps.setDate(4, Date.valueOf(p.getDateOfBirth()));
            }

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }

        throw new SQLException("Passenger ID was not generated.");
    }

    public Passenger getPassengerById(int id) throws SQLException {
        String sql = "SELECT * FROM passenger WHERE passenger_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Passenger p = new Passenger(
                        rs.getString("passenger_name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getDate("date_of_birth") == null
                            ? null
                            : rs.getDate("date_of_birth").toLocalDate()
                    );
                    p.setPassengerId(rs.getInt("passenger_id"));
                    return p;
                }
            }
        }

        return null;
    }
}