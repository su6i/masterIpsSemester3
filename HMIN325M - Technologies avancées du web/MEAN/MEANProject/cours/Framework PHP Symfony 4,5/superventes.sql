CREATE DATABASE IF NOT EXISTS superventes;
USE superventes;
CREATE TABLE IF NOT EXISTS membres ( id smallint unsigned not null auto_increment,
                                     mail varchar(30) not null,
				     password varchar(40) not null,
				     nom varchar(30) not null,
                                     prenom varchar(20) not null,
				     primary key (id));
INSERT INTO membres ( id, mail, password, nom, prenom ) VALUES (null, 'claire.delune@server.fr', 'e10adc3949ba59abbe56e057f20f883e', 'Delune', 'Claire'), (null, 'pompidor@lirmm.fr', '08a88f35872f266315d3323f41597da4', 'Pompidor', 'Pierre'), (null, 'meynard@lirmm.fr', '0674272bac0715f803e382b5aa437e08', 'Meynard', 'Michel');
