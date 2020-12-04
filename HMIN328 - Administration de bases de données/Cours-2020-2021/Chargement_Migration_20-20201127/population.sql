-- Amir SHIRALI POUR

set serveroutput on;
set long 40000
set head off echo off

-- 1.1  Schéma partiel de la base de données Communes
create table population(
    codeinsee varchar(6), 
    annee integer, 
    val_population integer,
    constraint pk_population primary key (codeinsee, annee)
);
    


-- 1.2  Construction de l'ensemble des tables et chargement
sqlldr userid=e20190009681/Ema241199@oracle.etu.umontpellier.fr:1523/pmaster control=population.ctl

-- 1.3 Elémentsà rendre dans le devoir
-- 3. sortie du résultat de la requête sur la vue usertables indiquant en particulier 
-- le nombre detuples inséré et le nombre de blocs de données alloués

ANALYZE TABLE Population COMPUTE STATISTICS;

select NUM_ROWS from user_tables where table_name = 'POPULATION';
-- select count(*) from POPULATION; -- Deuxième façon

select table_name , BLOCKS from user_tables where table_name = 'POPULATION';


-- 2. Migration de schémas
-- 2.1.2Code PL/SQLà rendre

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
cursor c is select DBMS_METADATA.GET_DDL('TABLE',upper(nomtable), USER) as eachTable from user_tables where table_name = upper(nomtable);
begin
for line in c
    loop
        dbms_output.put_line(line.eachTable);
    end loop;
end;
/

EXEC UneTable('population');




-- 2. Vous construirez une fonction nommée ToutesTables qui renvoie les ordres de création de
-- toutes les tables (sans les informations concernant le stockage) d'un schéma utilisateur dont le
-- nom est passé en paramètres d'entrée (variable de sortiede type CLOB). Vous utiliserez, pour
-- ce faire, une requête de la forme (ici schéma utilisateur HR) :

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




-- 3. Vous construirez une nouvelle fonction nommée ToutesTablesInfos qui renvoie les ordres
-- de création (plus l’information concernant l’organisation logique et les paramètres associés
-- au stockage physique) de toutes les tables d’un schéma utilisateur dont le nom est passé en
-- paramètre d’entrée.


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

