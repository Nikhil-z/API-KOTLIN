# syntax=docker/dockerfile:1

# Use a base image with a JRE (Java Runtime Environment)
#FROM openjdk:11

# Set the working directory inside the container
#WORKDIR /app

# Copy the JAR file into the container
#COPY build/libs/api-0.0.1.jar .

# Expose the port your Ktor service is listening on (replace with your port)
#EXPOSE 8080

# Command to run your application
#CMD ["java", "-jar", "api-0.0.1.jar"]

FROM openjdk:11
# Set the working directory inside the container
WORKDIR /api
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
