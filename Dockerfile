# Use a lightweight Java runtime as the base image
# Run Maven clean and install
#RUN mvn clean install
# Build stage
FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/jdbcdata-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


#1    docker build -t postgres-springboot-app .
#2    docker run -p 8080:8080 postgresdpringboot-app
#3    docker run -p 8080:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod" postgresdpringboot-app
#4    docker-compose -f redis-docker-compose.yml up
#5    docker-compose -f redis-docker-compose.yml up -d