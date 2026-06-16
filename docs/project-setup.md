# FinSight – Project Setup Guide

This document explains how to install, configure, and run the backend locally — including MySQL setup, application configuration, Swagger UI, and environment variables.

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
Open a terminal or command prompt and run:
```
git clone <git-repo-url>
```
Git will create a folder with the same name as the repository. Navigate into it:
```
cd finsight
```

## 3. Database (MySQL) Setup

### 3.1 Create Database
Log into MySQL:
```
mysql -u root -p
```
Create the database:
```
CREATE DATABASE finsight_db;
```

## 4. Configure Application Settings

### 4.1 Edit `src/main/resources/application.yaml`

The project uses a YAML configuration file. Update the datasource credentials to match your local MySQL setup:

```yaml
spring:
  application:
    name: Finsight
  datasource:
    url: jdbc:mysql://localhost:3306/finsight_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root            # replace with your MySQL username
    password: admin123        # replace with your MySQL password
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update         # "update" creates/updates tables automatically based on entities
    show-sql: true              # shows SQL queries in console
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect

logging:
  level:
    root: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

# Swagger/OpenAPI config (Springdoc)
springdoc:
  api-docs:
    path: /api-docs            # endpoint for JSON API spec
  swagger-ui:
    path: /swagger-ui.html      # UI for testing endpoints

server:
  port: 8080
```

**Environment Variables (Recommended for credentials)**

Instead of storing the DB password directly in `application.yaml`, you can use an environment variable:

- macOS/Linux:
```
export DB_PASSWORD=yourpassword
```
- Windows PowerShell:
```
setx DB_PASSWORD "yourpassword"
```
Then reference it in `application.yaml`:
```yaml
spring:
  datasource:
    password: ${DB_PASSWORD}
```

## 5. Build and Run the Spring Boot App

### 5.1 Run with Maven
```
mvn spring-boot:run
```

### 5.2 Run from IntelliJ
- Open IntelliJ → "Run" → "Run FinsightApplication"
- The application will start on:
```
http://localhost:8080
```

## 6. Access Swagger UI

Once the application is running, open:
```
http://localhost:8080/swagger-ui.html
```
Here you'll see all API endpoints, request/response formats, and can test endpoints directly.

**Note:** Endpoints are nested under a user context (e.g. `/api/users/{userId}/categories`, `/api/users/{userId}/transactions`, `/api/users/{userId}/dashboard`). You'll need a valid `userId` (created via `/api/users/register`) to test most endpoints — see `api-contract.md` for full details.

## 7. Verify Database Tables

When the app runs for the first time, Hibernate will create the tables automatically (`spring.jpa.hibernate.ddl-auto=update`). Log into MySQL and check:
```
USE finsight_db;
SHOW TABLES;
```

You should see:
- `users`
- `categories`
- `transactions`

## 8. Troubleshooting — Common Issues

| Issue | Fix |
|-------|-----|
| Cannot connect to MySQL | Check username/password in `application.yaml`; ensure MySQL service is running |
| Port 8080 already in use | Change `server.port` in `application.yaml` |
| Tables not created | Confirm `spring.jpa.hibernate.ddl-auto=update` |
| Swagger not loading | Visit `/swagger-ui/index.html` (Spring Boot 3 default path) if `/swagger-ui.html` doesn't load |

## 9. Future Notes (Phase 3 — JWT)

When JWT-based authentication is implemented in Phase 3, additional environment variables will be introduced:
- `JWT_SECRET`
- `JWT_EXPIRATION`
- `JWT_REFRESH_EXPIRATION`

These will be set in the OS environment and referenced in `application.yaml`, e.g.:
```yaml
jwt:
  secret: ${JWT_SECRET}
```
