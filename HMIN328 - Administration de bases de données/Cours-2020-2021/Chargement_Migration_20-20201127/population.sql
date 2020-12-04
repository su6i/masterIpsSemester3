-- Amir SHIRALI POUR

SET serveroutput ON;
SET long 40000
SET head OFF echo OFF


-- ---------------------------------------------------
-- 1.1  Schéma partiel de la base de données Communes

CREATE TABLE population(
    codeinsee VARCHAR(6),
    annee INTEGER,
    val_population INTEGER,
    CONSTRAINT pk_population PRIMARY KEY (codeinsee, annee)
);
    

-- ---------------------------------------------------
-- 1.2  Construction de l'ensemble des tables et chargement
sqlldr userid=e20190009681/MyPassWord@oracle.etu.umontpellier.fr:1523/pmaster control=population.ctl

-- 1.3 Elémentsà rendre dans le devoir
-- 3. sortie du résultat de la requête sur la vue usertables indiquant en particulier 
-- le nombre detuples inséré et le nombre de blocs de données alloués

ANALYZE TABLE population COMPUTE STATISTICS;

-- Premère façon
SELECT table_name, num_rows FROM user_tables WHERE table_name = 'POPULATION';

-- Deuxième façon
SELECT COUNT(*) FROM population; 

SELECT table_name , blocks FROM user_tables WHERE table_name = 'POPULATION';

-- ---------------------------------------------------
-- 2. Migration de schémas
-- 2.1.2Code PL/SQLà rendre

-- avec la table dual

CREATE OR REPLACE PROCEDURE UneTable(vTableName IN VARCHAR2)
IS
    vClob CLOB := NULL;
BEGIN
    SELECT dbms_metadata.Get_ddl('TABLE',Upper(vTableName)) INTO vClob FROM dual ;
    dbms_output.Put_line('Table details:' || vClob);
END;
/

EXEC UneTable('population');


-- ---------------------------------------------------
-- avec la table user_tables

CREATE OR REPLACE PROCEDURE UneTable(vTableName IN VARCHAR2) AS
CURSOR myCursor IS SELECT dbms_metadata.Get_ddl('TABLE',Upper(vTableName), USER) AS eachTable FROM user_tables WHERE table_name = Upper(vTableName);
BEGIN
    FOR line IN myCursor
    LOOP
        dbms_output.Put_line(line.eachTable);
    END LOOP;
END;
/

SET serveroutput ON
EXEC UneTable('population');


-- ---------------------------------------------------
-- 2. Vous construirez une fonction nommée ToutesTables qui renvoie les ordres de création de
-- toutes les tables (sans les informations concernant le stockage) d'un schéma utilisateur dont le
-- nom est passé en paramètres d'entrée (variable de sortiede type CLOB). Vous utiliserez, pour
-- ce faire, une requête de la forme (ici schéma utilisateur HR) :

-- TouteTable sur le shema dba_tables

CREATE OR REPLACE FUNCTION ToutesTables(vUserSchema IN VARCHAR2) RETURN CLOB AS
vClob CLOB := NULL;
CURSOR myCursor IS SELECT dbms_metadata.Get_ddl('TABLE',Upper(table_name), owner) AS eachTable FROM dba_tables WHERE owner = Upper(vUserSchema);
BEGIN
   dbms_metadata.Set_transform_param (dbms_metadata.session_transform, 'TABLESPACE', FALSE);
   dbms_metadata.Set_transform_param (dbms_metadata.session_transform, 'SQLTERMINATOR', TRUE);
   dbms_metadata.Set_transform_param (dbms_metadata.session_transform, 'PRETTY', TRUE);
   dbms_metadata.Set_transform_param (dbms_metadata.session_transform, 'SEGMENT_ATTRIBUTES', FALSE);
   dbms_metadata.Set_transform_param (dbms_metadata.session_transform, 'STORAGE', FALSE);
FOR line IN myCursor
    LOOP
        vClob := vClob || line.eachTable;
    END LOOP;
    RETURN vClob;
END;
/

SELECT ToutesTables('E20190009681') FROM dba_tables WHERE table_name = 'POPULATION';


