# 第一种运行方式 -- docker run（第一次运行）
export MYSQL_ADDRESS=172.30.32.1            # mysql的ip
export ZOOKEEPER_ADDRESS=172.30.32.1        # zookeeper的ip
export REDIS_ADDRESS=172.30.32.1            # redis的ip
export NETWORK=micro-service-demo-network   # 用于container之间相互通信的桥接网络名

# 创建桥接网络
docker network create --driver bridge $NETWORK

# 运行服务
# message-service
docker run --network $NETWORK --name message-service -d sun/message-service:1.0
# user-service
docker run --network $NETWORK --name user-service -d sun/user-service:1.0 --mysql.address=$MYSQL_ADDRESS
# user-edge-service
docker run --network $NETWORK --name user-edge-service -d sun/user-edge-service:1.0 --redis.address=$REDIS_ADDRESS
# course-service
docker run --network $NETWORK --name course-service -d sun/course-service:1.0 --mysql.address=$MYSQL_ADDRESS --zookeeper.address=$ZOOKEEPER_ADDRESS
# course-edge-service
docker run --network $NETWORK --name course-edge-service -d sun/course-edge-service:1.0 --zookeeper.address=$ZOOKEEPER_ADDRESS
# api-gateway
docker run -p 8080:8080 --network $NETWORK --name api-gateway -d sun/api-gateway:1.0
