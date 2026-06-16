# Finsight – Future Enhancements

This document outlines features, improvements, and ideas planned for future versions of the application, beyond the immediate Phase 3 roadmap (see main README).

## 1. Near-Term Backlog (Post Phase 3)

- **Mini Frontend** – A simple UI (React or Thymeleaf) with signup/login forms and a dashboard page showing totals and basic charts
- **Seed/Test Data & Setup Walkthrough** – Pre-populated sample data and a documented walkthrough of the core user journey (signup → login → add categories → add transactions → view dashboard), useful for demos and onboarding

## 2. Planned Features

- **Recurring Transactions** – Ability to mark transactions as recurring (e.g., monthly rent, subscriptions)
- **Budgeting & Alerts** – Set monthly budgets per category and receive notifications when close to limits
- **Multi-currency Support** – Automatic conversion and reporting in a preferred currency
- **Advanced Dashboard Visualizations** – Charts and trends over time (income vs. expense, category-wise breakdowns) building on the data already returned by the Phase 2 dashboard endpoint
- **Export & Import** – Export transactions to CSV/PDF and import from external sources

## 3. V2 / Long-Term Ideas

- **User Roles & Admin Panel** – Admin users to manage system-wide settings, global categories, and users
- **Collaborative Accounts** – Shared accounts for families or teams with controlled access
- **AI-based Insights** – Intelligent suggestions on spending, savings, and investment opportunities (e.g., top spending categories, overspending trends)
- **Mobile App / PWA** – Cross-platform mobile app with offline capabilities
- **Two-Factor Authentication (2FA)** – Additional login security layer

## 4. Ongoing Quality Improvements

These apply across all phases and aren't tied to a specific release:

- **Performance Optimization** – Indexing, caching, and query optimization for larger datasets
- **Expanded Test Coverage** – Broader unit and integration tests across all modules
- **Code Quality & Documentation** – API documentation and developer guides as the codebase grows
