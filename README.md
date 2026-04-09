# Gym Management API

Spring Boot REST API for managing gyms, users, memberships, and workouts with MySQL persistence and role-based security.

## Overview

This project demonstrates a compact Spring Boot API for a gym management workflow. It supports public user registration, admin-controlled gym and membership management, and trainer-assigned workouts, making it a strong learning project for role-based authorization across multiple related domain models.

## Concepts and Features Covered

- Spring Boot REST API setup
- Spring Data JPA repository pattern
- MySQL-backed persistence
- Spring Security with HTTP Basic authentication
- Method-level security using `@PreAuthorize`
- Public registration endpoint with secured domain operations
- Custom `UserDetailsService` integration
- Role-based access for `ADMIN`, `CUSTOMER`, and `TRAINER`
- DTO-based gym, user, and workout request handling
- Gym CRUD operations for admins
- Member assignment and removal within gyms
- User management with role-aware registration
- Trainer-driven workout assignment
- Custom exception handling for missing gyms and users

## Tech Stack

- Java 17
- Spring Boot 2.7
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Lombok

## Project Structure

```text
Gym I Template/
├── CHANGELOG.md
├── README.md
├── pom.xml
├── mvnw
├── mvnw.cmd
└── src/
    └── main/
        ├── java/com/CN/Gym/
        │   ├── config/
        │   ├── controller/
        │   ├── dto/
        │   ├── exception/
        │   ├── model/
        │   ├── repository/
        │   ├── security/
        │   ├── service/
        │   └── GymApplication.java
        └── resources/
            └── application.yml
```

## How to Run

1. Open a terminal in the project root.
2. Update the MySQL connection values in `src/main/resources/application.yml` if needed.
3. Run `mvn test`.
4. Run `mvn spring-boot:run`.
5. Use the public registration endpoint first to create users.
6. Use the API under `http://localhost:8082`.

Available endpoints:

- `GET /gym/all`
- `GET /gym/{id}`
- `POST /gym/create`
- `PUT /gym/{id}`
- `DELETE /gym/{id}`
- `POST /gym/addMember?userId={userId}&gymId={gymId}`
- `DELETE /gym/deleteMember?userId={userId}&gymId={gymId}`
- `GET /user/all`
- `POST /user/register`
- `GET /user/{id}`
- `PUT /user/{id}`
- `DELETE /user/{id}`
- `POST /user/workout/{userId}`

Access notes:

- `/user/register` is public
- `ADMIN` manages gyms, memberships, and user listing/deletion
- `CUSTOMER` can view and update their user profile
- `TRAINER` can assign workouts to users

Example request body for user registration:

```json
{
  "email": "alex@example.com",
  "password": "secret123",
  "age": 25,
  "gender": "Male",
  "userType": "CUSTOMER"
}
```

Example request body for gym creation:

```json
{
  "name": "FitZone",
  "address": "MG Road",
  "contactNo": "9876543210",
  "membershipPlans": "Monthly, Quarterly, Annual",
  "facilities": "Cardio, Strength, Yoga"
}
```

Example request body for workout assignment:

```json
{
  "workoutName": "Upper Body Split",
  "description": "Strength-focused routine",
  "difficultyLevel": "Intermediate",
  "duration": 45
}
```

## Learning Highlights

- Demonstrates role-based authorization across multiple business areas in one API
- Shows how user roles can drive different controller capabilities
- Uses repository-backed relationships for gym membership and workout assignment
- Combines custom user loading, password encoding, and method-level security in a compact learning project

## GitHub Metadata

- Suggested repository description: `Spring Boot REST API for gym, membership, and workout management with MySQL persistence, custom user loading, and role-based security.`
- Suggested topics: `java`, `java-17`, `spring-boot`, `spring-security`, `spring-data-jpa`, `mysql`, `rest-api`, `gym-management`, `role-based-access`, `maven`, `learning-project`, `portfolio-project`
