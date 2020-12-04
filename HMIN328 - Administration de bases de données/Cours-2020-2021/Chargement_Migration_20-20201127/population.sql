set serveroutput on;
set long 40000
set head off echo off

-- 1.1  Schéma partiel de la base de donn ́ees Communes
create table population(
    codeinsee varchar(6), 
    annee integer, 
    val_population integer,
    constraint pk_population primary key (codeinsee, annee)
);
    


-- 1.2  Construction de l'ensemble des tables et chargement
sqlldr userid=e20190009681/Ema241199@oracle.etu.umontpellier.fr:1523/pmaster control=population.ctl

-- 1.3 El ́ements `a rendre dans le devoir
-- 3. sortie du r ́esultat de la requˆete sur la vue usertables indiquant en particulier 
-- le nombre detuples ins ́er ́e et le nombre de blocs de donn ́ees allou ́es

ANALYZE TABLE Population COMPUTE STATISTICS;

select NUM_ROWS from user_tables where table_name = 'POPULATION';
-- select count(*) from POPULATION; -- Deuxième façon

select table_name , BLOCKS from user_tables where table_name = 'POPULATION';


-- 2. Migration de schémas
-- 2.1.2Code PL/SQL `a rendre

-- avec la table dual

set long 40000

create or replace procedure UneTable(table_name in varchar2)
is
    v_clob clob := null;
begin
    select DBMS_METADATA.GET_DDL('TABLE',upper(table_name)) into v_clob from dual ;
    dbms_output.put_line('ce que tu veux afficher' || v_clob);

end;
/

EXEC UneTable('population');

-- avec la table user_tables
set serveroutput on
create or replace procedure UneTable(nomtable in varchar2) as
cursor c is select DBMS_METADATA.GET_DDL('TABLE',upper(nomtable), USER) as ligne from user_tables where table_name = upper(nomtable);
begin
for line in c
    loop
        dbms_output.put_line(line.ligne);
    end loop;
end;
/

EXEC UneTable('population');




-- 2. Vous construirez une fonction nomm ́ee ToutesTables qui renvoie les ordres de cr ́eation de
-- toutes les tables (sans les informations concernant le stockage) d'un sch ́ema utilisateur dont le
-- nom est pass ́e en param`etres d'entr ́ee (variable de sortiede type CLOB). Vous utiliserez, pour
-- ce faire, une requˆete de la forme (ici sch ́ema utilisateur HR) :

-- -- select dbms_metadata.get_ddl('TABLE',TABLE_NAME,'HR') FROM DBA_TABLES WHERE OWNER ='HR';

-- TouteTable sur le shema dba_tables

create or replace FUNCTION ToutesTables(schema_user in varchar2) RETURN clob as
v_clob clob := null;
cursor c is select DBMS_METADATA.GET_DDL('TABLE',upper(table_name), OWNER) as eachTable from dba_tables where owner = upper(schema_user);

BEGIN
   DBMS_METADATA.set_transform_param (DBMS_METADATA.session_transform, 'TABLESPACE', false);
   DBMS_METADATA.set_transform_param (DBMS_METADATA.session_transform, 'SQLTERMINATOR', true);
   DBMS_METADATA.set_transform_param (DBMS_METADATA.session_transform, 'PRETTY', true);
   DBMS_METADATA.set_transform_param (DBMS_METADATA.session_transform, 'SEGMENT_ATTRIBUTES', false);
   DBMS_METADATA.set_transform_param (DBMS_METADATA.session_transform, 'STORAGE', false);
for line in c
    loop
        v_clob := v_clob || line.eachTable;
    end loop;
    RETURN v_clob;
end;
/

select ToutesTables('E20190009681') FROM dba_tables where table_name = 'population';




-- 3. Vous construirez une nouvelle fonction nomm ́eeToutesTablesInfosqui renvoie les ordres
-- de cr ́eation (plus l’information concernant l’organisation logique et les param`etres associ ́es
-- au stockage physique) de toutes les tables d’un sch ́ema utilisateur dont le nom est pass ́e en
-- param`etre d’entr ́ee.


set serveroutput on
create or replace FUNCTION ToutesTablesInfos(schema_user in varchar2) RETURN clob as
v_clob clob := null;
cursor c is select DBMS_METADATA.GET_DDL('TABLE',upper(table_name), OWNER) as eachTable from dba_tables where owner = upper(schema_user);
begin
for line in c
    loop
        v_clob := v_clob || line.eachTable;
    end loop;
    RETURN v_clob;
end;
/

select ToutesTablesInfos('E20190009681') FROM dba_tables;

-- 2.1.3  Informations associ ́ees `a l’organisation physique de la table


  CREATE TABLE "E20190009681"."POPULATION"
   (	"CODEINSEE" VARCHAR2(6),
	"ANNEE" NUMBER(*,0),
	"VAL_POPULATION" NUMBER(*,0),
	 CONSTRAINT "PK_POPULATION" PRIMARY KEY ("CODEINSEE", "ANNEE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL
DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "DATA_ETUD"  ENABLE
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "DATA_ETUD"
  




----------------------------



----------------------------










