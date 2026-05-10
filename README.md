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
- JWT Authentication Middleware
- Protected REST APIs
- Swagger JWT Authorization Support
- Stateless Authentication
- Custom UserDetailsService
- SecurityContext Integration

## Security Architecture

- JWT Token Authentication
- Stateless Session Management
- Spring Security Filter Chain
- Custom JWT Authentication Filter
- SecurityContext-Based Authentication
- Role-Based Authorization

## Tech Stack

### Backend
- Java 21
- Spring Boot 3.x
- Spring Security 6
- Spring Data JPA
- Hibernate

### Authentication
- JWT (JSON Web Token)
- BCrypt Password Encryption

### Database
- PostgreSQL

### Documentation
- Swagger / OpenAPI

### Build Tool
- Maven

### DevOps
- Docker (upcoming)
- Kubernetes (upcoming)

## Run Application

```bash
./mvnw spring-boot:run

## API Endpoints

### Authentication APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Authenticate user and generate JWT token |
| GET | `/users/me` | Get logged-in user details (Protected API) |

---

## JWT Authentication

1. Register or login using authentication APIs.
2. Copy the generated JWT token.
3. Click the **Authorize** button in Swagger UI.
4. Add token in this format:

```txt
Bearer your_jwt_token


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

## Screenshots

### Swagger UI

![Swagger UI](screenshots/swagger-home.png)

---

### Register API

![Register API](screenshots/register-api.png)

---

### Login API

![Login API](screenshots/login-api.png)

### Protected API Access

![Protected API](screenshots/protected-api.png)

