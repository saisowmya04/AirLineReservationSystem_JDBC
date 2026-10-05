
package com.airline.model;

import java.time.LocalDate;

public class Passenger {
    private int passengerId;
    private String passengerName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;

    public Passenger() {}

    public Passenger(String passengerName, String email,
                     String phone, LocalDate dateOfBirth) {
        this.passengerName = passengerName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public int getPassengerId() { 
    	return passengerId; 
    }
    
    public void setPassengerId(int id) { 
    	this.passengerId = id; 
    }

    public String getPassengerName() { 
    	return passengerName; 
    }
    
    public void setPassengerName(String name) { 
    	this.passengerName = name; 
    }

    public String getEmail() { 
    	return email; 
    }
    
    public void setEmail(String email) { 
    	this.email = email; 
    }

    public String getPhone() { 
    	return phone; 
    }
    
    public void setPhone(String phone) { 
    	this.phone = phone; 
    }

    public LocalDate getDateOfBirth() { 
    	return dateOfBirth; 
    }
    
    public void setDateOfBirth(LocalDate dob) { 
    	this.dateOfBirth = dob; 
    }
}