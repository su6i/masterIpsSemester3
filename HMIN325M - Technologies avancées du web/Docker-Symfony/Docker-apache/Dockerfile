FROM ubuntu:20.04
ENV DEBIAN_FRONTEND noninteractive
ENV TZ=Europe/Paris

LABEL version="1.0"
LABEL maintainer.name="Amir SHIRALI POUR"
LABEL maintainer.email="emasalari@gmail.com"
ADD . /ipsSymfony
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN apt-get update
RUN apt-get install dialog apt-utils -y -q php7.4 apache2 \
    libapache2-mod-php7.4 php7.4-mysql php7.4-intl git git-core \
    curl php7.4-curl php7.4-xml php7.4-fpm composer zip unzip php7.4-zip \
    vim zsh php7.4-cli

# Configure Apache
RUN rm -rf /var/www/* \
    && a2enmod rewrite \
    && echo "ServerName localhost" >> /etc/apache2/apache2.conf
ADD vhost.conf /etc/apache2/sites-available/000-default.conf


# Set Apache environment variables (can be changed on docker run with -e)
ENV APACHE_RUN_USER www-data
ENV APACHE_RUN_GROUP www-data
ENV APACHE_LOG_DIR /var/log/apache2
ENV APACHE_PID_FILE /var/run/apache2.pid
ENV APACHE_RUN_DIR /var/run/apache2
ENV APACHE_LOCK_DIR /var/lock/apache2
ENV APACHE_SERVERADMIN admin@localhost
ENV APACHE_SERVERNAME localhost
ENV APACHE_SERVERALIAS docker.localhost
ENV APACHE_DOCUMENTROOT /var/www


# Install Symfony
RUN mkdir -p /usr/local/bin
RUN curl -LsS https://symfony.com/installer -o /usr/local/bin/symfony
RUN chmod a+x /usr/local/bin/symfony
# Add main start script for when image launches
ADD run.sh /run.sh
RUN chmod 0755 /run.sh
# RUN /run.sh
WORKDIR /ipsSymfony
EXPOSE 80 8000 9000 3306
RUN cp -r /home /ipsSymfony
# CMD ["apache2", "-D", "FOREGROUND", "$@"]
CMD ["bash", "/run.sh"]