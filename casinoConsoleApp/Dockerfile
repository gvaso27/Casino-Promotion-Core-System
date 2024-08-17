# Use OpenJDK base image
FROM openjdk:22-jdk
# Set the working directory inside the container
WORKDIR /
# Copy the compiled Java classes into the container
COPY out/production/casinoConsoleApp /
# Set the entry point for the container with preview features enabled
ENTRYPOINT ["java", "--enable-preview", "main"]
