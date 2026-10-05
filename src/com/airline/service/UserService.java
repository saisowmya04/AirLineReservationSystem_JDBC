
package com.airline.service;

import com.airline.dao.UserDAO;
import com.airline.model.User;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    public boolean register(String username, String password, String role)
            throws SQLException {

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                "Username and password are required."
            );
        }

        if (!"USER".equals(role) && !"ADMIN".equals(role)) {
            throw new IllegalArgumentException("Invalid role.");
        }

        return userDAO.register(new User(username, password, role));
    }

    public User login(String username, String password)
            throws SQLException {
        return userDAO.login(username, password);
    }
}