# 📘 FinSight – Personal Finance Tracker

FinSight is a Spring Boot–based personal finance tracking system designed to help users record their income, expenses, and categories, and view insights into their spending patterns. The goal of the project is to build a real-world, production-grade backend system that follows clean architecture, proper documentation, and industry best practices.

This project is also part of a portfolio-building and backend learning journey, covering:
- API design (OpenAPI/Swagger)
- Database modeling (MySQL)
- Clean layered architecture
- Version control (Git/GitHub)
- Spring Boot project structure
- REST API best practices

---

## 🎯 Project Goals

- Build a functional backend service for managing income, expenses, and categories
- Practice designing scalable REST APIs with proper validation, error handling, and documentation
- Implement a clean service-repository layered architecture
- Connect Spring Boot application with MySQL using JPA/Hibernate
- Learn Git workflow, versioning, and documentation practices
- Prepare a strong portfolio project for software developer interviews

---

## ⭐ Core Features (MVP)

- User registration and login (basic authentication)
- Add, update, delete, and list **Categories** — supports global (system-defined) and user-owned categories
- Manage **Transactions** (income and expense) with amount, date, category, and notes
- Transaction type (INCOME/EXPENSE) is derived from the linked category — not stored independently
- Monthly **Dashboard** showing total income, total expense, net balance, expense breakdown by category, and 5 most recent transactions

---

## 🚀 Tech Stack

### Backend
- Spring Boot 3.5.7
- Java 24
- Spring Web
- Spring Data JPA / Hibernate
- MySQL 9.4.0

### Tools
- IntelliJ IDEA
- Maven
- Swagger UI / OpenAPI (Springdoc)
- Git & GitHub
- MySQL Workbench
- Postman

---

## 📂 High-Level Architecture

FinSight follows a standard layered architecture:

```
controller → service → repository → database
                |
           dto & entity
                |
          exception handling
```

### Layers:
- **Controller Layer** – Exposes REST endpoints
- **Service Layer** – Contains business logic and enforces business rules
- **Repository Layer** – JPA repositories for DB access
- **Entity Layer** – JPA models mapped to MySQL tables
- **DTO Layer** – Models for request/response (separate from entities)
- **Exception Layer** – Centralized error handling (planned for Phase 3)
- **Config Layer** – Application configuration (SecurityConfig, etc.)

---

## 🗂 Folder Structure (Backend)

```
src/main/java/com/finsight
│
├── controller
├── service
│   └── impl
├── repository
├── entity
├── dto
├── exception
├── config
└── FinsightApplication.java
```

---

## 🔍 Current Status

- **Phase 1 — Complete ✅**: Planning, design, documentation, and project skeleton
- **Phase 2 — Implemented and partially tested ✅**: Core backend modules (User, Category, Transaction, Dashboard) implemented; User and Category modules tested via Postman; Transaction and Dashboard modules code-complete but not yet tested

See the main `README.md` for full phase-by-phase status and next steps.

---

## 📌 Author
**Nekhat Perveen**
Java backend development portfolio project
2025
