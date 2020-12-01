docker run -dit --name apache -p 8080:80 \
-v "/Users/su6i/Amir/gitProjects/myGitProjects/Master-IPS-2019/masterIpsSemester3/HMIN325M - Technologies avancées du web/Symfony/SymfonyProject":/usr/local/apache2/htdocs/ httpd:2.4


docker run -it --name apache \
-v "/Users/su6i/Amir/gitProjects/myGitProjects/Master-IPS-2019/masterIpsSemester3/HMIN325M - Technologies avancées du web/Symfony/SymfonyProject":/ipsSymfony \
-w /ipsSymfony \
-p 8080:80 -p 9000:9000 -p 8000:8000 -p 3306:3306 \
emasalari/symfony-apache2 bash