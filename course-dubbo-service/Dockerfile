FROM openjdk:8u282-jre-buster
LABEL maintainer="sun oct-month@qq.com"

WORKDIR /course-service
COPY ./target/course-dubbo-service-1.0-SNAPSHOT.jar /course-service/course-service.jar
ENTRYPOINT [ "java", "-jar", "course-service.jar" ]
