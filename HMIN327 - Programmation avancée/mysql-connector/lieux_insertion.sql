drop table lieu;

create table lieu(
	codePostal varchar(5), 
	ville varchar(20),
	longitude float,
	latitude float,
	n_departement varchar(4),
	constraint pk_lieu primary key (codePostal)
);



insert into departement values ('12','AVEYRON','12202');                                      
drop table departement;

create table departement(
	n_departement varchar(4),
	nom_departement varchar(20),
	code_inse varchar(5), 
	constraint pk_departement primary key (n_departement)
);



insert into monument values ('spfb05nwqmvu','HOTEL DE GANGES','PRIVE','HOTEL_PARTICULIER',3.87639,43.611334,'34172');  
drop table monument;
create table monument(
	geohash varchar(12), 
	nom_monument varchar(25),
	proprietaire varchar(10),
	type_monument varchar(16),
	longitude float,
	latitude float,
	codeLieu varchar(5),
	constraint pk_monument primary key (geohash)
);



insert into  lieu values ('34172','MONTPELLIER',3.876716,43.610769,'34');
insert into  lieu values ('34198','PEROLS',3.954211,43.563782,'34');
insert into  lieu values ('34199','PEZENAS',3.423193,43.461531,'34');
insert into  lieu values ('34142','LODEVE',3.313975,43.73366,'34');
insert into  lieu values ('34032','BEZIERS',3.215795,43.344233,'34');
insert into  lieu values ('34129','LATTES',3.896473,43.567296,'34');			   
insert into  lieu values ('30334','UZES',4.419946,44.01211,'30');		     						 
insert into  lieu values ('30189','NIMES',4.360054,43.836699,'30');	


  						  



  						   
