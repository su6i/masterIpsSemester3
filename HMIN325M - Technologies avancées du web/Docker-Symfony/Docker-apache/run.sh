#!/bin/bash

mkdir -p ./web
mkdir -p ./cache ./logs
touch ./logs/prod.log
touch ./logs/dev.log

# usermod -a -G www-data $username
RUN usermod -u 1000 www-data
chgrp -R www-data .
chmod -R g+w ./cache ./logs

source /etc/apache2/envvars

tail -F /var/log/apache2/* ./logs/prod.log ./logs/dev.log &
exec service varnish start &
exec php7.4-fpm &
# exec /usr/sbin/apachectl -D FOREGROUND "$@"
exec apache2 -D FOREGROUND "$@"