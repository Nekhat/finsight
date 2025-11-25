# Finsight API Contract

## 1. Base URL
http://localhost:8080/api/v1

## 2. Authentication Overview
- Token-based authentication (JWT) is used.
- Signup returns basic user info.
- Login returns a JWT token to be used in the `Authorization` header for all protected endpoints.
- Logout is currently client-side; server-side logout endpoint available for future enhancement.

**Authorization Header Format:**
Authorization: Bearer <JWT_TOKEN>
---

## 3. Auth Endpoints

### 3.1 Signup
- **Endpoint:** `/auth/signup`
- **Method:** POST
- **Description:** Registers a new user.
- **Request Headers:** `Content-Type: application/json`
- **Request Body:**
```json
{
  "name": "Tom",
  "email": "tom@mail.com",
  "password": "tom201",
  "preferred_currency": "USD"
}
```
- **Success Response (201 Created):**
```json
{
"id": 1,
"name": "Tom",
"email": "tom@mail.com",
"preferred_currency": "USD",
"created_at": "2025-10-07T15:32:10Z"
}
```
- **Error Responses:** 400 Bad Request, 409 Conflict, 500 Internal Server Error
- **Notes:** Password is hashed before storing. Default currency is USD if not provided.

### 3.2 Login
- **Endpoint:** `/auth/login`
- **Method:** POST
- **Description:** Logs in an existing user, returns JWT token.
- **Request Body:**
```json
{
  "email": "tom@mail.com",
  "password": "tom201"
}
```
- **Success Response (200 OK):**
```json
{
"id": 1,
"name": "Tom",
"email": "tom@mail.com",
"preferred_currency": "USD",
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
"expires_in": 3600
}
```
### 3.3 Logout
- **Endpoint:** `/auth/logout`
- **Method:** POST
- **Description:** Intended for future implementation to invalidate JWT token.

## 4. User Endpoints
### 4.1 Get User
- **Endpoint:** `/users/{id}`
- **Method:** GET
- **Headers:** Authorization: Bearer <JWT_TOKEN>
- **Path Params:** id - user ID
- **Success Response:**
```json
{
  "id": 1,
  "name": "Tom",
  "email": "tom@mail.com",
  "preferred_currency": "USD",
  "created_at": "2025-10-07T15:32:10Z"
}
```
- **Errors:** 401 Unauthorized, 403 Forbidden, 404 Not Found
### 4.2 Update User
- **Endpoint:** `/users/{id}`
- **Method:** PUT
- **Headers:** Authorization: Bearer <JWT_TOKEN>
- **Request Body:** Partial fields allowed.
```json
{
  "name": "Tom Updated",
  "email": "tom@mail.com",
  "preferred_currency": "USD"
}
```
- **Success Response:** Returns updated user info.
- **Errors:** 401 Unauthorized, 403 Forbidden, 400/409/500

### 4.3 Delete User
- **Endpoint:** `/users/{id}`
- **Method:** DELETE
- **Headers:** Authorization: Bearer <JWT_TOKEN>
- **Success Response:**
```json
{
"message": "User account deleted successfully.",
"timestamp": "2025-10-22T12:45:30Z"
}
```
## 5. Category Endpoints
### 5.1 Get All Categories
- **Endpoint:** `/categories`
- **Method:** GET
- **Headers:** Authorization: Bearer <JWT_TOKEN>
- **Success Response:**
```json
 [
  { "id": 1, "name": "Salary", "type": "income" },
  { "id": 2, "name": "Groceries", "type": "expense" }
 ]
```
### 5.2 Get Category by ID
- **Endpoint:** `/categories/{id}`
- **Method:** GET

### 5.3 Create Category
- **Endpoint:** `/categories`
- **Method:** POST
- **Request Body:**
```json
{
"name": "Gifts",
"type": "expense"
}
```
- **Success Response:** Returns created category info.
### 5.4 Update Category
- **Endpoint:** `/categories/{id}`
- **Method:** PUT
- **Notes:** Only user-created categories can be updated.

### 5.5 Delete Category
- **Endpoint:** `/categories/{id}`
- **Method:** DELETE
- **Notes:** Default categories cannot be deleted; user can delete only own categories.

## 6. Transaction Endpoints
### 6.1 Get All Transactions
- **Endpoint:** `/transactions`
- **Method:** GET
- **Query Parameters:** startDate, endDate, category, type, limit
- **Success Response:** Returns list of transactions.

### 6.2 Get Transaction by ID
- **Endpoint:** `/transactions/{id}`
- **Method:** GET

### 6.3 Create Transaction
- **Endpoint:** `/transactions`
- **Method:** POST
- **Request Body:**
```json
{
  "amount": 150.00,
  "category_id": 6,
  "description": "Birthday gift",
  "txn_date": "2025-10-07"
}
```
### 6.4 Update Transaction
- **Endpoint:** `/transactions/{id}`
- **Method:** PUT

### 6.5 Delete Transaction
- **Endpoint:** `/transactions/{id}`
- **Method:** DELETE

## 7. Dashboard Endpoints
### 7.1 Summary
- **Endpoint:** `/dashboard/summary`
- **Method:** GET
- **Success Response:**
```json
{
  "total_income": 4500.00,
  "total_expense": 3200.00,
  "balance": 1300.00
}
```
### 7.2 Last 5 Transactions
- **Endpoint:** `/dashboard/last5transactions`
- **Method:** GET
- **Success Response:** Returns latest 5 transactions for the user.
```json
[
  {
    "id": 101,
    "amount": 120.00,
    "category_id": 6,
    "category_name": "Gifts",
    "type": "expense",
    "description": "Birthday gift",
    "txn_date": "2025-10-07"
  },
  {
    "id": 100,
    "amount": 2500.00,
    "category_id": 3,
    "category_name": "Salary",
    "type": "income",
    "description": "Monthly salary",
    "txn_date": "2025-10-05"
  }
]
```