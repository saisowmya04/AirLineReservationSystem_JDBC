# ✈️ Airline Reservation System

A console-based **Airline Reservation System** developed using **Core Java, JDBC, and MySQL**. This project allows users to search flights, manage passenger details, book flights, cancel bookings, and view reservation information.

The project demonstrates how a Java application connects to a MySQL database using **JDBC** and follows a layered architecture using **Model, DAO, Service, and Utility** packages.

---

## 📌 Project Overview

The Airline Reservation System is designed to simplify airline reservation operations by providing functionalities for:

* User registration and login
* Flight management
* Passenger management
* Flight search
* Ticket booking
* Booking cancellation
* Viewing booking details
* Database connectivity using JDBC
* CRUD operations using MySQL

This project helped me understand how **Core Java applications interact with relational databases using JDBC**.

---

## 🚀 Features

### 👤 User Management

* User registration
* User login
* User authentication
* User information management

### ✈️ Flight Management

* Add flight details
* View available flights
* Search flights
* Update flight information
* Delete flight information

### 🧑 Passenger Management

* Add passenger details
* View passenger information
* Update passenger details
* Delete passenger details

### 🎫 Booking Management

* Book a flight
* View booking details
* Check reservation status
* Cancel booking
* Store booking information in MySQL

### 🗄️ Database Management

* MySQL database integration
* JDBC connectivity
* PreparedStatement for SQL operations
* ResultSet for retrieving data
* CRUD operations

---

## 🛠️ Technologies Used

| Technology       | Purpose                             |
| ---------------- | ----------------------------------- |
| **Java**         | Application development             |
| **Core Java**    | OOP and business logic              |
| **JDBC**         | Java-Database connectivity          |
| **MySQL**        | Database management                 |
| **Eclipse IDE**  | Development environment             |
| **Git & GitHub** | Version control and project hosting |

---

## 📂 Project Structure

```text
AirLineReservationSystem_JDBC
│
├── src
│   └── com.airline
│       │
│       ├── model
│       │   ├── User.java
│       │   ├── Flight.java
│       │   ├── Passenger.java
│       │   └── Booking.java
│       │
│       ├── dao
│       │   ├── UserDAO.java
│       │   ├── FlightDAO.java
│       │   ├── PassengerDAO.java
│       │   └── BookingDAO.java
│       │
│       ├── service
│       │   ├── UserService.java
│       │   ├── FlightService.java
│       │   └── BookingService.java
│       │
│       ├── util
│       │   └── DBConnection.java
│       │
│       └── main
│           └── AirlineReservationApp.java
│
├── lib
│   └── mysql-connector-j-xxxxx.jar
│
├── .gitignore
└── README.md
```

---

## 🏗️ Architecture

The project follows a simple **layered architecture**.

```text
             User
              │
              ▼
     AirlineReservationApp
              │
              ▼
          Service Layer
              │
              ▼
            DAO Layer
              │
              ▼
        JDBC / DBConnection
              │
              ▼
          MySQL Database
```

### 1. Model Layer

The `model` package contains Java classes representing database entities.

Examples:

* `User`
* `Flight`
* `Passenger`
* `Booking`

These classes contain fields, constructors, getters, setters, and other required methods.

---

### 2. DAO Layer

The `dao` package contains classes responsible for database operations.

Examples:

```text
UserDAO
FlightDAO
PassengerDAO
BookingDAO
```

The DAO layer performs operations such as:

```text
INSERT
SELECT
UPDATE
DELETE
```

JDBC classes such as `Connection`, `PreparedStatement`, and `ResultSet` are used to communicate with MySQL.

---

### 3. Service Layer

The `service` package contains the application's business logic.

Examples:

```text
UserService
FlightService
BookingService
```

The service layer receives requests from the main application and communicates with the DAO layer.

---

### 4. Utility Layer

The `util` package contains the database connection class.

```text
DBConnection.java
```

It establishes a connection between Java and MySQL using JDBC.

Example JDBC URL:

```java
jdbc:mysql://localhost:3306/airlinereservation
```

---

### 5. Main Layer

The `main` package contains:

```text
AirlineReservationApp.java
```

This is the entry point of the application.

It displays the application menu and allows the user to perform different operations.

---

## 🔄 JDBC Flow

The basic flow of the application is:

```text
Java Application
       │
       ▼
   JDBC Driver
       │
       ▼
 DBConnection.java
       │
       ▼
     MySQL
       │
       ▼
 SQL Query Execution
       │
       ▼
 ResultSet / Database Update
```

For example, when a user searches for a flight:

```text
User
 ↓
AirlineReservationApp
 ↓
FlightService
 ↓
FlightDAO
 ↓
JDBC
 ↓
MySQL
 ↓
Flight Details
 ↓
User
```

---

## 🗄️ Database

The project uses **MySQL** as the relational database.

Example database:

```sql
CREATE DATABASE airlinereservation;
```

Select the database:

```sql
USE airlinereservation;
```

