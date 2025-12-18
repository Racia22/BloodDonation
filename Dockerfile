# Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy your JAR file from NetBeans dist folder
COPY dist/BloodDonationManagementSystemBest.jar app.jar

# Copy external libraries if your app uses them
COPY dist/lib ./lib

# Expose port (change if your app uses a different port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]