-- ---------------------------------------------------
-- 3. Vous construirez une nouvelle fonction nommée ToutesTablesInfos qui renvoie les ordres
-- de création (plus l’information concernant l’organisation logique et les paramètres associés
-- au stockage physique) de toutes les tables d’un schéma utilisateur dont le nom est passé en
-- paramètre d’entrée.

CREATE OR REPLACE FUNCTION ToutesTablesInfos(vUserSchema IN VARCHAR2) RETURN CLOB AS
vClob CLOB := NULL;
CURSOR myCursor IS SELECT dbms_metadata.Get_ddl('TABLE',Upper(table_name), owner) AS eachTable FROM dba_tables WHERE owner = Upper(vUserSchema);
BEGIN
FOR line IN myCursor
    LOOP
        vClob := vClob || line.eachTable;
    END LOOP;
    RETURN vClob;
END;
/

SET serveroutput ON
SELECT ToutesTablesInfos('E20190009681') FROM dba_tables;


-- ---------------------------------------------------
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


-- ---------------------------------------------------
-- 2.1.4  Informations sur les organisations logique/physique

-- 1
-- pop infos from dbasegments
--- segment_name='POPULATION' and owner='E20190009681'

CREATE OR REPLACE PROCEDURE Population_information_1 AS
CURSOR myCursor IS SELECT owner, segment_name, segment_type, bytes, blocks, tablespace_name, partition_name, extents FROM dba_segments WHERE segment_name='POPULATION' AND owner='E20190009681';
BEGIN
    FOR line IN myCursor
    LOOP
        dbms_output.Put_line(Chr(10) ||  'Owner: ' || line.owner || Chr(10) || ' Segment_name: '|| line.segment_name || Chr(10) || ' Segment type: '|| line.segment_type || Chr(10) || ' Bytes: '|| line.bytes || Chr(10) || ' Blocks: '|| line.blocks || Chr(10) || ' Tablespace name: '|| line.tablespace_name || Chr(10) || ' Partition name: '|| line.partition_name || Chr(10) || ' Eextentions: '|| line.extents);
    END LOOP;
END;
/

SET serveroutput ON
EXEC Population_information_1;


-- ---------------------------------------------------
-- 2
-- Seulement sur population
-- population blocks in bytes from dbasegments

CREATE OR REPLACE PROCEDURE Population_information_2 AS
CURSOR myCursor IS SELECT SUM(bytes) octets, SUM(blocks) blocs FROM dba_segments WHERE owner='E20190009681' AND segment_name='POPULATION';
BEGIN
    FOR line IN myCursor
    LOOP
        dbms_output.Put_line( Chr(10) ||  'population blocks and bytes: ' || Chr(10) ||  ' Bytes: ' || line.octets || Chr(10) ||  ' Blocks: ' || line.blocs);
    END LOOP;
END;
/

SET serveroutput ON
EXEC Population_information_2;


-- ---------------------------------------------------
-- 3
-- Infos populations sur jointure des deux tables
-- dbasegments and dbaextends

CREATE OR REPLACE PROCEDURE Population_information_3 AS  
CURSOR myCursor IS SELECT DISTINCT ds.owner ow, ds.segment_name sn, ds.segment_type st, ds.bytes octets, ds.blocks blocs, ds.tablespace_name tsn, ds.partition_name pn, extents FROM dba_segments ds, dba_extents de WHERE ds.segment_name=de.segment_name AND ds.segment_name='POPULATION' AND ds.owner='E20190009681';
BEGIN
    FOR line IN myCursor
    LOOP
        dbms_output.Put_line( Chr(10) ||  'Owner: ' || line.ow || Chr(10) ||  ' Segment name: '|| line.sn || Chr(10) ||  ' Segment type: '|| line.st || Chr(10) ||  ' Bytes: '|| line.octets || Chr(10) ||  ' Blocks: '|| line.blocs || Chr(10) ||  ' Tablespace name: '|| line.tsn || Chr(10) ||  ' Partition name: '|| line.pn || Chr(10) ||  ' Eextentions: '|| line.extents);
    END LOOP;
END;
/

SET serveroutput ON
EXEC Population_information_3;


