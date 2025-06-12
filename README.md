# 🏦 Bank Management System
A complete Spring Boot REST API for managing bank accounts. This application allows creating, retrieving, updating, and deleting bank account data.
It follows a layered architecture with DTO validation, exception handling, and interactive API documentation using Swagger UI.
---
## 📌 Project Description
This project is built to simulate core banking functionalities for managing customer accounts. It supports:
- Adding a new bank account
- Updating or deleting existing accounts
- Fetching account details
- Exception handling at both local and global levels
- API documentation 
- Unit and integration testing
---
## 🧰 Technologies Used
Java 21
Spring Boot 3.4.5
Spring Data JPA (Hibernate)
PostgreSQL (Database)
Spring Validation
MapStruct (DTO Mapping)
SLF4J + Logback (Logging)
JUnit 5 + Mockito (Testing)
Maven
---
### ✅ Prerequisites
- Java 21
- Maven 3.x
- PostgreSQL installed and running
---
### ✅Step 1: Clone the Repository
```bash
git clone https://github.com/nilofarshaikh12/Bank_Management_CRUD.git
cd Bank_Management_CRUD

✅ Step 2: Create the Database
Make sure PostgreSQL is running, then open your terminal or pgAdmin and run:
CREATE DATABASE bank;
ℹ️ Note: The application expects a DB named bank. You can change it in application.properties if needed.

✅ Step 3: Build and Run the Application
   # Run with Maven
      mvn clean install
     	mvn spring-boot:run
    # Or Run from IDE
	   Open the project in IntelliJ or Eclipse
	   Run the BankManagementApplication.java class

### 🌐 API Documentation
After starting the application, visit:
http://localhost:8080/swagger-ui.html
You can test all endpoints (GET, POST, PUT, DELETE) directly in the browser using Swagger UI.

### 🧪 How to Run Tests
use following command:
    mvn test
Includes:
Unit tests using Mockito
Integration tests using MockMvc
Annotated with @SpringBootTest and @Transactional to keep tests isolated

### ❗ Exception Handling
Global: With @ControllerAdvice for centralized error handling
Local: With custom try-catch logic
Custom Exceptions: For account not found, validation errors, etc.