-- 2.1.3  Informations associéesà l’organisation physique de la table
-- EXEC UneTable('population');


  CREATE TABLE "E20190009681"."POPULATION"
   (	"CODEINSEE" VARCHAR2(6),
	"ANNEE" NUMBER(*,0),
	"VAL_POPULATION" NUMBER(*,0),
	 CONSTRAINT "PK_POPULATION" PRIMARY KEY ("CODEINSEE", "ANNEE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  -- PCTFREE 10: block utilization parameter, est le purcentage d’espace réservé dans chaque bloc pour des misesà jouràvenir est égal à 10%
  -- INITRANS 2: nombre initial d’entrées de transactions pré-allouéesà un bloc qui peut être un nombre entre 1 et 255. 
        -- Dans ce cas est égal à 2 parce que c'est un segment d’index.
  -- MAXTRANS 255: nombre maximum de transactions concurrentes qui peuvent modifier unbloc allouéà une table que est égal à 255
  -- COMPUTE STATISTICS: Génère des statistiques pour les colonnes ("CODEINSEE", "ANNEE").

  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  -- INITIAL 65536: Taille en octets du premier ”extent”. Lorsque vous créez l'objet de schéma Oracle alloue un espace de 65536 octets pour cette extension
  -- NEXT 1048576:  La taille en octets du second extentsion qui est égal à 1048576 octets ou presque 1 Mega octets.
  -- MINEXTENTS 1:  une extent allouéà la création
  -- MAXEXTENTS 2147483645: Nombre maximal d'extentsion que l'objet peut avoir. est égal à 2147483645.
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  -- PCTINCREASE 0:  pourcentage d’augmentation entre 2 extents est mis à 0 afin de réduire la fragmentation sur le tablespace.
  -- FREELISTS 1: Chaque segment a une liste libre (dans l'en-tête de segment) qui garde la trace des blocs de données sous le high-water mark.
  -- FREELIST GROUPS 1: On a une FREELIST GROUPS
  BUFFER_POOL DEFAULT 
  -- La clause BUFFER_POOL est utilisée pour définir le pool de mémoire tampon par défaut pour un objet.
  FLASH_CACHE DEFAULT 
  -- un argument de table et d'index qui ressemble à l'affectation d'objets à high-use au pool KEEP.
  CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "DATA_ETUD"  ENABLE
   ) SEGMENT CREATION IMMEDIATE
   -- Le segment de table est créé dans le cadre de cette instruction CREATE TABLE.
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
  -- PCTUSED = 40, Oracle n'ajoutera pas de nouvelles lignes au bloc à moins que suffisamment de lignes ne soient supprimées 
  -- du bloc pour qu'il tombe en dessous de 40% utilisé.
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "DATA_ETUD"


-- 2.1.4  Informations sur les organisations logique/physique

-- 1
-- pop infos from dbasegments
--- segment_name='POPULATION' and owner='E20190009681'
create or replace procedure pop_infos_1 as  
CURSOR myCursor IS select owner, segment_name, segment_type, bytes, blocks, tablespace_name, partition_name, extents from dba_segments where segment_name='POPULATION' and owner='E20190009681';
begin
    FOR line IN myCursor
    LOOP
        DBMS_OUTPUT.PUT_LINE(chr(10) ||  'Owner: ' || line.owner || chr(10) || ' Segment_name: '|| line.segment_name || chr(10) || ' Segment type: '|| line.segment_type || chr(10) || ' Bytes: '|| line.bytes || chr(10) || ' Blocks: '|| line.blocks || chr(10) || ' Tablespace name: '|| line.tablespace_name || chr(10) || ' Partition name: '|| line.partition_name || chr(10) || ' Eextentions: '|| line.extents);
    END LOOP;
end;
/

set serveroutput on
exec pop_infos_1;

-- 2
-- Seulement sur population
-- pop blocks in bytes from dbasegments
create or replace procedure pop_infos_2 as
CURSOR myCursor IS select sum(bytes) octets, sum(blocks) blocs from dba_segments where owner='E20190009681' and segment_name='POPULATION';
begin
    FOR line IN myCursor
    LOOP
        DBMS_OUTPUT.PUT_LINE( chr(10) ||  'Bytes: ' || line.octets || chr(10) ||  ' Blocks: ' || line.blocs);
    END LOOP;
end;
 / 

set serveroutput on
exec pop_infos_2;

-- 3
-- Infos populations sur jointure des deux tables
-- dbasegments and dbaextends
create or replace procedure pop_infos_3 as  --- resultats en une ligne et on pourra voir extents=nombre de ligne requete depuis dba_extends
CURSOR myCursor IS select DISTINCT ds.owner ow, ds.segment_name sn, ds.segment_type st, ds.bytes octets, ds.blocks blocs, ds.tablespace_name tsn, ds.partition_name pn, extents from dba_segments ds, dba_extents de where ds.segment_name=de.segment_name and ds.segment_name='POPULATION' and ds.owner='E20190009681';
begin
    FOR line IN myCursor
    LOOP
        DBMS_OUTPUT.PUT_LINE( chr(10) ||  'Owner: ' || line.ow || chr(10) ||  ' Segment name: '|| line.sn || chr(10) ||  ' Segment type: '|| line.st || chr(10) ||  ' Bytes: '|| line.octets || chr(10) ||  ' Blocks: '|| line.blocs || chr(10) ||  ' Tablespace name: '|| line.tsn || chr(10) ||  ' Partition name: '|| line.pn || chr(10) ||  ' Eextentions: '|| line.extents);
    END LOOP;
end;
/

set serveroutput on
exec pop_infos_3;

-- 4
-- pop infos from dbasegments
--- Avec la somme de des tailles des segments et des blocks et des exetensions pour chaque utilisateur

CREATE OR REPLACE PROCEDURE info_population_4 IS
CURSOR myCursor IS select owner, segment_type, count(segment_name),
sum(bytes) octets, sum(blocks) blocs,
sum(extents) extensions from dba_segments group by owner, segment_type order
by octets asc;
BEGIN
    FOR line IN myCursor
    LOOP
        DBMS_OUTPUT.PUT_LINE(chr(10) || 'owner: ' || line.owner || chr(10) ||  ' Segment type: ' || line.segment_type || chr(10) || ' Bytes: ' || line.octets ||
    chr(10) || ' Blocks: ' || line.blocs);
    END LOOP;
END;
/

set serveroutput on
exec info_population_4;

-- 2.2  Export XML Schéma et données
-- 2.2.1  Fonction GETXML du paquetage DBMSMETADATA
-- 1. Vous construirez une fonction nommée TableXML qui renvoie la description XML d’une table
-- en particulier du schéma utilisateur (nom de la table passé en paramètre d’entrée)


CREATE OR REPLACE FUNCTION TableXML(v_tableName VARCHAR) RETURN CLOB IS
CURSOR myCursor IS SELECT dbms_metadata.get_ddl('TABLE',
UPPER(v_tableName)) AS ligne FROM user_tables;
v_clob CLOB;
BEGIN
FOR lig IN myCursor
LOOP
v_clob := lig.ligne;
END LOOP;
return v_clob;
END;
/

set serveroutput on
select TableXML('population') from dual;


-- 2. Vous exploiterez le paquetage DBMSXMLGEN pour avoir également les feuilles de l’arborescence 
-- XML (données) pour une table donnée. Construisez également une fonctionà ce sujet
-- TableDataXMLqui prend en argument le nom d’une table mais aussi une condition 
-- de filtre(clause where).

create or replace function TableDataXML (attribut varchar, nomTable varchar,filtre varchar) 
return clob
is
begin
declare
v_clob clob;
cursor c is select dbms_xmlgen.getxml(dbms_xmlgen.newcontext('select ' || attribut || ' from ' || nomTable || ' where ' || filtre)) as requete from dual;
begin

for var in c
loop
v_clob := v_clob || var.requete;
end loop;
return v_clob;
end;
end;
/


set serveroutput on
select TableDataXML('fonction', 'EMP','fonction=''directeur'' ') from dual;



-- 3. Export des données
-- Vous définirez une procédure qui permet de renvoyer les données correspondant au
-- codeinsee, nom de la commune, valeur de la population en 2000, et valeur de la population en 2010
-- au format tabulé.

create or replace procedure factory_population
is
cursor c is select p.codeinsee, val_population, annee, nomcommin from population p, commune c where p.codeinsee = c.codeinsee and annee in (2000,2010);
begin
for line in c
loop 
dbms_output.put_line(line.codeinsee||chr(9)||line.val_population||chr(9)||line.annee||chr(9)||line.nomcommin||chr(13)) ;
end loop ;
exception
when others then dbms_output.put_line('erreurs') ;
end ;
/


set serveroutput on
exec factory_population;