The database contains tables for storing information related to users, flights, passengers, bookings, and other reservation-related data.

---

## 🔌 JDBC Connection

The project uses MySQL Connector/J to connect Java with MySQL.

Example:

```java
public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/airlinereservation";

    private static final String USER = "root";

    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

> Replace `your_password` with your local MySQL password.

---

## ⚙️ Setup Instructions

### Step 1: Install Java

Install JDK and verify the installation:

```bash
java -version
```

---

### Step 2: Install MySQL

Install MySQL Server and MySQL Workbench.

Verify that the MySQL service is running.

---

### Step 3: Create the Database

Open MySQL Workbench or MySQL Command Line and execute:

```sql
CREATE DATABASE airlinereservation;
```

Then:

```sql
USE airlinereservation;
```

Create the required tables using the SQL scripts provided with the project.

---

### Step 4: Configure Database Connection

Open:

```text
src/com/airline/util/DBConnection.java
```

Update:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/airlinereservation";

private static final String USER = "root";

private static final String PASSWORD = "your_password";
```

Use your local MySQL username and password.

---

### Step 5: Add MySQL Connector/J

Add the MySQL Connector/J `.jar` file to the project classpath.

Example:

```text
lib/
└── mysql-connector-j-xxxxx.jar
```

In Eclipse:

```text
Right Click Project
       ↓
Build Path
       ↓
Configure Build Path
       ↓
Libraries
       ↓
Classpath
       ↓
Add JARs / Add External JARs
```

Select the MySQL Connector/J `.jar` file.

---

### Step 6: Run the Application

Run:

```text
AirlineReservationApp.java
```

The application starts from the `main()` method.

---

## 💻 Sample Application Flow

```text
====================================
     AIRLINE RESERVATION SYSTEM
====================================

1. User Registration
2. User Login
3. Search Flights
4. Book Flight
5. View Booking
6. Cancel Booking
7. Exit

Enter your choice:
```

Example:

```text
Enter your choice: 3

Enter source: Hyderabad
Enter destination: Delhi

Available Flights:

Flight ID: 101
Airline: Air India
Source: Hyderabad
Destination: Delhi
Price: 4800.00
```

Booking:

```text
Enter Flight ID: 101
Enter Passenger ID: 10

Booking successful!

Booking ID: 176
Flight ID: 101
Passenger ID: 10
Booking Status: CONFIRMED
```

---

## 🔑 Key Java Concepts Used

This project demonstrates several Core Java concepts:

* Classes and Objects
* Encapsulation
* Constructors
* Inheritance
* Polymorphism
* Abstraction
* Interfaces
* Exception Handling
* Collections
* Packages
* Methods
* JDBC
* SQL
* CRUD operations
* Layered architecture

---

## 🗃️ CRUD Operations

The project demonstrates CRUD operations using JDBC.

### Create

```sql
INSERT INTO ...
```

### Read

```sql
SELECT * FROM ...
```

### Update

```sql
UPDATE ...
```

### Delete

```sql
DELETE FROM ...
```

These operations are implemented through the DAO classes.

---

## 🔐 Security Practices

The project uses `PreparedStatement` for executing parameterized SQL queries.

Example:

```java
String sql = "SELECT * FROM users WHERE username = ?";

PreparedStatement ps = connection.prepareStatement(sql);

ps.setString(1, username);

ResultSet rs = ps.executeQuery();
```

Using `PreparedStatement` helps prevent SQL injection and provides safer parameter handling.

---

## 📚 Learning Outcomes

Through this project, I gained practical knowledge of:

* Connecting Java applications with MySQL
* Using JDBC for database operations
* Implementing CRUD operations
* Designing DAO classes
* Creating a layered Java application
* Handling SQL exceptions
* Using PreparedStatement and ResultSet
* Managing relational database data
* Using Git and GitHub for version control

---

## 🔮 Future Enhancements

The project can be enhanced with:

* Admin and User dashboards
* Graphical User Interface
* Web-based interface
* Spring Boot integration
* REST APIs
* Online payment integration
* Email/SMS booking confirmation
* Password encryption
* Role-based authentication
* Flight seat selection
* Ticket generation
* PDF ticket download

---

## 📌 Project Highlights

```text
✔ Core Java
✔ Object-Oriented Programming
✔ JDBC
✔ MySQL
✔ CRUD Operations
✔ DAO Pattern
✔ Service Layer
✔ Exception Handling
✔ PreparedStatement
✔ Git & GitHub
```

---

## 👩‍💻 Author

**Mangamuri Sai Sowmya**

B.Tech – Artificial Intelligence & Data Science

Interested in:

* Java Development
* Full Stack Development
* Data Science
* Artificial Intelligence
* Database Technologies

---

## ⭐ Project Purpose

This project was developed as a practical learning project to understand how **Core Java, JDBC, and MySQL** can be integrated to build a real-world database-driven application.

If you find this project useful, consider giving it a ⭐ on GitHub.
