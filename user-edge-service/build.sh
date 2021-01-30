docker build -t sun/user-edge-service:1.0 .

docker run --network $NETWORK --name user-edge-service -d sun/user-edge-service:1.0 --redis.address=$REDIS_ADDRESS
