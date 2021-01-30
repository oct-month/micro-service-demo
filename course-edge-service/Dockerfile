FROM openjdk:8u282-jre-buster
LABEL maintainer="sun oct-month@qq.com"

WORKDIR /course-service
COPY ./target/course-edge-service-1.0-SNAPSHOT.jar /course-service/course-edge-service.jar
ENTRYPOINT [ "java", "-jar", "course-edge-service.jar" ]
