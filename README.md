# Gym Management API

Spring Boot REST API for managing gyms, users, memberships, and workouts with MySQL persistence, role-based security, and JWT-based authentication.

## Overview

This project demonstrates a compact Spring Boot gym-management application with secure, role-aware workflows. It covers public user registration, token-based login, admin-controlled gym and membership management, trainer-assigned workouts, customer profile access, and JWT-based request authentication for protected endpoints.

## Concepts and Features Covered

- Spring Boot REST API setup
- Spring Data JPA repository pattern
- MySQL-backed persistence
- Spring Security with JWT authentication
- Method-level security using `@EnableMethodSecurity` and `@PreAuthorize`
- Custom `UserDetailsService` for loading users by email
- JWT token generation and bearer-token validation
- Custom `OncePerRequestFilter` for token-based authentication
- Role-based access for `ADMIN`, `CUSTOMER`, and `TRAINER`
- DTO-based request handling for gym, user, workout, and login operations
- Gym create, read, update, and delete endpoints
- Member assignment and member removal workflows
- User registration, lookup, update, and deletion
- Trainer-assigned workout creation for users
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
- JJWT

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
        │   ├── jwt/
        │   ├── model/
        │   ├── repository/
        │   ├── security/
        │   ├── service/
        │   └── GymApplication.java
        └── resources/
            ├── application.yml
            └── templates/
                └── login.html
```

## How to Run

1. Open a terminal in the project root.
2. Update the MySQL connection values in `src/main/resources/application.yml` if needed.
3. Run `mvn test`.
4. Run `mvn spring-boot:run`.
5. Obtain a JWT using `POST /auth/login`.
6. Call protected endpoints with `Authorization: Bearer <token>`.

Available endpoints:

- `POST /auth/login`
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
- `GET /login`

Access notes:

- `/auth/login` and `/user/register` are public
- all other API endpoints require a valid bearer token
- `ADMIN` manages gyms, memberships, and user listing/deletion
- `CUSTOMER` can view and update their own profile
- `TRAINER` can assign workouts to users

Example request body for login:

```json
{
  "username": "john@example.com",
  "password": "secret123"
}
```

Example request body for user registration:

```json
{
  "email": "john@example.com",
  "password": "secret123",
  "age": 24,
  "gender": "Male",
  "userType": "CUSTOMER"
}
```

Example request body for gym creation:

```json
{
  "name": "FitCore Gym",
  "address": "Sector 18, Noida",
  "contactNo": "9876543210",
  "membershipPlans": "Monthly, Quarterly, Yearly",
  "facilities": "Cardio, Strength, Personal Training"
}
```

## Learning Highlights

- Demonstrates multi-role authorization in a Spring Boot application
- Shows how custom user loading integrates with token-based authentication
- Adds JWT generation and request filtering without changing the core gym domain flow
- Uses service-layer coordination for gym membership assignment and workout management
- Combines security, persistence, and role-aware workflows in a compact learning project

## GitHub Metadata

- Suggested repository description: `Spring Boot REST API for gym, membership, and workout management with MySQL persistence, JWT authentication, and role-based security.`
- Suggested topics: `java`, `java-17`, `spring-boot`, `spring-security`, `spring-data-jpa`, `mysql`, `rest-api`, `gym-management`, `jwt`, `role-based-access`, `maven`, `learning-project`, `portfolio-project`