-- ---------------------------------------------------
-- 4
-- pop infos from dbasegments
--- Avec la somme de des tailles des segments et des blocks et des exetensions pour chaque utilisateur

CREATE OR REPLACE PROCEDURE Population_information_4 IS
CURSOR myCursor IS SELECT owner, segment_type, Count(segment_name),
SUM(bytes) octets, SUM(blocks) blocs,
SUM(extents) extensions FROM dba_segments GROUP BY owner, segment_type ORDER
BY octets ASC;
BEGIN
    FOR line IN myCursor
    LOOP
        dbms_output.Put_line(Chr(10) || 'owner: ' || line.owner || Chr(10) ||  ' Segment type: ' || line.segment_type || Chr(10) || ' Bytes: ' || line.octets ||
    Chr(10) || ' Blocks: ' || line.blocs);
    END LOOP;
END;
/

SET serveroutput ON
EXEC Population_information_4;


-- ---------------------------------------------------
-- 2.2  Export XML Schéma et données
-- 2.2.1  Fonction GETXML du paquetage DBMSMETADATA
-- 1. Vous construirez une fonction nommée TableXML qui renvoie la description XML d’une table
-- en particulier du schéma utilisateur (nom de la table passé en paramètre d’entrée)

CREATE OR REPLACE FUNCTION TableXML(vTableName VARCHAR) RETURN CLOB IS
CURSOR myCursor IS SELECT dbms_metadata.Get_ddl('TABLE',
Upper(vTableName)) AS eachTable FROM user_tables;
vClob CLOB;
BEGIN
    FOR line IN myCursor
    LOOP
        vClob := line.eachTable;
    END LOOP;
    RETURN vClob;
END;
/

SET serveroutput ON
SELECT TableXML('population') FROM dual;


-- ---------------------------------------------------
-- 2.2  Export XML Schéma et données
-- 2.2.1  Fonction GETXML du paquetage DBMSMETADATA
-- 2. Vous exploiterez le paquetage DBMSXMLGEN pour avoir également les feuilles de l’arborescence 
-- XML (données) pour une table donnée. Construisez également une fonctionà ce sujet
-- TableDataXMLqui prend en argument le nom d’une table mais aussi une condition 
-- de filtre(clause where).

CREATE OR REPLACE FUNCTION TableDataXML(vColumName VARCHAR, vTableName VARCHAR,condition VARCHAR)
RETURN clob
IS
BEGIN
    DECLARE
        vClob clob;
        CURSOR myCursor IS SELECT dbms_xmlgen.Getxml(dbms_xmlgen.Newcontext('select ' || vColumName || ' from ' || vTableName || ' where ' || condition)) AS query FROM dual;
    BEGIN
        FOR line IN myCursor
        LOOP
            vClob := vClob || line.query;
        END LOOP;
        RETURN vClob;
    END;
END;
/

SET serveroutput ON
SELECT TableDataXML('*', 'POPULATION','codeinsee=''38185'' ') FROM dual;
SELECT TableDataXML('dragon', 'dragons','dragon=''Miloch'' ') FROM dual;
SELECT TableDataXML('*', 'dragons','dragon=''Miloch'' ') FROM dual;


-- ---------------------------------------------------
-- 3. Export des données
-- Vous définirez une procédure qui permet de renvoyer les données correspondant au
-- codeinsee, nom de la commune, valeur de la population en 2000, et valeur de la population en 2010
-- au format tabulé.

CREATE OR REPLACE PROCEDURE factory_population
IS
CURSOR myCursor IS SELECT p.codeinsee, val_population, annee, nomcommin FROM population p, commune c WHERE p.codeinsee = c.codeinsee AND annee IN (2000,2010);
BEGIN
    FOR line IN myCursor
    LOOP
        dbms_output.Put_line(line.codeinsee || Chr(9) || line.val_population || Chr(9) || line.annee || Chr(9) || line.nomcommin || Chr(13)) ;
    END LOOP;
    EXCEPTION
    WHEN OTHERS THEN dbms_output.Put_line('Error');
END;
/

SET serveroutput ON
EXEC factory_population;


-- ---------------------------------------------------


