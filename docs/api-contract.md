# FinSight API Contract

This document reflects the API surface as actually implemented through Phase 2. It supersedes the original Phase 1 API contract, which was based on a flat `/api/v1` + JWT design that was not the path Phase 2 ultimately took.

## 1. Base URL
`http://localhost:8080/api`

## 2. Authentication Overview (Current — Phase 2)

- Registration and login use **basic email/password checks** — no token is issued yet.
- All endpoints are currently open (no Spring Security enforcement on requests beyond what's noted).
- **JWT-based authentication and authorization is planned for Phase 3** (see README "Next Steps").

## 3. Resource Structure

Most resources are nested under a user context:

```
/api/users/{userId}/categories
/api/users/{userId}/categories/{categoryId}
/api/users/{userId}/transactions
/api/users/{userId}/transactions/{transactionId}
/api/users/{userId}/dashboard
```

If `{userId}` does not correspond to an existing user, endpoints return an error (currently a generic exception — custom exception handling is planned for Phase 3).

---

## 4. User Endpoints

### 4.1 Register
- **Endpoint:** `/api/users/register`
- **Method:** POST
- **Request Body:**
```json
{
  "name": "Tom",
  "email": "tom@mail.com",
  "password": "tom201"
}
```
- **Success Response (201 Created):**
```json
{
  "id": 1,
  "name": "Tom",
  "email": "tom@mail.com"
}
```
- **Notes:** `preferredCurrency` defaults to `"USD"` on the entity but is not returned in the response — `UserResponse` currently includes only `id`, `name`, and `email`. Password is currently stored as plain text — hashing (BCrypt) is planned for Phase 3.

### 4.2 Login
- **Endpoint:** `/api/users/login`
- **Method:** POST
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
  "email": "tom@mail.com"
}
```
- **Notes:** No token is returned. JWT-based login response is planned for Phase 3.

---

## 5. Category Endpoints

All category endpoints are scoped to a user: `/api/users/{userId}/categories`

### 5.1 Create Category
- **Endpoint:** `/api/users/{userId}/categories`
- **Method:** POST
- **Request Body:**
```json
{
  "name": "Gifts",
  "type": "EXPENSE"
}
```
- **Success Response (201 Created):**
```json
{
  "id": 12,
  "name": "Gifts",
  "type": "EXPENSE",
  "global": false
}
```
- **Notes:** Creates a user-owned category. Global (system-default) categories are not created via this endpoint.

### 5.2 Get All Categories
- **Endpoint:** `/api/users/{userId}/categories`
- **Method:** GET
- **Success Response (200 OK):** Returns all global categories plus the user's own categories.
```json
[
  { "id": 1, "name": "Food", "type": "EXPENSE", "global": true },
  { "id": 12, "name": "Gifts", "type": "EXPENSE", "global": false }
]
```

### 5.3 Get Category by ID
- **Endpoint:** `/api/users/{userId}/categories/{categoryId}`
- **Method:** GET
- **Success Response (200 OK):**
```json
{ "id": 12, "name": "Gifts", "type": "EXPENSE", "global": false }
```

### 5.4 Update Category
- **Endpoint:** `/api/users/{userId}/categories/{categoryId}`
- **Method:** PUT
- **Request Body:** Only `name` can be updated — `type` is immutable.
```json
{ "name": "Birthday Gifts" }
```
- **Success Response (200 OK):**
```json
{ "id": 12, "name": "Birthday Gifts", "type": "EXPENSE", "global": false }
```

### 5.5 Delete Category
- **Endpoint:** `/api/users/{userId}/categories/{categoryId}`
- **Method:** DELETE
- **Success Response:** 204 No Content
- **Notes:** Fails if any transactions reference this category (enforced in the service layer).

---

## 6. Transaction Endpoints

All transaction endpoints are scoped to a user: `/api/users/{userId}/transactions`

### 6.1 Create Transaction
- **Endpoint:** `/api/users/{userId}/transactions`
- **Method:** POST
- **Request Body:**
```json
{
  "amount": 150.00,
  "categoryId": 6,
  "date": "2025-10-07",
  "notes": "Birthday gift"
}
```
- **Success Response (201 Created):** Returns the created transaction, including the derived `type` from its category.
- **Notes:** `type` is not part of the request — it is derived from the category's type. `amount` must be greater than zero; `date` cannot be in the future.

### 6.2 Get All Transactions
- **Endpoint:** `/api/users/{userId}/transactions`
- **Method:** GET
- **Success Response (200 OK):** Returns all transactions for the user.

### 6.3 Update Transaction
- **Endpoint:** `/api/users/{userId}/transactions/{transactionId}`
- **Method:** PUT
- **Request Body:** Only `amount`, `date`, and `notes` can be updated.
```json
{
  "amount": 175.00,
  "date": "2025-10-08",
  "notes": "Birthday gift (updated)"
}
```
- **Notes:** `category`, `type`, and `user` are immutable after creation and cannot be changed via this endpoint.

### 6.4 Delete Transaction
- **Endpoint:** `/api/users/{userId}/transactions/{transactionId}`
- **Method:** DELETE
- **Success Response:** 204 No Content

---

## 7. Dashboard Endpoint

### 7.1 Get Dashboard
- **Endpoint:** `/api/users/{userId}/dashboard`
- **Method:** GET
- **Query Parameters:** `month`, `year`
- **Example:** `/api/users/1/dashboard?month=10&year=2025`
- **Success Response (200 OK):**
```json
{
  "totalIncome": 4500.00,
  "totalExpense": 3200.00,
  "netBalance": 1300.00,
  "expenseByCategory": [
    { "categoryId": 6, "categoryName": "Gifts", "totalAmount": 150.00 }
  ],
  "recentTransactions": [
    {
      "transactionId": 101,
      "amount": 120.00,
      "date": "2025-10-07",
      "type": "EXPENSE",
      "categoryName": "Gifts"
    }
  ]
}
```
- **Notes:** This single endpoint replaces the separate `/dashboard/summary` and `/dashboard/last5transactions` endpoints originally planned in Phase 1. All dashboard data is aggregated via JPQL queries on the Transaction repository — there is no separate Dashboard entity or table.

---

## 8. Planned for Phase 3 (Not Yet Implemented)

- JWT issuance on login and `Authorization: Bearer <token>` enforcement on protected endpoints
- Custom exception types and consistent error response format via a global exception handler (`@ControllerAdvice`)
- Bean validation error responses (currently only `@Valid` is applied on Transaction DTOs)
- User update (`PUT /api/users/{userId}`) and delete endpoints — not yet implemented

```