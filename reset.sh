# sh stop.sh

mvn clean

docker network rm micro-service-demo-network

docker rm message-service
docker rm user-service
docker rm user-edge-service
docker rm course-service
docker rm course-edge-service
docker rm api-gateway

docker rmi sun/api-gateway:1.0
docker rmi sun/course-edge-service:1.0
docker rmi sun/course-service:1.0
docker rmi sun/user-edge-service:1.0
docker rmi sun/user-service:1.0
docker rmi sun/message-service:1.0
