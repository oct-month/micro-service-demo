FROM openjdk:8u282-jre-buster
LABEL maintainer="sun oct-month@qq.com"

WORKDIR /api-gateway
COPY ./target/api-gateway-zuul-1.0-SNAPSHOT.jar /api-gateway/api-gateway.jar
ENTRYPOINT [ "java", "-jar", "api-gateway.jar" ]
