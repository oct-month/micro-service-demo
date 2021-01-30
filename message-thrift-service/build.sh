docker build -t sun/message-service:1.0 .

docker run --network $NETWORK --name message-service -d sun/message-service:1.0
