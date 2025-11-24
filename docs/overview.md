# 📘 Finsight – Personal Finance Tracker

Finsight is a Spring Boot–based personal finance tracking system designed to help users record their income, expenses, categories, and view insights into their spending patterns. The goal of the project is to build a real-world, production-grade backend system that follows clean architecture, proper documentation, and industry best practices.

This project is also part of a portfolio-building and backend learning journey, covering:
- API design (OpenAPI/Swagger)
- Database modeling (MySQL)
- Clean layered architecture
- Version control (Git/GitHub)
- Spring Boot project structure
- REST API best practices

---
## 🎯 Project Goals

- Build a functional backend service for managing income, expenses, and categories.
- Practice designing scalable REST APIs with proper validation, error handling, and documentation.
- Implement a clean service-repository layered architecture.
- Connect Spring Boot application with MySQL using JPA/Hibernate.
- Learn Git workflow, versioning, and documentation practices.
- Prepare a strong portfolio project for software developer interviews.
---

## ⭐ Core Features (MVP)

- Add, update, delete, and list **Categories**
- Manage **Income entries** with amount, date, category, and notes
- Manage **Expense entries**
- View insights such as:
    - Total income
    - Total expenses
    - Balance
    - Filters by date range and category

---

## 🚀 Tech Stack

### **Backend**
- Spring Boot 3+
- Java 17+
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
### **Tools**
- IntelliJ IDEA
- Maven
- Swagger UI / OpenAPI
- Git & GitHub
- MySQL Workbench

---

## 📂 High-Level Architecture

Finsight follows a standard layered architecture:
controller → service → repository → database
|
dto & mappers
|
exception handling

### Layers:
- **Controller Layer** – Exposes REST endpoints
- **Service Layer** – Contains business logic
- **Repository Layer** – JPA repositories for DB access
- **Entity Layer** – JPA models mapped to MySQL tables
- **DTO Layer** – Models for request/response
- **Exception Layer** – Centralized error handling

---

## 🗂 Folder Structure (Backend)
src/main/java/com/finsight
│
├── controller
├── service
│ └── impl
├── repository
├── entity
├── dto
├── exception
├── config
└── FinsightApplication.java

## 🔍 Current Status (Phase 1 Completed)

The following items are completed:

- Project name + description
- Feature breakdown (MVP + future improvements)
- User stories
- Database design + DDL + initial data
- API contract for all endpoints
- Spring Boot project skeleton using Spring Initializr
- MySQL connection + Swagger validation
- GitHub repository setup + initial commit
- Documentation folder structure

Next phases will include implementing all endpoints, adding tests, and building insights APIs.

---

## 📌 Author
**Nekhat Perveen**  
Java backend development portfolio project  
2025