docker run --network $NETWORK --name user-service -d sun/user-service:latest --mysql.address=$MYSQL_ADDRESS