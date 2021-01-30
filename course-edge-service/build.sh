docker build -t sun/course-edge-service:1.0 .

docker run --network $NETWORK --name course-edge-service -d sun/course-edge-service:1.0 --zookeeper.address=$ZOOKEEPER_ADDRESS
