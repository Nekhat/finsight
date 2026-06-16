# Feature List – Finsight (Personal Expense Tracker)

This document captures all functional and non-functional features planned for Finsight.  
Features are organized into **MVP (Phase 1)** and **Nice-to-Have (Phase 2)**.  
These will drive API design, database schema, user stories, and development planning.

---

## 📌 1. MVP Features (Phase 1)
These are the must-have features required to deliver the first working version of Finsight.

### **1.1 User Management**
- **Signup** using email, password, and name
- **Login** using email and password
- Maintain a **user profile**:
   - Name
   - Email
   - Preferred currency
- Session persistence (remain logged in during active session)
- Basic authentication (JWT-based authentication planned for Phase 3)

---
### **1.2 Expense & Income Tracking**
#### Transaction CRUD
- Add new **expense** or **income**
- Edit/update transaction details
- Delete a transaction
- Transaction fields include:
   - Amount
   - Type (Income / Expense)
   - Date
   - Category
   - Notes (optional)
   - User ID (owner)

#### Categories
- Assign category (e.g., Food, Rent, Transport, Shopping)
- Built-in default categories
- Ability to create/update/delete categories (optional MVP)

#### Validation Rules
- Amount must be **greater than 0**
- Date must be valid
- Category required
- Type must be income or expense
- Notes optional but sanitized

---

### **1.3 Dashboard / Overview**
- Display **total income**
- Display **total expenses**
- Display **current balance** (income – expenses)
- Show **last 5 transactions**
- Charts:
   - Pie chart: expenses by category
   - Bar chart: monthly income vs. expenses

---

### **1.4 Data Persistence**
- Store user + transactions in SQL database (MySQL/PostgreSQL)
- Proper relational schema with FK constraints
- CRUD APIs for:
   - Users
   - Transactions
   - Categories
- Basic DTO mapping using:
   - Request DTO
   - Response DTO

---

### **1.5 API Testing & Basic UI**
- All APIs tested through Postman
- Optional simple frontend for:
   - Dashboard
   - Adding transactions
   - Viewing summary
- Technologies:
   - React OR Thymeleaf (optional MVP)

---

## 📌 2. Nice-to-Have Features (Phase 2)
These enhance user experience but are not required for the first release.

### **2.1 Budgeting & Alerts**
- Set monthly budgets per category
- Notifications when:
   - User reaches 80% of budget
   - User exceeds the budget
- Dashboard highlight for overspending categories

---

### **2.2 Smart Insights**
- Identify spending trends (weekly/monthly)
- Highlight top 3 spending categories per month
- Predict next month’s spending using history
- Monthly insights summary panel

---
### **2.3 Export / Import**
- Export transactions as:
   - CSV
   - PDF monthly reports
- Import bank statements (CSV format)
- Automatic category mapping (advanced Phase 2.1)

---

### **2.4 Recurring Transactions**
- Create recurring income/expense:
   - Salary
   - Rent
   - Insurance
   - Subscriptions
- Auto-generate recurring entries monthly

---

### **2.5 Enhanced Security**
- Password hashing & salting (BCrypt)
- JWT authentication
- Optional:
   - 2-Factor Authentication (email-based OTP)
   - Session refresh tokens

---

### **2.6 User Experience Upgrades**
- Fully mobile-responsive UI
- Interactive charts with filters:
   - Category
   - Month
   - Date range
   - Income vs. Expense toggle
- Dark mode

---

## 📌 3. Technical / Non-Functional Features

### **3.1 API Documentation**
- Swagger/OpenAPI 3.0 auto-generation
- Example requests & responses

### **3.2 Logging**
- Request logs
- Error logs
- Transaction logs (create/update/delete)

### **3.3 Error Handling**
- Global exception handler
- Uniform error format
- Validation error messages

### **3.4 Performance Considerations**
- Indexes for amount, date, user_id
- Pagination for transaction lists

### **3.5 Environment Setup**
- Profiles:
   - local
   - dev
   - prod (future)
- Environment variables for:
   - DB credentials
   - JWT secret (Phase 2)

---

## 📌 4. Feature Dependencies
- **Dashboard** depends on:
   - Transactions
   - Categories
   - User currency preference

- **Budgeting** depends on:
   - Categories
   - Monthly transaction totals

- **Smart Insights** depends on:
   - Historical transaction data

- **Recurring transactions** depend on:
   - Scheduled job or cron-like mechanism

---

## 📌 5. Out of Scope (Phase 3 / Future)
- Mobile app (iOS/Android)
- AI-based financial recommendations
- Bank account integration APIs
- Multi-currency auto-conversion
- Investment tracking

---

## 📌 6. Versioning Notes
- MVP: **Version 1.0**
- Nice-to-Have Enhancements: **Versions 1.1 – 1.5**
- Smart financial insights & automation: **2.0+**

---

**This feature list defines the full scope of Finsight and will guide design, development, and API building.**
