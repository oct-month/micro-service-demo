# 使用docker-compose命令构建后的reset脚本
docker-compose down

docker rmi sun/api-gateway:1.0
docker rmi sun/course-edge-service:1.0
docker rmi sun/course-service:1.0
docker rmi sun/user-edge-service:1.0
docker rmi sun/user-service:1.0
docker rmi sun/message-service:1.0
