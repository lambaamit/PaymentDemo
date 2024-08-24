# Use the official OpenJDK 21 image as a parent image
FROM openjdk:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the build artifact from the target directory (after build)
COPY target/payment-core-1.0.0.jar /app/payment-core-1.0.0.jar

# Expose the port the application runs on
EXPOSE 9131

# Run the application
ENTRYPOINT ["java", "-jar", "payment-core-1.0.0.jar"]