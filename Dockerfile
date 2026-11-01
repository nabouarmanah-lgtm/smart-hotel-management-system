# Base image with Java (JDK)
FROM eclipse-temurin:21-jdk-alpine

# Working directory inside the container
WORKDIR /app

# Copy only the source code (see .dockerignore)
COPY src/ ./src/

# Compile all Java files into the "out" folder
RUN mkdir out && javac -d out $(find src -name "*.java")

# Run the application
CMD ["java", "-cp", "out", "app.FinalP"]
