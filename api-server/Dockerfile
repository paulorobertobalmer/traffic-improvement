FROM openjdk:17-jdk-alpine
ADD target/api-server-1.0.0.jar api-server-1.0.0.jar
ENTRYPOINT ["java", "-jar","api-server-1.0.0.jar"]
EXPOSE 8080