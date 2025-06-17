# Kotlin Spring Boot Backend Application

A modern RESTful API built with Kotlin and Spring Boot demonstrating best practices.

## Features

- RESTful API with proper response handling
- Layered architecture (Controller, Service, Repository)
- Entity relationships with JPA
- Exception handling
- CORS configuration
- Database integration (H2 in-memory)
- API documentation
- Data Transfer Objects (DTOs)
- Transactional operations
- Lombok integration for reduced boilerplate

## Technologies

- Kotlin 1.9.0
- Spring Boot 3.1.0
- Spring Data JPA
- H2 Database
- Gradle with Kotlin DSL

## Getting Started

### Prerequisites

- JDK 17 or later
- Gradle 7.6+ (or use the included Gradle wrapper)

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run with Gradle:

```shell
./gradlew bootRun
```

Or on Windows:

```shell
gradlew.bat bootRun
```

The application will be available at http://localhost:8080

### API Endpoints

| Method | URL                       | Description            |
|--------|---------------------------|------------------------|
| GET    | /api/v1/users             | Get all users          |
| GET    | /api/v1/users/{id}        | Get user by ID         |
| POST   | /api/v1/users             | Create a new user      |
| PUT    | /api/v1/users/{id}        | Update an existing user|
| DELETE | /api/v1/users/{id}        | Delete a user          |
| GET    | /api/v1/users/search      | Search users by name   |
| GET    | /api/v1/users/recent      | Get recently added users|

### H2 Console

The H2 console is enabled and available at http://localhost:8080/h2-console

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
| Password: blank

## Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/
│   │       └── example/
│   │           ├── Application.kt
│   │           ├── AppConfig.kt
│   │           ├── User.kt
│   │           ├── UserController.kt
│   │           ├── UserDto.kt
│   │           ├── UserRepository.kt
│   │           ├── UserService.kt
│   │           └── GlobalExceptionHandler.kt
│   └── resources/
│       └── application.properties
└── test/
    └── kotlin/
        └── com/
            └── example/
                └── UserControllerTests.kt
```

## Best Practices Implemented

1. **Architecture**
   - Separation of concerns (Controller, Service, Repository)
   - Use of interfaces for loose coupling
   - Proper exception handling

2. **API Design**
   - RESTful principles
   - Proper HTTP status codes
   - DTOs for request/response separation

3. **Kotlin Best Practices**
   - Immutable data classes
   - Extension functions
   - Null safety
   - Function expressions

4. **Performance**
   - JPA optimizations
   - Transaction management
   - Connection pooling

5. **Security**
   - CORS configuration
   - Input validation
   - Error handling without exposing sensitive information

## License

This project is licensed under the MIT License
