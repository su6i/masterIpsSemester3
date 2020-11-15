-- mysql -h mysql.etu.umontpellier.fr -u p00000009432 -p p00000009432
-- silong
drop table personne; 
drop table ville;

create table Ville (nom varchar(12), pays varchar(15), primary key(nom)) engine=INNODB;


insert into Ville values ('Montpellier','France');
insert into Ville values ('Paris','France');
insert into Ville values ('Berlin','Allemagne');
insert into Ville values ('Angers','France');

create table Personne (numSS varchar(12), nom varchar(15), dateN date, genre varchar(1), primary key (numSS)) engine=INNODB;

alter table Personne add localisation varchar(12);
alter table Personne add constraint personne_fk foreign key (localisation) references Ville (nom);

insert into Personne values ('1234','Marie','1988-06-02','F','Montpellier');
insert into Personne values ('2345','Paul','1998-06-06','M','Paris');
insert into Personne values ('3456','Elise','2008-06-02','F','Berlin');
insert into Personne values ('1123','Eloi','1978-09-02','M','Angers');


CREATE TABLE Car (id int PRIMARY KEY NOT NULL AUTO_INCREMENT, make varchar(100) NOT NULL, 
	model varchar(100) NOT NULL );
