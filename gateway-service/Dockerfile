FROM openjdk:8-jdk-alpine
MAINTAINER xyz <xyz@gmail.com>
ADD target/gateway-service.jar gateway-service.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]
