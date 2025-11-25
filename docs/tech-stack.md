# Finsight – Tech Stack & Dependencies

This document describes the technologies, tools, and libraries used in the **Finsight – Personal Expense Tracker** project. It also includes the Maven dependencies currently used and the dependencies planned for Phase 2 enhancements (authentication, JWT, security).

---

## 1. Backend Technologies

| Layer | Technology | Purpose |
|-------|-----------|---------|
| **Language** | Java 24 | Core backend programming language |
| **Framework** | Spring Boot 3.5.7 | Rapid development, dependency management, auto-configuration |
| **Database** | MySQL 9.4.0 | Relational database for users, categories & transactions |
| **ORM** | Spring Data JPA (Hibernate) | Simplifies DB operations using repositories and entity mapping |
| **Build Tool** | Maven | Project build and dependency management |
| **API Documentation** | Springdoc OpenAPI (Swagger UI) | Interactive documentation for REST APIs |
| **Validation** | Jakarta Validation | To validate request DTOs (amount > 0, date valid, etc.) |
| **Logging** | SLF4J + Logback | Logging, debugging, monitoring |
| **Testing** | JUnit 5, Mockito | Unit + Integration testing framework |

---

## 2. Tools & Development Environment

| Tool | Purpose |
|------|---------|
| **IntelliJ IDEA** | Development & debugging |
| **Git + GitHub** | Version control and repo hosting |
| **Postman** | API testing client |
| **MySQL Workbench** | Database schema creation and query testing |

---

## 3. Spring Boot Dependencies (pom.xml)

Below are the dependencies included in the initial version of the project.

### **Core Dependencies**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
### **Database Driver**
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```
### **Validation**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
### **Lombok**
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```
### **Swagger / OpenAPI**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.0</version>
</dependency>
```
### **Testing**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-test</artifactId>
<scope>test</scope>
</dependency>
```
## 4. Phase 2 Enhancements (Later)
When we add authentication, the following dependencies will be introduced.
### 4.1 **Spring Security**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
### 4.2 **JWT Authentication (JJWT)**
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```
## 5. Summary
The Finsight project uses a modern, production-ready tech stack with:

- Spring Boot for backend development
- MySQL as the relational database
- Spring Data JPA for ORM
- Swagger for API documentation
- JUnit/Mockito for testing
- GitHub for version control
- Optional future integration with Spring Security + JWT

This stack ensures clean, scalable, maintainable architecture suitable for a real-world portfolio project.