docker build -t sun/api-gateway:1.0 .

docker run -p 8080:8080 --network $NETWORK --name api-gateway -d sun/api-gateway:1.0
