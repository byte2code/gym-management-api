# Changelog

All notable changes to this project are documented in this file.

## [v2.0.0] - 2026-04-10

### Summary
Second version of the Gym Management API that adds a custom form-login experience and remember-me authentication support.

### Highlights

- Added a dedicated `LoginController` with a custom `/login` page.
- Added Thymeleaf-based `login.html` for browser login flow.
- Replaced HTTP Basic security with form login.
- Added remember-me support backed by the custom `UserDetailsService`.
- Refreshed the README to present the project as a stronger security and web-integration learning showcase.

### Notes

This version improves the project’s learning value by combining secured REST workflows with a simple custom authentication UI.

## [v1.0.0] - 2026-04-09

### Summary
Initial publication of the Gym Management API as a clean, portfolio-ready Spring Boot REST project.

### Highlights

- Added a recruiter-friendly README with API overview, features, run steps, security notes, and project structure.
- Added a changelog for clear future version tracking.
- Cleaned IDE files, build artifacts, and template helper files before publishing.
- Preserved the original gym, user, membership, and workout management workflows.

### Notes

This version establishes the project as a compact learning showcase for multi-role secured REST APIs built with Spring Boot, JPA, and MySQL.
