create table commune as select * from P00000009432.COMMUNE;

ALTER TABLE COMMUNE ADD CONSTRAINT compk PRIMARY KEY(codeinsee);