FROM openjdk:8u282-jre-buster
LABEL maintainer="sun oct-month@qq.com"

WORKDIR /user-edge-service
COPY ./target/user-edge-service-1.0-SNAPSHOT.jar /user-edge-service/user-edge-service.jar
ENTRYPOINT [ "java", "-jar", "user-edge-service.jar" ]
