
package com.airline.main;

import com.airline.model.Passenger;
import com.airline.model.User;
import com.airline.service.UserService;
import com.airline.service.FlightService;
import com.airline.service.BookingService;
import com.airline.dao.PassengerDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class AirlineReservationSystem {

    private static final Scanner sc = new Scanner(System.in);

    private static final UserService userService = new UserService();
    private static final FlightService flightService = new FlightService();
    private static final BookingService bookingService = new BookingService();
    private static final PassengerDAO passengerDAO = new PassengerDAO();

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("\n=== AIRLINE RESERVATION SYSTEM ===");
                System.out.println("1. Register user");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose: ");

                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> {
                        System.out.println("Thank you!");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void register() throws SQLException {
        System.out.print("Username: ");
        String username = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine();

        boolean registered = userService.register(
            username, password, "USER"
        );

        System.out.println(
            registered ? "Registration successful."
                       : "Registration failed."
        );
    }

    private static void login() throws SQLException {
        System.out.print("Username: ");
        String username = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userService.login(username, password);

        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }

        System.out.println("Welcome, " + user.getUsername());
        if ("ADMIN".equals(user.getRole())) {
            adminMenu();
        } else if ("USER".equals(user.getRole())) {
            userMenu();
        } else {
            System.out.println("Unknown account role.");
        }
    }

    private static void adminMenu() throws SQLException {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Display flights");
            System.out.println("2. Display bookings");
            System.out.println("3. Logout");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> flightService.showAllFlights();
                case 2 -> bookingService.showAllBookings();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void userMenu() throws SQLException {
        while (true) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. Display flights");
            System.out.println("2. Add passenger");
            System.out.println("3. Book flight");
            System.out.println("4. Display bookings");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> flightService.showAllFlights();
                case 2 -> addPassenger();
                case 3 -> bookFlight();
                case 4 -> bookingService.showAllBookings();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addPassenger() throws SQLException {
        System.out.print("Passenger name: ");
        String name = sc.nextLine().trim();

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Phone: ");
        String phone = sc.nextLine().trim();

        System.out.print("Date of birth (YYYY-MM-DD): ");
        String dobText = sc.nextLine().trim();

        LocalDate dob = dobText.isEmpty()
                ? null : LocalDate.parse(dobText);

        Passenger passenger = new Passenger(name, email, phone, dob);
        int id = passengerDAO.addPassenger(passenger);

        System.out.println("Passenger added. ID: " + id);
    }

    private static void bookFlight() throws SQLException {
        System.out.print("Passenger ID: ");
        int passengerId = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Flight ID: ");
        int flightId = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Seat number: ");
        String seat = sc.nextLine().trim();

        System.out.print("Amount: ");
        BigDecimal amount = new BigDecimal(sc.nextLine().trim());

        int bookingId = bookingService.bookFlight(
            passengerId, flightId, seat, amount
        );

        System.out.println(
            "Booking created successfully. Booking ID: " + bookingId
        );
    }
}