docker run --network $NETWORK --name course-edge-service -d sun/course-edge-service:latest --zookeeper.address=$ZOOKEEPER_ADDRESS