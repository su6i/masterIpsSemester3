# programmationAvancee

## JavaEE:   
### Maven project:   

#### Java execution commands:   
---------------------------------------
export CLASSPATH=~/eclipse/mysql-connector/mysql-connector-java-8.0.21.jar:.   
echo $CLASSPATH   
javac EX1_20.java   
java EX1_20   
mysql -h mysql.etu.umontpellier.fr -u e20190009681 -p e20190009681 < ./monuments_insertion.sql   
delete from lieu; -- delete all rows in the table "lieu"   
use e20190009681;   

#### MySQL Commands:   
---------------------------------------   

mysql -h mysql.etu.umontpellier.fr -u e20190009681 -p e20190009681 < ./monuments_insertion.sql   
delete from lieu; -- delete all rows in the table "lieu"   
use e20190009681;   

Distortion des langages
Patron de Conception
DAO = Data Access Object

Specification JavaEE, JPA: Java Persistence API (Hibernate)

#### Spring boot:   
---------------------------------------   

Install Spring boot on Mac with brew command:
`brew tap pivotal/tap`   
`brew install springboot maven`   
`brew cask install springtoolsuite`   

Homebrew will install *spring* to `/usr/local/bin`.
