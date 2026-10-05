
package com.airline.dao;

import com.airline.model.User;
import com.airline.util.DBConnection;

import java.sql.*;

public class UserDAO {

    public boolean register(User user) throws SQLException {
        String sql = "INSERT INTO users(username, password, role) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());

            return ps.executeUpdate() == 1;
        }
    }

    public User login(String username, String password)
            throws SQLException {

        String sql = "SELECT user_id, username, password, role "
                   + "FROM users WHERE username = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                    user.setUserId(rs.getInt("user_id"));
                    return user;
                }
            }
        }

        return null;
    }
}