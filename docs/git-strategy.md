# Finsight – Git Strategy

This document describes the version control workflow for the FinSight – Personal Expense Tracker project.
It ensures clean commits, predictable branching, and smooth collaboration.

## 1. Branching Strategy
We follow a simple and effective Git branching model:

**Main Branch**
 * main

        * Always contains stable, production-ready code.
        * Protected branch — do NOT commit directly.
        * All merges happen via Pull Requests (PRs).

**Feature Branches**

Each new change must be done in a feature branch.

**Naming Convention:**
````
feature/<short-description>
````
**Examples:**
- feature/user-authentication
- feature/transaction-api
- feature/dashboard-charts

**Bug Fix Branches**
For fixes after merging.
````
fix/<issue-description>
````

Examples:
- fix/login-validation
- fix/category-service-exception

**Documentation Branches**
````
docs/<topic>
````

Example:
- docs/architecture-update

## 2. Commit Message Conventions
We follow the Conventional Commits pattern:
**Format:**
````
<type>: <short description>
````

**Types to Use**
| Type         | Use Case                                  |
| ------------ | ----------------------------------------- |
| **feat**     | New feature added                         |
| **fix**      | Bug fix                                   |
| **docs**     | Documentation changes                     |
| **refactor** | Code restructuring without logic change   |
| **style**    | Formatting, removing unused imports, etc. |
| **test**     | Adding or updating tests                  |
| **chore**    | Build, config, dependency updates         |

**Examples**
````
feat: add endpoint for creating transactions
fix: resolve null pointer exception in category service
docs: add API contract for user module
refactor: simplify transaction mapper and DTOs
````

## 3. Pull Request Workflow
To ensure quality and consistency, all changes must follow this process:

**Step 1 — Create Feature Branch**
``````
git checkout -b feature/<name>

``````
**Step 2 — Commit Changes Frequently**
- Small, meaningful commits.
- Use Conventional Commit messages.

**Step 3 — Push Branch**
``````
git push origin feature/<name>
``````
**Step 4 — Open a Pull Request (PR)**
Include:
-Clear summary of the change
-Screenshots (if any UI work)
-Linked issues (if applicable)

**Step 5 — Review & Approvals**
- Check formatting, naming, unused code, and logic correctness.
- Resolve comments before merging.

**Step 6 — Squash & Merge**
- Squash keeps main clean with one commit per feature.
- Delete the branch after merge.

## 4. Branch Naming Summary
| Type     | Example                          |
| -------- | -------------------------------- |
| Feature  | `feature/add-budget-module`      |
| Fix      | `fix/incorrect-total-balance`    |
| Docs     | `docs/update-readme`             |
| Refactor | `refactor/service-layer-cleanup` |


## 5. Notes
- Never commit directly to main.
- Always pull latest changes before starting new work:
``````
git pull origin main
``````
- Keep feature branches small to avoid merge conflicts.


