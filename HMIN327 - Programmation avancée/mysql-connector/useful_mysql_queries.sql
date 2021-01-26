create table project_generated.commune select * from commune;       -- create table in another database
create table backup_commune select * from commune;                  -- create a table with the data of another table
insert into backup_commune select * from commune;                   -- insert data to a table from the result of another table
delete from backup_commune;                                         -- delete all tuples of a table
ALTER TABLE commune MODIFY longitude decimal(13,11);                -- change data type of a column
delete from commune where latitude is null;                         -- delete with condition
select * from commune limit 10;                                     -- limit the number of result tuples
update table commune set latitude = longitude where new !='NULL';   -- set a value
alter table commune drop column libelle;                            -- drop a column
source new.sql                                                      -- import sql file
select database() from dual;                                        -- display selected database
insert into lieu(codeInsee, nomCom, longitude, latitude) select codeinsee, nom_com, longitude, latitude from commune;        -- insert selected columns into another table
insert ignore into lieu(codeInsee, nomCom, longitude, latitude) select codeinsee, nom_com, longitude, latitude from commune;        -- insert data but ignore if already exists that data
mysqldump -uUsername -pPassword project_generated >  project_generated.sql -- dump (export) an entire database to a file
update lieu set dep = SUBSTR(code_insee, 1, 2);                     -- set a part of a value in a column from another column
update lieu_backup set dep = SUBSTR(code_insee, 1, 3) where code_insee like '97%'; -- set a part of a value in a column from another column with a condition
update departement g set chef_lieu = (select chefLieu from project_initial.departement i where g.num_dep = i.chefLieu);
insert ignore into monument(code_m, latitude, longitude, nom_m, proprietaire, type_monument, code_lieu) select codeM, longitude, latitude, nomM, proprietaire, typeMonument, codeLieu from project_initial.monument; 

create table backup_programmation_java.departement select * from departement;
create table backup_programmation_java.monument select * from monument;
create table backup_programmation_java.lieu select * from lieu;
create table backup_programmation_java.celebrite select * from celebrite;
create table backup_programmation_java.monument_celebrite select * from monument_celebrite;
create table backup_programmation_java.data select * from data;

insert ignore into departement select * from backup_programmation_java.departement;
insert ignore into monument select * from backup_programmation_java.monument;
insert ignore into lieu select * from backup_programmation_java.lieu;
insert ignore into celebrite select * from backup_programmation_java.celebrite;
insert ignore into monument_celebrite select * from backup_programmation_java.monument_celebrite;
insert ignore into data select * from backup_programmation_java.data;




SELECT COUNT(*) FROM ( SELECT DISTINCT prenom, nom FROM celebrite);

update lieu        set parent_url = 'http://localhost:8080/json/lieux/page/1';
update monument    set parent_url = 'http://localhost:8080/json/monuments/page/1';
update celebrite   set parent_url = 'http://localhost:8080/json/celebrities/page/1';
update departement set parent_url = 'http://localhost:8080/json/departements/page/1';

update monument    set url = CONCAT('http://localhost:8080/json/monuments/', code_m);
update celebrite   set url = CONCAT('http://localhost:8080/json/celebrities/', num_celebrite);
update lieu        set url = CONCAT('http://localhost:8080/json/lieux/', code_insee);
update departement set url = CONCAT('http://localhost:8080/json/departements/', num_dep);
update lieu        set departement_url = CONCAT('http://localhost:8080/json/departements/',dep);

insert into lieu(code_insee, nom_com, longitude, latitude) select codeInsee, nomCom, longitude, latitude from project_initial.lieu; 

insert into lieu select * from project_initial.lieu where codeInsee = '34172';
insert ignore into celebrite select * from backup_programmation_java.celebrite;
insert ignore into celebrite(num_celebrite, nom, prenom, nationalite, epoque, url, parent_url, image) select num_celebrite, nom, prenom, nationalite, epoque, url, parent_url, image from backup_programmation_java.celebrite;
update celebrite set image =concat('/image/',num_celebrite,'.jpg');




insert ignore into monument(code_m, latitude, longitude, nom_m, proprietaire, type_monument, code_lieu, parent_url, url) select code_m, longitude, latitude, nom_m, proprietaire, type_monument, code_lieu, parent_url, url from backup_programmation_java.monument; 
insert ignore into celebrite(num_celebrite, epoque, nationalite, nom, prenom,parent_url, url, image) select num_celebrite, epoque, nationalite, nom, prenom,parent_url, url, image from backup_programmation_java.celebrite; 
insert ignore into departement(num_dep, nom_dep, chef_lieu,parent_url, url) select num_dep, nom_dep, chef_lieu, parent_url, url from backup_programmation_java.departement; 
insert ignore into lieu(code_insee, latitude, longitude, nom_com, parent_url, url, dep) select code_insee, longitude, latitude, nom_com, parent_url, url, dep from backup_programmation_java.lieu; 

insert into celebrite(epoque, nationalite, nom, prenom) select epoque, nationalite, nom, prenom from backup_programmation_java.celebrite; 

drop table departement;
drop table monument;
drop table lieu;
drop table celebrite;

insert into lieu select * from pro_v1.lieu;
insert into departement select * from pro_v1.departement;
insert into monument select * from pro_v1.monument;
insert into celebrite select * from pro_v1.celebrite;

select * from departement limit 5;
select * from monument limit 5;
select * from lieu limit 5;
select * from celebrite limit 5;


create table monument  select * from backup_programmation_java.monument;
