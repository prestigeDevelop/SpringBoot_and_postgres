# Spring Boot with Redis and Swagger UI

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Redis](https://img.shields.io/badge/Redis-7.0-red)
![Swagger](https://img.shields.io/badge/Swagger-UI-lightgreen)

A Spring Boot application demonstrating integration with Redis for caching and Swagger UI for API documentation.

## Features

- REST API with Spring Boot
- Redis caching integration
- Automated API documentation with Swagger UI
- JPA/Hibernate with PostgreSQL
- Spring Security configuration

## Prerequisites

- Java 17+
- Docker and Docker Compose
- Maven/Gradle
- PostgreSQL 13
- Redis 7

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repo.git
   ```

2. Navigate to the project directory:
   ```bash
   cd your-repo
   ```

3. Build the project:
   ```bash
   mvn clean install
   # or
   gradle build
   ```

4. Start the services using Docker Compose:
   The commands docker-compose up --build and docker-compose up -d serve different purposes, though they can be
   combined. Here's the breakdown:

1. docker-compose up --build Rebuilds images before starting containers

Ensures you're running containers with the latest code changes

Shows logs in the current terminal (foreground process)

Useful during development when you've made changes to:

Your application code

Dockerfiles

Dependencies

2. docker-compose up -d Starts containers in detached mode (runs in background)

Uses existing images (doesn't rebuild)

Returns terminal control immediately

Ideal for production or when you know images are up-to-date

## Configuration

Environment variables (set in `docker-compose.yml`):

| Variable                     | Default Value       | Description                          |
|------------------------------|---------------------|--------------------------------------|
| SPRING_DATASOURCE_URL        | jdbc:postgresql://... | PostgreSQL connection URL            |
| SPRING_DATASOURCE_USERNAME   | postgres            | Database username                   |
| SPRING_DATASOURCE_PASSWORD   | postgres            | Database password                   |
| SPRING_REDIS_HOST            | redis               | Redis host                          |
| SPRING_REDIS_PASSWORD        | redis_password      | Redis password                      |

## API Documentation

After starting the application, access the Swagger UI at:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

The OpenAPI JSON specification is available at:
[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Available Endpoints

- `GET /api/users/findAll` - Retrieve all users
- `POST /api/users/create` - Create a new user
- `GET /test-redis` - Test Redis connection
  http://localhost:8080/test-redis
## Running Tests

```bash
mvn test
# or
gradle test
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/yourpackage/
│   │       ├── config/       # Configuration classes
│   │       ├── controller/   # REST controllers
│   │       ├── model/        # Entity classes
│   │       ├── repository/   # Repository interfaces
│   │       └── service/      # Business logic
│   └── resources/
│       ├── application.properties # Configuration
│       └── static/           # Static resources
├── test/                     # Test classes
docker-compose.yml            # Docker configuration
Dockerfile                    # Application Dockerfile
```

## License

This project is licensed under the [MIT License](LICENSE).

## Troubleshooting

If you encounter issues:

1. Check container logs:
   ```bash
   docker-compose logs
   ```
2. Verify Redis connection:
   ```bash
   docker exec -it your-redis-container redis-cli ping
   ```
3. Check application health:
   [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

# First build (or after major changes):

docker-compose up --build -d

# Later restarts (no code changes):

docker-compose up -d

# When you need to see logs:

docker-compose logs -f app

# stop the container

docker-compose down 