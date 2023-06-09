
FROM openjdk:17-alpine

# Set the working directory
WORKDIR /app
# Copy the Gradle files
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
# Download and install Gradle
RUN ./gradlew --version
# Copy the project files
COPY . /app
# Build the project
RUN ./gradlew build
# Set the startup command
CMD ["java", "-jar", "build/libs/bank-backend-app-0.0.1-SNAPSHOT.jar"]

