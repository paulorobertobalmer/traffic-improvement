FROM openjdk:17-jdk-alpine

ADD target/traffic-batch-1.0.0.jar traffic-batch-1.0.0.jar
ENTRYPOINT ["java", "-jar","traffic-batch-1.0.0.jar"]
EXPOSE 8080