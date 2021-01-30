docker build -t sun/user-service:1.0 .

docker run --network $NETWORK --name user-service -d sun/user-service:1.0 --mysql.address=$MYSQL_ADDRESS
