# FinSight — Data Model Documentation

## 1. Overview
This document describes the core data model for FinSight as actually implemented through Phase 2, including entities, relationships, and design notes. It supersedes the original Phase 1 data model, which has since evolved — most notably around category ownership and how transaction type is derived.

---

## 2. Core Entities

| Entity | Description |
|--------|-------------|
| User | Stores login and profile information (name, email, password, preferred currency) |
| Category | Stores transaction categories. Can be **global** (system-defined, shared by all users) or **user-owned** |
| Transaction | Stores both income and expense transactions. Type is **derived** from its linked category, not stored independently |

*(A separate `Currency` entity was considered in Phase 1 but was not implemented — `preferredCurrency` remains a simple string field on `User`.)*

---

## 3. Relationships

- **User ↔ Transaction**: One user has many transactions (1:N)
- **User ↔ Category**: One user can own many categories (1:N) — categories with `user = null` are global/system-defined and shared across all users
- **Category ↔ Transaction**: Each transaction belongs to one category; a category can be linked to many transactions (1:N)

---

## 4. Entity Details

### User
| Field | Type | Notes |
|-------|------|-------|
| id | Integer (PK) | Auto-generated (IDENTITY) |
| name | String | Required |
| email | String | Required, unique |
| password | String | Required. Currently stored as plain text — BCrypt hashing planned for Phase 3 |
| preferredCurrency | String | Defaults to `"USD"` |
| createdAt | Instant | Set on creation, immutable |

### Category
| Field | Type | Notes |
|-------|------|-------|
| id | Integer (PK) | Auto-generated (IDENTITY) |
| name | String | Required |
| type | CategoryType (enum: `INCOME`, `EXPENSE`) | Required, **immutable after creation** |
| isGlobal | Boolean | `true` = system-defined category visible to all users; `false` = user-owned |
| user | User (FK, nullable) | `null` for global categories; set for user-owned categories |

**Business rules:**
- Category names must be unique per user (case-insensitive), and must not clash with global category names
- `type` cannot be changed after creation — only `name` is updatable
- A category cannot be deleted if any transactions reference it

### Transaction
| Field | Type | Notes |
|-------|------|-------|
| id | Integer (PK) | Auto-generated (IDENTITY) |
| amount | BigDecimal(12,2) | Required, must be greater than 0 (enforced in constructor and update methods) |
| date | LocalDate | Required, cannot be in the future |
| notes | String (max 500) | Optional |
| user | User (FK) | Required, immutable after creation |
| category | Category (FK) | Required, immutable after creation |
| createdAt | LocalDateTime | Set via `@PrePersist`, immutable |
| updatedAt | LocalDateTime | Set via `@PrePersist` and `@PreUpdate` |

**Derived field:**
- `type` (INCOME/EXPENSE) is **not a column** — it's computed via `getType()`, which returns `category.getType()`. A transaction's type always matches its category's type.

**Mutability rules:**
- Updatable: `amount`, `date`, `notes` (via dedicated `updateAmount()`, `updateDate()`, `updateNotes()` methods with validation)
- Immutable after creation: `category`, `user`, and (by extension) `type`

---

## 5. Entity-Relationship Summary

```
User
 ├── id (PK)
 ├── name, email (unique), password
 ├── preferredCurrency (default "USD")
 └── createdAt

      │ 1
      │
      │ N
Category
 ├── id (PK)
 ├── name, type (INCOME/EXPENSE, immutable)
 ├── isGlobal (true = system-wide, false = user-owned)
 └── user (FK, nullable — null when isGlobal = true)

      │ 1
      │
      │ N
Transaction
 ├── id (PK)
 ├── amount, date, notes
 ├── user (FK, immutable)
 ├── category (FK, immutable)
 ├── type — DERIVED from category.type (not stored)
 └── createdAt, updatedAt
```

---

## 6. Notes

- Global categories (`isGlobal = true`, `user = null`) are intended to be seeded as system defaults (e.g., Food, Rent, Salary) — a formal seed script is planned for Phase 3 (see "Seed/Test Data" in README Next Steps)
- Password hashing, JWT, and full Spring Security integration are planned for Phase 3 — passwords are currently plain text for development purposes only
- Multi-currency conversion logic is not implemented — `preferredCurrency` is stored but not yet used in calculations; full multi-currency support remains a future enhancement (see `future-enhancements.md`)
- The original Phase 1 SQL DDL (separate `CREATE TABLE` scripts with `txn_date`, `category_id` snake_case columns) reflected an earlier design and does not match the current JPA entity mappings. Schema is currently managed via JPA/Hibernate auto-generation during development; a maintained DDL script can be added in Phase 3 alongside seed data
