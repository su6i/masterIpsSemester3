# Symfony project

- `sudo service --status-all | less` : Verifying services on Ubuntu
- `cat /etc/os-release` : Verifying the Linux version

### Nginx:





---------------------------------------------
### Apache2:

- `sudo apt install apache2` : Installing Apache2 on Ubuntu
- `sudo service apache2 restart` or `systemctl restart apache2` : Restart Apache2 service on Ubuntu. We can also use `start` or `stop` istead of `restart`

---------------------------------------------
### MySQL:
- `sudo apt install mysql-server mysql-client` : Installing MySQL on Ubuntu
- `ps -edf | grep -i mysqld` : Verifying if MySQL daemon is runnung or not

---------------------------------------------
### PHP:
- `sudo apt install php7.4-cli php7.4-xml libapache2-mod-php7.4` or we can execute the same command without specifying the version, `sudo apt install php-cli php-xml libapache2-mod-php`   
- `sudo apt install php-mysql`   

---------------------------------------------
### Symfony
- `curl -sS https://getcomposer.org/installer -o composer-setup.php`: Downloding Symfony
- `sudo php composer-setup.php --install-dir=/usr/local/bin --filename=composer` Installing Symfony
- composer create-project symfony/skeleton my-project` : Creating a new Symfony project
- `composer require symfony/web-server-bundle --dev ^4.4.2` or `composer require symfony/web-server-bundle 4.4` or `php-cli` : Installing PHP development server in the project folder
  - `php bin/console server:run` : Running PHP development server in the project folder
- Address of composer: `/usr/local/bin/`
