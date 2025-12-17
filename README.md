# Spring JPA JS Template

A comprehensive Spring Boot template with JPA backend and vanilla JavaScript frontend, featuring dark mode design with Tailwind CSS.

## Features

### Backend
- **Spring Boot 4.0** - Latest Spring framework
- **Spring Data JPA** - Database abstraction layer
- **H2 Database** - In-memory database for development
- **Generic CRUD Service** - Reusable service layer with exception handling
- **Global Exception Handler** - Centralized error handling
- **RESTful API** - Standard REST endpoints
- **BaseEntity** - Common entity fields (id, createdAt, updatedAt)
- **DTO Pattern** - Data transfer objects for clean API
- **CORS Configuration** - Frontend integration ready
- **Security Disabled** - For development ease (can be re-enabled)

### Frontend
- **Vanilla JavaScript** - No framework dependencies
- **Tailwind CSS** - Utility-first CSS framework
- **Dark Mode** - Modern dark theme
- **Responsive Design** - Mobile-friendly interface
- **Centralized API Client** - Consistent HTTP requests (GET, POST, PUT, PATCH, DELETE)
- **CRUD Operations** - Complete entity management interface

## Project Structure

```
src/main/java/com/example/springjpajstemplate/
├── config/                 # Configuration classes
│   ├── SecurityConfig.java # Security configuration (disabled)
│   └── WebConfig.java      # CORS and view controllers
├── controller/             # REST controllers
│   └── SampleEntityController.java
├── dto/                    # Data Transfer Objects
│   └── SampleEntityDTO.java
├── exception/              # Exception handling
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── model/                  # Entity classes
│   ├── BaseEntity.java     # Base entity with common fields
│   └── SampleEntity.java   # Sample entity implementation
├── repository/             # JPA repositories
│   └── SampleEntityRepository.java
└── service/               # Service layer
    ├── CrudService.java    # Generic CRUD operations
    └── SampleEntityService.java

src/main/resources/
├── static/
│   ├── css/
│   │   └── styles.css      # Custom styles
│   ├── html/               # Additional HTML files
│   ├── js/
│   │   ├── api-client.js   # Centralized API client
│   │   └── sample-entity.js # Entity management logic
│   └── index.html          # Main HTML page
└── application.properties  # Configuration
```

## Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6+

### Running the Application

1. **Clone/Download** this template
2. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```
3. **Access the application:**
   - Frontend: [http://localhost:8080](http://localhost:8080)
   - H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: `password`

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/sample-entities` | Get all entities |
| GET | `/api/sample-entities/{id}` | Get entity by ID |
| POST | `/api/sample-entities` | Create new entity |
| PUT | `/api/sample-entities/{id}` | Update entity |
| DELETE | `/api/sample-entities/{id}` | Delete entity |

### Sample API Usage

```bash
# Get all entities
curl http://localhost:8080/api/sample-entities

# Create new entity
curl -X POST http://localhost:8080/api/sample-entities \
  -H "Content-Type: application/json" \
  -d '{"name":"Sample","description":"Test description"}'

# Update entity
curl -X PUT http://localhost:8080/api/sample-entities/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated","description":"Updated description"}'
```

## Customization

### Adding New Entities

1. **Create Entity Class:**
   ```java
   @Entity
   @Table(name = "your_entities")
   @Data
   @EqualsAndHashCode(callSuper = true)
   public class YourEntity extends BaseEntity {
       private String yourField;
       // Add more fields as needed
   }
   ```

2. **Create Repository:**
   ```java
   @Repository
   public interface YourEntityRepository extends JpaRepository<YourEntity, Long> {
   }
   ```

3. **Create Service:**
   ```java
   @Service
   public class YourEntityService extends CrudService<YourEntity, Long> {
       public YourEntityService(YourEntityRepository repository) {
           super(repository);
       }
   }
   ```

4. **Create DTO and Controller** following the existing patterns

### Frontend Customization

The API client (`api-client.js`) provides all HTTP methods. Create new frontend modules following the pattern in `sample-entity.js`.

## Configuration

### Database
The application uses H2 in-memory database by default. To use a different database:

1. Add the database dependency to `pom.xml`
2. Update `application.properties` with your database configuration:
   ```properties
   spring.datasource.url=jdbc:your-db://localhost:5432/yourdb
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=create-drop
   ```

### Security
Security is disabled by default for development. To enable:

1. Remove or modify `SecurityConfig.java`
2. Add authentication/authorization as needed

## Built With
- [Spring Boot](https://spring.io/projects/spring-boot) - Backend framework
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Data access layer
- [H2 Database](https://h2database.com/) - In-memory database
- [Lombok](https://projectlombok.org/) - Reduces boilerplate code
- [Tailwind CSS](https://tailwindcss.com/) - CSS framework
- [Vanilla JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript) - Frontend logic

## License
This template is open source and available under the [MIT License](LICENSE).