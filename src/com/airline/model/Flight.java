
package com.airline.model;

import java.time.LocalDateTime;

public class Flight {
    private int flightId;
    private String flightNumber;
    private int airlineId;
    private int sourceAirportId;
    private int destinationAirportId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int totalSeats;

    public Flight() {}

    public int getFlightId() { 
    	return flightId; 
    }
    
    public void setFlightId(int id) { 
    	this.flightId = id; 
    }

    public String getFlightNumber() { 
    	return flightNumber; 
    }
    
    public void setFlightNumber(String number) { 
    	this.flightNumber = number; 
    }

    public int getAirlineId() { 
    	return airlineId; 
    }
    
    public void setAirlineId(int id) { 
    	this.airlineId = id; 
    }

    public int getSourceAirportId() { 
    	return sourceAirportId; 
    }
    
    public void setSourceAirportId(int id) { 
    	this.sourceAirportId = id; 
    }

    public int getDestinationAirportId() { 
    	return destinationAirportId; 
    }
    
    public void setDestinationAirportId(int id) {
        this.destinationAirportId = id;
    }

    public LocalDateTime getDepartureTime() { 
    	return departureTime; 
    }
    
    public void setDepartureTime(LocalDateTime time) {
        this.departureTime = time;
    }

    public LocalDateTime getArrivalTime() { 
    	return arrivalTime; 
    }
    
    public void setArrivalTime(LocalDateTime time) {
        this.arrivalTime = time;
    }

    public int getTotalSeats() { 
    	return totalSeats; 
    }
    
    public void setTotalSeats(int seats) { 
    	this.totalSeats = seats; 
    }
}