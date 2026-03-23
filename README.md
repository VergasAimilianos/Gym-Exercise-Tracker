# Gym Exercise Tracker

A full-stack CRUD web application for tracking gym exercises and weights. Built with a **Spring Boot** REST API backend and a **React + TypeScript** frontend.

---

## Overview

The app lets users log their gym workouts by storing exercise names and the corresponding weight lifted. Each record can be created, viewed, updated, and deleted directly from the UI.

---

## Tech Stack

### Backend
| Technology | Version | Purpose |
|---|---|---|
| Java | 21 | Language |
| Spring Boot | 4.0.0 | REST API framework |
| Spring Data JPA | — | ORM / database access |
| PostgreSQL | — | Relational database |
| Lombok | — | Boilerplate reduction |
| MapStruct | 1.6.3 | DTO ↔ Entity mapping |
| Maven | — | Build tool |

### Frontend
| Technology | Version | Purpose |
|---|---|---|
| React | 19 | UI framework |
| TypeScript | ~5.9.2 | Type-safe JavaScript |
| React Router DOM | 6.29.0 | Client-side routing |
| Nx | 22.2.0 | Monorepo build system |
| Webpack | — | Bundler |

---

## Features

- **Create** a new gym record with an exercise name and weight
- **Read** all existing gym records in real time
- **Update** an existing record's exercise name or weight
- **Delete** a record by ID
- Global exception handling with meaningful HTTP error responses
- MapStruct-powered DTO mapping layer keeping the API clean

---

## Project Structure

```
├── backend/
│   ├── BackendApplication.java          # Spring Boot entry point
│   ├── entities/
│   │   └── GymRecord.java               # JPA entity (id, exercise, weight)
│   ├── dtos/
│   │   └── GymRecordDto.java            # Data Transfer Object
│   ├── repositories/
│   │   └── GymRecordsRepository.java    # JPA repository
│   ├── services/
│   │   └── RecordsService.java          # Business logic (CRUD)
│   ├── controllers/
│   │   └── RecordsController.java       # REST endpoints
│   ├── mappers/
│   │   └── GymRecordMapper.java         # MapStruct mapper
│   ├── exceptions/
│   │   ├── AppException.java
│   │   └── RestExceptionHandler.java    # Global error handler
│   └── config/
│       └── WebConfig.java               # CORS configuration
│
└── frontend/
    ├── app.tsx                          # Root component, state & fetch logic
    ├── entities/GymRecord.tsx           # TypeScript type definition
    ├── vertical-container/              # Layout wrapper component
    ├── content-box/
    │   ├── CreateContentBox.tsx         # Create form
    │   ├── ReadContentBox.tsx           # Record display card
    │   ├── UpdateContentBox.tsx         # Update form
    │   └── DeleteContentBox.tsx         # Delete button card
    └── styles/                          # CSS modules
```

---

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.9+
- Node.js 18+
- PostgreSQL running on port `5434`

---

### 1. Database Setup

Create a PostgreSQL database and user to match the default configuration:

```sql
CREATE USER backend WITH PASSWORD 'backend';
CREATE DATABASE backenddb OWNER backend;
```

The default datasource config in `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5434/backenddb
    username: backend
    password: backend
  jpa:
    hibernate:
      ddl-auto: create-drop
```

> `ddl-auto: create-drop` will **drop and recreate** the schema on every restart. Change to `update` or `validate` to persist data between runs.

---

### 2. Run the Backend

```bash
# From the backend root (where pom.xml is)
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

### 3. Run the Frontend

```bash
# From the frontend root (where package.json is)
npm install
npm start
```

The app will be available at `http://localhost:4200` (Nx default).

---

## API Reference

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/gym/records` | Fetch all gym records |
| `POST` | `/gym/records` | Create a new record |
| `PUT` | `/gym/records/{id}` | Update a record by ID |
| `DELETE` | `/gym/records/{id}` | Delete a record by ID |

### Example Request Body (POST / PUT)

```json
{
  "exercise": "Bench Press",
  "weight": 100
}
```

### Example Response

```json
{
  "id": 1,
  "exercise": "Bench Press",
  "weight": 100
}
```

---

## Data Model

**GymRecord**

| Field | Type | Description |
|-------|------|-------------|
| `id` | `Long` | Auto-generated primary key |
| `exercise` | `String` | Name of the exercise |
| `weight` | `Integer` | Weight lifted (in your preferred unit) |

---

## Error Handling

The backend uses a global `RestExceptionHandler` that catches `AppException` instances and returns structured error responses:

```json
{
  "message": "Gym Record not found"
}
```

Common HTTP status codes returned:
- `200 OK` — Successful read, update, or delete
- `201 Created` — Record created successfully
- `404 Not Found` — Record with given ID does not exist
