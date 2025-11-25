# Finsight – Project Setup Guide

This document explains how to install, configure, and run the backend locally—including MySQL setup, application configuration, Swagger UI, and environment variables.

## 1. Prerequisites
Make sure the following tools are installed:
**Required Software**
- Java 24
- Maven 3.9+
- MySQL Server 9.4+
- IntelliJ IDEA (recommended)
- Git
- Postman (optional, for API testing)


## 2. Clone the Repository
- Open a terminal or command prompt and run the following command to clone the repo:
````
git clone <git-repo-url>
````
- Git will create a folder with the same name as your repository. This is your project folder.
- Navigate into the project folder:
````
cd your-project-folder
````

## 3. Database MySQL Setup
### 3.1 Create Database
- Log into MySQL:
````
mysql -u root -p
````

- Create DB:
````
  CREATE DATABASE expense_tracker;
````

## 4. Configure Application Properties
### 4.1 Edit src/main/resources/application.properties
```properties
pring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Swagger/OpenAPI
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
```
**Environment Variables (Optional)**
Instead of storing the DB password in the file, you can set it:
- macOS/Linux:
````
  export DB_PASSWORD=yourpassword
````
- Windows PowerShell:
````
  setx DB_PASSWORD "yourpassword"
````
- Then update config as:
```properties
spring.datasource.password=${DB_PASSWORD}
```
## 5. Build and Run the Spring Boot App
### 5.1 Run with Maven
````
mvn spring-boot:run
````

### 5.2 Run from IntelliJ
- Open IntelliJ → “Run” → “Run ExpenseTrackerApplication”
- The application will start on:
````
  http://localhost:8080
````

## 6. Access Swagger UI
Once the application is running, open:
````
http://localhost:8080/swagger-ui.html
````

Here we will see:
- All API endpoints
- Request/response formats
- Ability to test APIs directly

## 7. Verify Database Tables
When the app runs for the first time, Hibernate will create the tables automatically.
Login to MySQL and check:
````
USE expense_tracker;
SHOW TABLES;
````

We should see:
- user
- category
- expense
(and more, depending on the project’s progress)

## 8. Troubleshooting
❗ Common Issues
| Issue                    | Fix                                                      |
| ------------------------ | -------------------------------------------------------- |
| Cannot connect to MySQL  | Check username/password; ensure MySQL service is running |
| Port 8080 already in use | Change port: `server.port=8081`                          |
| Table not created        | Ensure `spring.jpa.hibernate.ddl-auto=update`            |
| Swagger not loading      | Visit `/swagger-ui/index.html` on Spring Boot 3          |

## 9. Future Notes (Phase 2 – JWT)
When authentication is added later, we will create more environment variables:
- JWT_SECRET
- JWT_EXPIRATION
- JWT_REFRESH_EXPIRATION
These will be set in our OS and accessed using:
````
  jwt.secret=${JWT_SECRET}
````