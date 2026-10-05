package com.airline.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/airlinereservation";

    private static final String user = "root";

    private static final String pwd = "root";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL,user,pwd);
    }
}