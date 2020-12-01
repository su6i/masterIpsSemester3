
create table population(
    codeinsee varchar(6), 
    annee integer, 
    val_population integer,
    constraint pk_population primary key (codeinsee, annee)
);
    



sqlldr userid=e20190009681/Ema241199@oracle.etu.umontpellier.fr:1523/pmaster control=population.ctl

select count(*) from POPULATION;


ANALYZE TABLE Population COMPUTE STATISTICS;
select table_name , BLOCKS from user_tables where table_name = 'POPULATION';
