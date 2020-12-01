# Symfony project


## Dockerfile of project:
- `docker run -dit --name apache \
-v "/Users/su6i/Amir/gitProjects/myGitProjects/Master-IPS-2019/masterIpsSemester3/HMIN325M - Technologies avancées du web/Symfony/SymfonyProject":/ipsSymfony \
-w /ipsSymfony \
-p 8080:80 -p 9000:9000 -p 8000:8000 -p 3306:3306 \
emasalari/symfony-apache2 bash /run.sh`

- `sudo service --status-all | less` : Verifying services on Ubuntu
- `cat /etc/os-release` : Verifying the Linux version

### Nginx:





---------------------------------------------
### Apache2:

- `sudo apt install apache2` : Installing Apache2 on Ubuntu
- `sudo service apache2 restart` or `systemctl restart apache2` : Restart Apache2 service on Ubuntu. We can also use `start` or `stop` istead of `restart`
- `/etc/apache2/apache2.conf` : Apache2 config file   
- `/etc/apache2/sites-available/000-default.conf` : 
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
