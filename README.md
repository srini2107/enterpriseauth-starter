# EnterpriseAuth Starter

Production-ready Spring Boot JWT Authentication Starter Kit.

## Features

- Spring Boot 3.x
- Java 21
- JWT Authentication
- User Registration API
- User Login API
- BCrypt Password Encryption
- Role-Based Authorization
- Spring Security 6
- PostgreSQL Integration
- JPA/Hibernate
- UUID-Based Primary Keys
- Global Security Configuration
- Swagger/OpenAPI Documentation
- RESTful API Architecture
- Maven Build Configuration
- Audit Fields (createdAt, updatedAt)
- Clean Enterprise Project Structure
- Docker Ready (upcoming)
- Kubernetes Ready (upcoming)

## Tech Stack

- Spring Boot
- PostgreSQL
- Maven
- Docker

## Run Application

```bash
./mvnw spring-boot:run

## API Endpoints

### Authentication APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Authenticate user and generate JWT token |

---

## Sample Register Request

```json
{
  "username": "string",
  "email": "string@gmail.com",
  "password": "password123"
}

## Sample Login Request
{
  "email": "string@gmail.com",
  "password": "password123"
}

## Sample Auth Response
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
