#!/bin/bash

mkdir -p ipsSymfonyProject/app/cache ipsSymfonyProject/app/logs
touch ipsSymfonyProject/app/logs/prod.log
touch ipsSymfonyProject/app/logs/dev.log
chgrp -R www-data .
chmod -R g+w ipsSymfonyProject/app/cache ipsSymfonyProject/app/logs

source /etc/apache2/envvars

tail -F /var/log/apache2/* ipsSymfonyProject/app/logs/prod.log ipsSymfonyProject/app/logs/dev.log &
exec service varnish start &
exec php-fpm &
exec apache2 -D FOREGROUND "$@"