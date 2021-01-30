mvn package -Dmaven.test.skip=true

WORKDIR=`pwd`
export MYSQL_ADDRESS=172.30.32.1            # mysql的ip
export ZOOKEEPER_ADDRESS=172.30.32.1        # zookeeper的ip
export REDIS_ADDRESS=172.30.32.1            # redis的ip
export NETWORK=micro-service-demo-network   # 用于container之间相互通信的桥接网络名

# 创建桥接网络
docker network create --driver bridge $NETWORK

# message-service
cd $WORKDIR/message-thrift-service/
sh build.sh

# user-service
cd $WORKDIR/user-thrift-service/
sh build.sh

# user-edge-service
cd $WORKDIR/user-edge-service/
sh build.sh

# course-service
cd $WORKDIR/course-dubbo-service/
sh build.sh

# course-edge-service
cd $WORKDIR/course-edge-service/
sh build.sh

# api-gateway
cd $WORKDIR/api-gateway-zuul/
sh build.sh
