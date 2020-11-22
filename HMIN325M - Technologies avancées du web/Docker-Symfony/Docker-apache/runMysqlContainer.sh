docker run \
--detach \
--name=ipsMysql \
--env="MYSQL_ROOT_PASSWORD=root" \
--publish 6603:3306 \
--volume=/root/docker/ipsMysql/conf.d:/etc/mysql/conf.d \
--volume=/ipsMysql-data:/var/lib/mysql \
mysql