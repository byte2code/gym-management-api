# Gym Management API

Spring Boot application for managing gyms, users, workouts, authentication, and JWT-based security.

## Features

- Gym, user, and workout management
- Login and authentication flow
- JWT token handling
- Actuator health and Prometheus exposure
- MySQL persistence with Spring Data JPA

## Main Areas

- `AuthController` for authentication
- `GymController` for gym operations
- `UserController` for user operations
- `CustomUserDetailService` for Spring Security integration

## Stack

- Java 17
- Spring Boot 2.7.16
- Spring Security
- Spring Data JPA
- MySQL
- JWT
- Lombok
