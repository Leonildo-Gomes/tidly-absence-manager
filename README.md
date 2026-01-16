# Tidly | SaaS Absence Management (Norway)

![Java 21](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot 4](https://img.shields.io/badge/Spring_Boot-4.0-green?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql)
![Next.js 16](https://img.shields.io/badge/Next.js-16-black?style=for-the-badge&logo=next.js)

> **A robust, multi-tenant SaaS solution designed to automate absence management and ensure compliance with the Norwegian Working Environment Act (*Arbeidsmiljøloven*).**

---

## 🚀 Project Overview

**Tidly** is an enterprise-grade application engineered to replace manual HR processes with a digital workflow. Unlike generic absence trackers, Tidly is built specifically for the **Norwegian market**, handling complex logic such as *Egenmelding* (self-certified sickness) quotas, *Feriepenger* (holiday pay) basis tracking, and strict GDPR compliance.

### Key Differentiators
*   **🇳🇴 Compliance-First:** Native support for Norwegian holiday rules (25 days) and sick leave logic (3-day self-declaration limits).
*   **🏢 Deep Multi-tenancy:** Logical data isolation at the database level using `company_id` filtering for security.
*   **⚡ High Performance:** Backend leveraging **Java 21 Virtual Threads** for high-throughput I/O operations.

---

## 🏗️ Technical Architecture

The system follows a **Modular Monolith** architecture to balance development velocity with strict domain isolation.

### Backend (Java 21 & Spring Boot 4)
*   **Package Structure:** `no.tidly.modules.*` (Strict encapsulation).
*   **Concurrency:** Utilizes **Virtual Threads** (Project Loom) for non-blocking database interactions.
*   **Data Integrity:** **UUID** primary keys and **Optimistic Locking** for balance transactions.
*   **Security:** Spring Security tailored for multi-tenant context propagation.

### Frontend (Next.js 16)
*   **Rendering:** React Server Components (RSC) for optimized initial loads.
*   **State:** TanStack Query for real-time synchronization with the backend.
*   **Design:** Accessible, mobile-first UI for rapid request submission.

---

## 🧩 Core Modules

### 1. Organization & Identity (`no.tidly.modules.organization`)
Manages the "Who is Who".
*   **Tree Hierarchy:** Departments and Teams structure.
*   **Audit Trail:** Immutable `LeadershipHistory` to track who managed whom at any point in time.

### 2. Rules Engine (`no.tidly.modules.configuration`)
Manages the "Rules of the Game".
*   **Holiday Calendar:** Integration with Norwegian public holidays to calculate actual working days.
*   **Accrual Logic:** Configurable engines for Vacation vs. Sick Leave balances.

### 3. Workflow Engine (`no.tidly.modules.workflow`)
Manages the "Transactions".
*   **State Machine:** `PENDING` → `AUTHORIZED` (Team Lead) → `APPROVED` (Manager).
*   **Log:** Immutable approval logs for HR auditing.

---

## 📂 Project Structure

The project follows a strict package-by-feature organization to ensure modularity.

```
no.tidly/
├── core/                        # Global configs & Infrastructure
│   ├── config/                  # Security, Database, Jackson, Cloud
│   ├── security/                # JWT, Auth Provider, TenantContext
│   ├── exception/               # Global Exception Handler
│   └── shared/                  # BaseEntity, Generic DTOs, Utils
│
├── modules/                     # Business Modules (Isolated Contexts)
│   ├── organization/            # Module 1: Core Organizational Structure
│   │   ├── controllers/         # REST Controllers
│   │   ├── domain/              # Entities (Company, Dept, Team, Employee)
│   │   ├── repository/          # Spring Data Interfaces
│   │   ├── service/             # Business Logic (e.g., Hiring)
│   │   └── dto/                 # Request/Response Records
│   │
│   ├── configuration/           # Module 2: Absence Rules & Holidays
│   │   ├── controllers/
│   │   ├── domain/              # AbsenceType, Holiday, AbsenceSetting
│   │   ├── repository/
│   │   └── service/             # Date Calculation Engine
│   │
│   └── workflow/                # Module 3: Absence Requests & Flow
│       ├── controllers/
│       ├── domain/              # AbsenceRequest, ApprovalLog, Balance
│       ├── repository/
│       └── service/             # Approval Engine & Balance Transactions
│
└── TidlyApplication.java        # Main Entry Point
```

---

## 🛠️ Getting Started

### Prerequisites
*   Java 21 JDK
*   Docker (for PostgreSQL)
*   Maven

### Local Development

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/tidly-absence-manager.git
    cd tidly-absence-manager
    ```

2.  **Start the Database:**
    ```bash
    docker-compose up -d db
    ```

3.  **Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```

---

## 📍 Roadmap & Status

| Milestone | Feature | Status |
| :--- | :--- | :--- |
| **M1** | Core Architecture & Multi-tenancy | 🚧 **In Progress** |
| **M2** | Date Calculation Engine (Norwegian Holidays) | 🔴 Planned |
| **M3** | Balance & Accrual Transactions | 🔴 Planned |
| **M4** | Approval Workflow State Machine | 🔴 Planned |
| **M5** | Manager Dashboard (Next.js) | 🔴 Planned |

---

## 👨‍💻 Author

**Leonildo Gomes**  
*Senior Software Engineer & Architect*

Built with focus on **Clean Architecture**, **Domain-Driven Design (DDD)**, and **Developer Experience**.
