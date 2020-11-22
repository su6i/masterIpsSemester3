FROM ubuntu:20.04
ENV DEBIAN_FRONTEND noninteractive
ENV TZ=Europe/Paris

LABEL version="1.0"
LABEL maintainer.name="Amir SHIRALI POUR"
LABEL maintainer.email="emasalari@gmail.com"
ADD . /app
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN apt-get update
RUN apt-get install dialog apt-utils -y -q php apache2 \
    libapache2-mod-php7.4 php-mysql php-intl git git-core \
    curl php-curl php-xml php-fpm composer zip unzip php-zip \
    vim zsh

# Configure Apache
RUN rm -rf /var/www/* \
    && a2enmod rewrite \
    && echo "ServerName localhost" >> /etc/apache2/apache2.conf
ADD vhost.conf /etc/apache2/sites-available/000-default.conf

# Install Symfony
RUN mkdir -p /usr/local/bin
RUN curl -LsS https://symfony.com/installer -o /usr/local/bin/symfony
RUN chmod a+x /usr/local/bin/symfony
# Add main start script for when image launches
ADD run.sh /run.sh
RUN chmod 0755 /run.sh
WORKDIR /app
EXPOSE 80
CMD ["/run.sh"]

