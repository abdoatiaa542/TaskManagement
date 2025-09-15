# Task Management API

A simple Spring Boot REST API for user authentication and task management.

## Requirements

* Java 17
* Maven 3.9+
* MySQL (or any other database configured in `application.yml`)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/task-management-api.git
cd task-management-api
```

### 2. Configure the Database

Update your `src/main/resources/application.yml` (or `.properties`) with your DB credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/task_db
    username: root
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

application:
  security:
    access-token:
      secret-key: your_generated_secret_key_here
```

👉 Generate a secure key (example):

```bash
openssl rand -base64 32
```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The app will start at:

```
http://localhost:8080
```

## API Endpoints

### Authentication

* `POST /auth/register` → Register new user
* `POST /auth/login` → Login and get tokens
* `POST /auth/logout` → Logout user

### Tasks

* `POST /tasks` → Create task
* `GET /tasks` → Get all tasks for current user
* `PUT /tasks/{id}` → Update task
* `DELETE /tasks/{id}` → Delete task

## Running Tests

### Run all tests

```bash
mvn test
```

### Run a specific test class

```bash
mvn -Dtest=AuthServiceTest test
```

### Run tests inside IntelliJ IDEA

1. Right-click on `src/test/java` → **Run 'All Tests'**.
2. Or open a specific test class and click the green play button.

---

