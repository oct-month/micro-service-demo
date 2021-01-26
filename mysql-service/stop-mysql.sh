#!/usr/bin/sh
cur_dir=`pwd`

docker stop course-demo-mysql
docker rm course-demo-mysql
