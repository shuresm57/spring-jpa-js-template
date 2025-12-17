# Spring JPA JS Template

Spring Boot template with JPA backend and vanilla JavaScript frontend.

## Features
- Spring Boot 4.0 with Spring Data JPA
- H2 in-memory database
- Generic CRUD service with exception handling
- REST API endpoints
- Vanilla JavaScript frontend with Tailwind CSS
- Dark mode UI design

## Getting Started
1. Run the application: `./mvnw spring-boot:run`
2. Open browser: http://localhost:8080
3. H2 Console: http://localhost:8080/h2-console (jdbc:h2:mem:testdb, user: sa, password: password)

## API Endpoints
- GET /api/sample-entities - Get all entities
- POST /api/sample-entities - Create entity
- PUT /api/sample-entities/{id} - Update entity
- DELETE /api/sample-entities/{id} - Delete entity

## Usage
Extend CrudService for new entities: `public class YourService extends CrudService<YourEntity, Long>`
Use findByIdOrThrow() to automatically throw ResourceNotFoundException for missing entities
GlobalExceptionHandler catches exceptions and returns proper HTTP error responses

## Requirements
- Java 21+
- Maven 3.6+