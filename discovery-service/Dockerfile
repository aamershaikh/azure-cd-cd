FROM openjdk:8-jdk-alpine
MAINTAINER xyz <xyz@gmail.com>
ADD target/discovery-service.jar discovery-service.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]
