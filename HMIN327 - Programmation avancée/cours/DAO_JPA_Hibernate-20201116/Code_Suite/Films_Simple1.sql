CREATE TABLE Acteur1  (idActeur VARCHAR(4) NOT NULL,
                       nom VARCHAR (30) NOT NULL,
                       PRIMARY KEY (idActeur),
                       UNIQUE (nom));

CREATE TABLE Film1  (idFilm INTEGER NOT NULL,
                    titre    VARCHAR (50) NOT NULL,
                    PRIMARY KEY (idFilm)
                   );

CREATE TABLE Joue1 (idFilm INTEGER NOT NULL,
                   idActeur VARCHAR(4) NOT NULL,
                   PRIMARY KEY (idFilm, idActeur),
                   FOREIGN KEY (idFilm) REFERENCES Film1(idFilm),
                   FOREIGN KEY (idActeur) REFERENCES Acteur1(idActeur));



INSERT INTO Acteur1 VALUES ('6','Cameron');
INSERT INTO Acteur1 VALUES ('3','Hitchcock');
INSERT INTO Acteur1 VALUES ('4','Scott');
INSERT INTO Acteur1 VALUES ('5','Weaver');
INSERT INTO Acteur1 VALUES ('9','Tarkovski');
INSERT INTO Acteur1 VALUES ('10','Woo');
INSERT INTO Acteur1 VALUES ('11','Travolta');
INSERT INTO Acteur1 VALUES ('12','Cage');
INSERT INTO Acteur1 VALUES ('13','Burton');
INSERT INTO Acteur1 VALUES ('14','Depp');
INSERT INTO Acteur1 VALUES ('15','Stewart');
INSERT INTO Acteur1 VALUES ('16','Novak');
INSERT INTO Acteur1 VALUES ('17','Mendes');
INSERT INTO Acteur1 VALUES ('18','Spacey');
INSERT INTO Acteur1 VALUES ('19','Bening');


INSERT INTO Film1  VALUES (1,'Vertigo');
INSERT INTO Film1 VALUES (2,'Alien');
INSERT INTO Film1 VALUES (3,'Titanic');
INSERT INTO Film1 VALUES (4,'Sacrifice');
INSERT INTO Film1 VALUES (5,'Volte/Face');
INSERT INTO Film1 VALUES (6,'Sleepy Hollow');
INSERT INTO Film1 VALUES (7,'American Beauty');
INSERT INTO Film1 VALUES (8,'Impitoyable');


INSERT INTO Joue1 VALUES (1,'15');
INSERT INTO Joue1 VALUES (1,'16');
INSERT INTO Joue1 VALUES (2,'5');
INSERT INTO Joue1 VALUES (3,'9');
INSERT INTO Joue1 VALUES (3,'11');
INSERT INTO Joue1 VALUES (5,'11');
INSERT INTO Joue1 VALUES (5,'12');
INSERT INTO Joue1 VALUES (6,'14');



