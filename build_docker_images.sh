# 构建docker镜像
mvn clean
mvn package -Dmaven.test.skip=true

WORKDIR=`pwd`

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
