LABEL authors="Thanh Duong"
LABEL version="1.0"
# Base image with Java 23
FROM eclipse-temurin:23-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Grant execution permission to mvnw
RUN chmod +x mvnw

# Install required packages for JavaFX
RUN apt-get update && apt-get install -y \
    libgl1-mesa-glx \
    libx11-6 \
    libxext6 \
    libxtst6 \
    libxrender1 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src/ ./src/

# Build the application
RUN ./mvnw clean package

# Environment variable for display
ENV DISPLAY=:0

# Command to run the application
CMD ["./mvnw", "javafx:run", "target/BlueMoon-1.0-SNAPSHOT.jar"]