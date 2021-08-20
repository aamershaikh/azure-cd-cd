FROM openjdk:8-jdk-alpine
MAINTAINER xyz <xyz@gmail.com>
ADD target/account-service.jar account-service.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "/account-service.jar"]