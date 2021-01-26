#!/usr/bin/sh
cur_dir=`pwd`

docker stop course-demo-mysql
docker rm course-demo-mysql
docker run --name course-demo-mysql -v ${cur_dir}/conf:/etc/mysql/conf.d -v ${cur_dir}/data:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=qwerty -d mysql:latest
