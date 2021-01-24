-- to drop table with foreign keys in SQL
SET FOREIGN_KEY_CHECKS = 0;


-- monument(codeInsee varchar(5), dep varchar(4), nomCom varchar(46), longitude float, latitude float)
-- avec Lieu(dep)⊆Departement(dep)

-- An example tuple: 
-- drop table lieu;
create table if not exists lieu (
	codeInsee varchar(5), 
    dep varchar(4), 
    nomCom varchar(46), 
    longitude float, 
    latitude float,
	constraint pk_lieu primary key (codeInsee),
    constraint fk_lieu foreign key (dep) references departement(dep)
);

-- We need to add this line after that we created departement table, because we have 3 tables that are related with a foreign key.
-- ,constraint fk_lieu foreign key (dep) references departement(dep)

-- ------------------------------------------------------------------------------------------
-- Departement(dep varchar(4), chefLieu varchar(46), nomDep varchar(30), reg varchar(4)) avec
-- Departement(chefLieu)⊆Lieu(codeInsee)

-- An example tuple: insert into departement values ('12','AVEYRON','12202');                                      
-- drop table departement;

create table if not exists departement(
	dep varchar(4), 
    chefLieu varchar(46), 
    nomDep varchar(30), 
    reg varchar(4),
	constraint pk_departement primary key (dep),
    constraint fk_departement foreign key (chefLieu) references lieu(codeInsee)
);


-- ------------------------------------------------------------------------------------------
-- Monument(codeM varchar(5), nomM varchar(25) proprietaire varchar(10), typeMonument varchar(16), longitude float, latitude float, codeLieu varchar(5))
-- avec Monument(codeLieu)⊆Lieu(codeInsee)

-- An example tuple: insert into monument values ('spfb05nwqmvu','HOTEL DE GANGES','PRIVE','HOTEL_PARTICULIER',3.87639,43.611334,'34172');  
-- drop table monument;

create table if not exists monument(
	codeM varchar(5), 
    nomM varchar(25), 
    proprietaire varchar(10), 
    typeMonument varchar(16), 
    longitude float, 
    latitude float, 
    codeLieu varchar(5),
	constraint pk_monument primary key (codeM),
    constraint fk_monument foreign key (codeLieu) references lieu(codeInsee)
);


-- ------------------------------------------------------------------------------------------
-- Celebrite(numCelebrite integer, nom varchar(16), prenom varchar(16), nationalite varchar(10), epoque varchar(6))

create table if not exists celebrite (
	numCelebrite integer, 
    nom varchar(16), 
    prenom varchar(16), 
    nationalite varchar(10), 
    epoque varchar(6),
    url varchar(255),
    image varchar(255),
    constraint pk_celebrite primary key (numCelebrite)
);



-- ------------------------------------------------------------------------------------------
-- AssocieA(codeM varchar(5), numCelebrite integer)
-- avec AssocieA(codeM)⊆Monument(codeM) et AssocieA(numCelebrite)⊆Celebrite(numCelebrite)




-- to activate foreign keys verification in SQL
SET FOREIGN_KEY_CHECKS = 1;


insert ignore into monument(code_m, latitude, longitude, nom_m, proprietaire, type_monument, code_lieu) select codeM, longitude, latitude, nomM, proprietaire, typeMonument, codeLieu from pro_v1.monument; 
insert ignore into celebrite(code_m, latitude, longitude, nom_m, proprietaire, type_monument, code_lieu) select codeM, longitude, latitude, nomM, proprietaire, typeMonument, codeLieu from pro_v1.monument; 
insert ignore into departement(num_dep, nom_dep, chef_lieu) select num_dep, nom_dep, chef_lieu from pro_v1.departement; 
insert ignore into monument(code_m, latitude, longitude, nom_m, proprietaire, type_monument, code_lieu) select code_m, longitude, latitude, nom_m, proprietaire, type_monument, code_lieu from pro_v1.monument; 


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


create table if not exists users (
    email varchar(255),
    password varchar(255),
    active boolean,
    constraint pk_users primary key (email)
);

create table if not exists roles (
    role varchar(255),
    constraint pk_roles primary key (role)
);

create table if not exists users_roles (
    email varchar(255),
    role varchar(255),
    constraint pk_users_roles primary key (email, role)
);

 insert into users values ("amir@gmail.com",md5("123"),1),("voy@gmail.com",md5("123"),1), ("tour@gmail.com",md5("123"),1);
 insert into roles values ("ADMIN"),("VOYAGISTE"),("TOURISTE");
 insert into users_roles values ("tour@gmail.com","TOURISTE"),("amir@gmail.com","ADMIN"),("voy@gmail.com","VOYAGISTE");



 uploadDir=/resources/image

#Enable multipart uploads
spring.servlet.multipart.enabled=true
# Threshold after which files are written to disk.
spring.servlet.multipart.file-size-threshold=2KB
# Max file size.
spring.servlet.multipart.max-file-size=200MB
# Max Request Size
spring.servlet.multipart.max-request-size=215MB


//    @Autowired
//    private DataSource dataSource;

    
	private JdbcTemplate jdbcTemplate;

	
	public void ContactDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
