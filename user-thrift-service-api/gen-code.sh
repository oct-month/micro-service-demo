#!/usr/bin/sh
thrift --gen java -out ./src/main/java ./thrift/user_service.thrift
