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

FROM gradle:7.4.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build


FROM openjdk:11

EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/api-0.0.1.jar /app/
RUN bash -c 'touch /app/api-0.0.1.jar'
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/api-0.0.1.jar"]
