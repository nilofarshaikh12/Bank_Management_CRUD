# 🏦 Bank Management System
- A complete Spring Boot REST API for managing bank accounts. This application allows creating, retrieving, updating, and deleting bank account data.
It follows a layered architecture with DTO validation, exception handling, and interactive API documentation using Swagger UI.
---
### 📌 Project Description
This project is built to simulate core banking functionalities for managing customer accounts. It supports:
- Adding a new bank account
- Updating or deleting existing accounts
- Fetching account details
- Exception handling at both local and global levels
- API documentation 
- Unit and integration testing
---
### 🧰 Technologies Used
| Technology          | Description                          |
|---------------------|--------------------------------------|
| Java                | Version 21                           |
| Spring Boot         | Version 3.4.5                        |
| Spring Data JPA     | Hibernate-based ORM                  |
| PostgreSQL          | Relational Database                  |
| JUnit 5 + Mockito   | Testing Framework                    |
| Maven               | Build and Dependency Management      |
---
### ✅ Prerequisites
- Java 21
- Maven 3.x
- PostgreSQL installed and running
---
### 📥 Step 1:Clone the repository and navigate to the project directory:

```bash
# Clone the repository from GitHub
git clone https://github.com/nilofarshaikh12/Bank_Management_CRUD.git

# Move into the project directory
cd Bank_Management_CRUD

### 🗃️ Step 2: Create the Database
- Make sure PostgreSQL is installed and running.
- Open your terminal or pgAdmin and run the following command to create the database:
```bash
# Create PostgreSQL database named 'bank'
CREATE DATABASE bank;

- ℹ️ Note: The application expects a database named bank. You can change this in src/main/resources/application.properties if needed.

### ⚙️ Step 3: Build and Run the Application
#### ▶️ Run with Maven:
```bash
# Clean and build the project
mvn clean install

# Run the Spring Boot application
mvn spring-boot:run

#### 💻 Or Run from IDE:
# Open the project in IntelliJ IDEA or Eclipse
# Locate the main class: BankManagementApplication.java
# Run it as a Java Application

---
### 🌐 API Documentation with Swagger

Once the app is running, open your browser and visit:

🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

You can test all endpoints (GET, POST, PUT, DELETE) directly using Swagger UI.

---
### 🧪 How to Run Tests
Use the following command to run all tests:
```bash
mvn test

- Includes:
- ✅ Unit tests with Mockito
- ✅ Integration tests with MockMvc
- ✅ Annotated with @SpringBootTest and @Transactional to keep tests isolated

---
### ❗ Exception Handling Strategy
- Global: Centralized error handling with @ControllerAdvice
- Local: Custom try-catch blocks where needed
- Custom Exceptions: For cases like “Account Not Found”, validation failures, etc.




