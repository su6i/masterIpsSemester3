-- 1.1  Schéma partiel de la base de donn ́ees Communes
create table population(
    codeinsee varchar(6), 
    annee integer, 
    val_population integer,
    constraint pk_population primary key (codeinsee, annee)
);
    


-- 1.2  Construction de l’ensemble des tables et chargement
sqlldr userid=e20190009681/Ema241199@oracle.etu.umontpellier.fr:1523/pmaster control=population.ctl

-- 1.3 El ́ements `a rendre dans le devoir
-- 3. sortie du r ́esultat de la requˆete sur la vue usertables indiquant en particulier 
-- le nombre detuples ins ́er ́e et le nombre de blocs de donn ́ees allou ́es

select NUM_ROWS from user_tables where table_name = 'POPULATION';
-- select count(*) from POPULATION; -- Deuxième façon

ANALYZE TABLE Population COMPUTE STATISTICS;
select table_name , BLOCKS from user_tables where table_name = 'POPULATION';


-- 2. Migration de schémas


create or replace procedure uneTable as
set long 40000
select DBMS_METADATA.GET_DDL('TABLE','COMMUNE') from DUAL;

select DBMS_METADATA.GET_DDL('TABLE','REGION', USER) from user_tables;


select s.sid, s.osuser, substr(a.sql_fulltext,1), plan_hash_value from v$session s join v$sqlarea a on a.hash_value = s.prev_hash_value ;