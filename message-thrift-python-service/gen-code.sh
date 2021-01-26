#!/usr/bin/sh
thrift --gen py -out ./ ./thrift/message.thrift
thrift --gen java -out ../message-thrift-java-service/src/main/java ./thrift/message.thrift
