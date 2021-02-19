FROM openjdk:8-jre-alpine
LABEL maintainer="sun oct-month@qq.com"

WORKDIR /user-service
COPY ./target/user-thrift-service-1.0-SNAPSHOT.jar /user-service/user-service.jar
ENTRYPOINT [ "java", "-jar", "user-service.jar" ]
