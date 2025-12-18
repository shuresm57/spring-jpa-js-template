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
- Fork the repository to get started.
- Open it in IntelliJ and select File -> Save as Template.

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
