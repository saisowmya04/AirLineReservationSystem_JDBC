
package com.airline.service;

import com.airline.dao.FlightDAO;
import java.sql.SQLException;

public class FlightService {
    private final FlightDAO flightDAO = new FlightDAO();

    public void showAllFlights() throws SQLException {
        flightDAO.displayFlights();
    }

    public boolean isValidFlight(int flightId) throws SQLException {
        return flightId > 0 && flightDAO.flightExists(flightId);
    }
}