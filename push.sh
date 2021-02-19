docker tag sun/api-gateway:latest localhost/micro-service/api-gateway:latest
docker tag sun/course-edge-service:latest localhost/micro-service/course-edge-service:latest
docker tag sun/course-service:latest localhost/micro-service/course-service:latest
docker tag sun/user-edge-service:latest localhost/micro-service/user-edge-service:latest
docker tag sun/user-service:latest localhost/micro-service/user-service:latest
docker tag sun/message-service:latest localhost/micro-service/message-service:latest

docker push localhost/micro-service/api-gateway:latest
docker push localhost/micro-service/course-edge-service:latest
docker push localhost/micro-service/course-service:latest
docker push localhost/micro-service/user-edge-service:latest
docker push localhost/micro-service/user-service:latest
docker push localhost/micro-service/message-service:latest

docker rmi localhost/micro-service/api-gateway:latest
docker rmi localhost/micro-service/course-edge-service:latest
docker rmi localhost/micro-service/course-service:latest
docker rmi localhost/micro-service/user-edge-service:latest
docker rmi localhost/micro-service/user-service:latest
docker rmi localhost/micro-service/message-service:latest

docker rmi sun/api-gateway:latest
docker rmi sun/course-edge-service:latest
docker rmi sun/course-service:latest
docker rmi sun/user-edge-service:latest
docker rmi sun/user-service:latest
docker rmi sun/message-service:latest
