# Gym Management API

Spring Boot REST API for managing gyms, users, memberships, and workouts with MySQL persistence, role-based security, and a custom login page.

## Overview

This project demonstrates a compact Spring Boot gym-management application with secure, role-aware workflows. It covers public user registration, admin-controlled gym and membership management, trainer-assigned workouts, customer profile access, and a custom Thymeleaf login page with remember-me support.

## Concepts and Features Covered

- Spring Boot REST API setup
- Spring Data JPA repository pattern
- MySQL-backed persistence
- Spring Security with form login
- Method-level security using `@EnableMethodSecurity` and `@PreAuthorize`
- Custom `UserDetailsService` for loading users by email
- Custom Thymeleaf login page
- Remember-me authentication support
- Role-based access for `ADMIN`, `CUSTOMER`, and `TRAINER`
- DTO-based request handling for gym, user, and workout operations
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
- Thymeleaf
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
            ├── application.yml
            └── templates/
                └── login.html
```

## How to Run

1. Open a terminal in the project root.
2. Update the MySQL connection values in `src/main/resources/application.yml` if needed.
3. Run `mvn test`.
4. Run `mvn spring-boot:run`.
5. Open `http://localhost:8082/login` for the custom login page.
6. Use the secured API under `http://localhost:8082`.

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
- `GET /login`

Access notes:

- `/user/register` and `/login` are public
- `ADMIN` manages gyms, memberships, and user listing/deletion
- `CUSTOMER` can view and update their own profile
- `TRAINER` can assign workouts to users
- successful login can optionally use `remember-me` persistence

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

Example request body for workout assignment:

```json
{
  "workoutName": "Upper Body Strength",
  "description": "Chest, shoulders, and triceps workout",
  "difficultyLevel": "Intermediate",
  "duration": 45
}
```

## Learning Highlights

- Demonstrates multi-role authorization in a Spring Boot application
- Shows how custom user loading integrates with Spring Security authentication
- Adds a custom login page and remember-me flow without changing the core gym domain logic
- Uses service-layer coordination for gym membership assignment and workout management
- Combines security, persistence, templating, and role-aware workflows in a compact learning project

## GitHub Metadata

- Suggested repository description: `Spring Boot REST API for gym, membership, and workout management with MySQL persistence, custom login flow, and role-based security.`
- Suggested topics: `java`, `java-17`, `spring-boot`, `spring-security`, `spring-data-jpa`, `thymeleaf`, `mysql`, `rest-api`, `gym-management`, `role-based-access`, `maven`, `learning-project`, `portfolio-project`
