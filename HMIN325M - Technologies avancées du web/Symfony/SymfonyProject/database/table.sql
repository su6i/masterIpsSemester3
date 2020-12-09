CREATE DATABASE IF NOT EXISTS superventes;
USE superventes;
CREATE TABLE IF NOT EXISTS membres ( id smallint unsigned not null auto_increment, 
            mail varchar(30) not null,
            password varchar(40) not null,
            nom varchar(30) not null,
            prenom varchar(20) not null,
            primary key (id)
            );
            
INSERT INTO membres ( id, mail, password, nom, prenom ) VALUES 
                    (null, 'claire@delune.fr', 'e10adc3949ba59abbe56e057f20f883e', 'Delune', 'Claire'),
                    (null, 'pompidor@lirmm.fr', '08a88f35872f266315d3323f41597da4', 'Pompidor', 'Pierre'),
                    (null, 'meynard@lirmm.fr', '0674272bac0715f803e382b5aa437e08', 'Meynard', 'Michel');



CREATE TABLE IF NOT EXISTS `superventes`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `json_data` JSON NOT NULL,
  PRIMARY KEY (`id`)
);


LOAD DATA INFILE 'products.json'
INTO TABLE `superventes`.`products` (`json_data`);

LOAD DATA LOCAL INFILE "products.json" INTO TABLE `superventes`.`products`;


mysql --local-infile -uamir -pamir superventes

SELECT [FirstName], 
    [LastName], 
    [Country], 
    COUNT(*) AS CNT
FROM [SampleDB].[dbo].[Employee]
GROUP BY [FirstName], 
      [LastName], 
      [Country]
HAVING COUNT(*) > 1;



CREATE TABLE IF NOT EXISTS data ( 
    name varchar(255),
    price varchar(255),
    image varchar(255),
    category varchar(255),
    type varchar(255),
    description JSON
);


WITH CTE AS(
   SELECT name, price, image, category, type, description,
       RN = ROW_NUMBER()OVER(PARTITION BY name ORDER BY name)
   FROM products.data
)
select * from cte WHERE RN > 1;
DELETE FROM CTE WHERE RN > 1;


create table products select * from data;

