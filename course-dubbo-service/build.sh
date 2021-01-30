docker build -t sun/course-service:1.0 .

docker run --network $NETWORK --name course-service -d sun/course-service:1.0 --mysql.address=$MYSQL_ADDRESS --zookeeper.address=$ZOOKEEPER_ADDRESS
