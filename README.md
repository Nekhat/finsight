# FinSight
**FinSight** — Personal finance & expense tracker with smart monthly insights and budget alerts.

---
## Table of Contents
- [Overview](#overview)
- [Phase 1 Status](#phase-1-status)
- [Phase 2 Status](#phase-2-status)
- [Key Design Decisions & Learnings](#key-design-decisions--learnings)
- [Documentation](#documentation)
- [Project Setup](#project-setup)
- [Future Enhancements](#future-enhancements)
- [Next Steps](#next-steps)

---

## Overview
This project is a personal finance management system that allows users to track income, expenses, and budgets, with insights and analytics to help manage money effectively.  
For detailed documentation, please refer to the [docs folder](./docs).

---

## Phase 1 Status
**Phase 1 (Planning, Design & Initial Setup) – Complete ✅**

Phase 1 included the following deliverables:

- Pick a project name & elevator pitch
- Define MVP feature list and nice-to-have features
- Sketch core user stories (8–12 short stories for MVP)
- Design the data model (ERD) and produce DDL SQL scripts
- Define API contract (OpenAPI style) with endpoints, requests, and responses
- Select tech stack, libraries, and dependencies
- Create GitHub repository & project skeleton (folders, README.md, .gitignore)
- Generate minimal Spring Boot app skeleton with local MySQL setup
- Complete documentation:
    - `overview.md`
    - `feature-list.md`
    - `user-stories.md`
    - `data-model.md`
    - `api-contract.md`
    - `tech-stack.md`
    - `project-setup.md`
    - `git-strategy.md`
    - `future-enhancements.md`

---

## Phase 2 Status
**Phase 2 (Core Backend Implementation) – Implemented and partially tested**

Phase 2 expanded the application from project skeleton (Phase 1) into a working backend with core financial-tracking modules.

### What was built

**User & Authentication**
- User entity, repository, service, and controller
- Signup API with email/password validation and uniqueness checks
- Login API with basic authentication (email/password verification)
- Unit tests for core user APIs

**Category Management**
- Category entity supporting both global (system-defined) and user-defined categories
- Full CRUD APIs: create, update (name only), delete, and fetch (global + user-specific)
- Business rules implemented:
  - Category type (INCOME/EXPENSE) is immutable after creation
  - Category names are unique per user (case-insensitive) and cannot clash with global categories
  - Categories cannot be deleted if linked transactions exist

**Transaction Management**
- Transaction entity with intent-based domain methods (no raw setters), JPA lifecycle callbacks for audit fields, and lazy relationships
- Type is derived from the linked category (income/expense)
- Validation rules: amount must be positive, date cannot be in the future, category must be global or owned by the user
- Immutable fields: category, type, user — only amount, date, and notes can be updated
- Full layered implementation: entity, DTOs, repository, service, and controller

**Dashboard / Reporting**
- Read-only dashboard endpoint (`GET /dashboard?userId={id}&month={m}&year={y}`)
- Aggregated via custom JPQL queries on the Transaction repository — no separate entity or table
- Returns: total income, total expense, net balance, expense breakdown by category, and the 5 most recent transactions

### Architecture & Engineering Decisions

- Followed a strict feature-branch Git workflow (`feature/user-api`, `feature/category-api`, `feature/dashboard-api`) with descriptive, conventional commit messages per layer
- All business rules and validation enforced in the service layer; controllers remain thin and delegate entirely
- Repository layer restricted to data access — no business logic
- Conscious, documented deferrals to keep Phase 2 scoped (see Tech Debt below) rather than over-engineering an MVP

### Testing Status

- **User and Category modules**: Manually tested end-to-end via Postman against a local sample database (signup, login, category CRUD) — test data created via SQL inserts and API calls
- **Transaction and Dashboard modules**: Implemented and code-complete, but not yet tested

### Tech Debt — Deferred to Phase 3

The following were intentionally deferred to keep Phase 2 focused on core functionality, and are documented for future implementation:

- Password hashing (BCrypt) — currently stored as plain text for development purposes
- JWT-based authentication and authorization (Spring Security)
- Bean validation annotations (`@NotBlank`, `@Email`, `@Valid`) across DTOs
- Custom exception classes (e.g. `UserAlreadyExistsException`, `InvalidCredentialsException`) and a global exception handler (`@ControllerAdvice`)
- DTO mapper classes (e.g. `UserMapper`) to clean up entity-to-DTO conversion
- `toString()`, `equals()`, and `hashCode()` overrides on entities
- Enum-based currency support (currently a plain `String` field)

## Key Design Decisions & Learnings

- **Transaction type is derived, not stored** — a transaction's income/expense type is always
  determined by its linked category, so storing it separately would risk inconsistency.
  Delegating via `getType()` enforces a single source of truth.

- **Intent-based domain methods over raw setters** — instead of `setAmount()`, the Transaction
  entity exposes `updateAmount()`, `updateDate()`, and `updateNotes()` — each with built-in
  validation. This means business rules live in the entity and cannot be bypassed by any caller.

- **Strict layer separation enforced by design** — controllers stay thin and delegate entirely
  to services; repositories handle only data access. All business rules (uniqueness checks,
  ownership validation, deletion guards) live exclusively in the service layer.

- **Deliberate, documented tech debt** — items like JWT authentication, BCrypt password hashing,
  and global exception handling were intentionally deferred to Phase 3 to keep Phase 2 focused
  on delivering working core functionality first. Every deferral is documented in the README and
  inline notes.

---

## Documentation
All the Phase 1 documentation is available in the [docs folder](./docs).  
Each file covers a different aspect of the project:

- **overview.md** – Project elevator pitch and summary
- **feature-list.md** – MVP features and nice-to-have items
- **user-stories.md** – Core user stories
- **data-model.md** – ERD and table definitions
- **api-contract.md** – API endpoints and OpenAPI-style request/response examples
- **tech-stack.md** – Selected technologies, libraries, and dependencies
- **project-setup.md** – Steps to run backend locally, MySQL setup, Swagger, and environment variables
- **git-strategy.md** – Branching strategy, commit naming, pull request workflow
- **future-enhancements.md** – Planned features and ideas for v2

---

## Project Setup
For instructions on setting up the project locally, see [project-setup.md](./docs/project-setup.md).

---

## Future Enhancements
Ideas for v2 and advanced features are documented in [future-enhancements.md](./docs/future-enhancements.md).

---

## Next Steps / Phase 3
Phase 3 will focus on:
- Completing testing for Transaction and Dashboard modules
- Implementing security (password hashing, JWT-based authentication & authorization)
- Adding validation annotations, custom exceptions, and global exception handling
- Adding seed/test data for easier local setup

Phase 3 status will be tracked separately once work begins.

---

