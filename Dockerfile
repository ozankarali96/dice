# FROM adoptopenjdk/maven-openjdk11 AS builder
#
# WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests
#
# FROM op5com/jre-alpine
#
# COPY --from=builder /app/target/gambling-1.0-SNAPSHOT.jar /app/gambling-1.0-SNAPSHOT.jar
#
# ENTRYPOINT ["java", "-jar", "/app/gambling-1.0-SNAPSHOT.jar"]

FROM maven:3.8.4-openjdk-11-slim AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM openjdk:11-jre-slim
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/gambling-1.0-SNAPSHOT.jar /app/gambling-1.0-SNAPSHOT.jar
#
 ENTRYPOINT ["java", "-jar", "/app/gambling-1.0-SNAPSHOT.jar"]