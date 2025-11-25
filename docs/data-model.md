# Finsight — Data Model Documentation

## 1. Overview
This document describes the core data model for Finsight, including entities, relationships, database design (DDL), and sample data.

---

## 2. Core Entities

| Entity      | Description |
|------------|-------------|
| User       | Stores login and profile information (name, email, password, preferred currency) |
| Transaction| Stores both income and expense transactions |
| Category   | Stores transaction categories (Food, Rent, Salary, etc.) |
| Currency   | Optional: handled via `preferred_currency` in User entity for MVP |

---

## 3. Relationships

- **User ↔ Transaction**: One user can have multiple transactions (1:N)
- **Transaction ↔ Category**: Each transaction belongs to one category; each category can have multiple transactions (1:N)

---

## 4. ERD (Entity-Relationship Diagram)
User

id (PK)
name
email (unique)
password
preferred_currency

|
| 1 --- N
|
Transaction

id (PK)
user_id (FK → User.id)
category_id (FK → Category.id)
amount
type (ENUM: income/expense)
txn_date
note

|
| N --- 1
|
Category

id (PK)
name (unique)
type (ENUM: income/expense/both)


---

## 5. SQL DDL (CREATE Scripts)

```sql
-- Create database
CREATE DATABASE finsight_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE finsight_db;

-- USERS table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- plain text for testing, bcrypt recommended for production
    preferred_currency VARCHAR(10) DEFAULT 'USD',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- CATEGORIES table
CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NULL,
    name VARCHAR(100) NOT NULL,
    type ENUM('income','expense') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_categories_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL,
    UNIQUE KEY uq_user_category_name (user_id, name)
);

-- TRANSACTIONS table
CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    amount DECIMAL(12,2) NOT NULL CHECK (amount > 0),
    txn_date DATE NOT NULL,
    note VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_transactions_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_transactions_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE RESTRICT
);

-- Indexes
CREATE INDEX idx_transactions_user_date ON transactions (user_id, txn_date DESC);
CREATE INDEX idx_transactions_category ON transactions (category_id);
CREATE INDEX idx_categories_user ON categories (user_id);
```
---

## 6. Sample Data (Initial Population)
-- Users
INSERT INTO users (name, email, password, preferred_currency) 
VALUES 
('Alice', 'alice@mail.com', 'alice123', 'USD'),
('Bob', 'bob@mail.com', 'bobSecure@99', 'EUR'),
('Charlie', 'charlie@mail.com', 'charliePass', 'INR');

-- System Categories (user_id = NULL)
INSERT INTO categories (user_id, name, type)
VALUES
(NULL, 'Food', 'expense'),
(NULL, 'Rent', 'expense'),
(NULL, 'Utilities', 'expense'),
(NULL, 'Transport', 'expense'),
(NULL, 'Entertainment', 'expense'),
(NULL, 'Health', 'expense'),
(NULL, 'Education', 'expense'),
(NULL, 'Salary', 'income'),
(NULL, 'Investment', 'income'),
(NULL, 'Miscellaneous', 'expense');

-- User-created category example
INSERT INTO categories (user_id, name, type) VALUES (1, 'Gifts', 'expense');

-- Transactions
INSERT INTO transactions (user_id, category_id, amount, txn_date, note)
VALUES
(1, 1, 120.50, '2025-09-01', 'Groceries'),
(1, 2, 1500.00, '2025-09-03', 'Monthly rent'),
(1, 8, 3000.00, '2025-09-05', 'September salary'),
(1, 5, 40.00, '2025-09-07', 'Movie tickets'),
(2, 1, 200.75, '2025-09-02', 'Groceries'),
(2, 4, 80.00, '2025-09-06', 'Taxi fare'),
(2, 8, 4500.00, '2025-09-08', 'Salary'),
(2, 9, 250.00, '2025-09-10', 'Mutual fund dividend'),
(3, 3, 100.00, '2025-09-04', 'Electricity bill'),
(3, 10, 30.00, '2025-09-09', 'Miscellaneous'),
(3, 8, 2800.00, '2025-09-05', 'Salary');


---

## 7. Notes

Default categories have user_id = NULL for system-wide defaults.

Unique index (user_id, name) ensures no duplicate category names per user.

ON DELETE RESTRICT prevents deletion of categories used in transactions.

Passwords should be hashed using bcrypt when integrating Spring Security; plain text is for testing only.

Indexes optimize queries for dashboards and reports.

