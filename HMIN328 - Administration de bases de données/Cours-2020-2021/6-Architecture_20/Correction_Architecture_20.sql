
Exercice 1
set linesize 180
col table_name for a30
col comments for a30

SELECT table_name,comments FROM dictionary WHERE table_name LIKE 'USER_%' ORDER BY table_name;
SELECT table_name FROM dictionary WHERE table_name LIKE 'DBA_%' ORDER BY table_name;
SELECT name FROM v$fixed_table WHERE name LIKE 'V$%';

SELECT * FROM v$fixed_table WHERE name LIKE 'V$SESSION';


--- exemple de construction de paquetage sur une seule question

-- definition des signatures
CREATE OR REPLACE package DataArchitecture
AS
function blocksNumberPerUser (username varchar) return integer ;
procedure ConsumedSpacePerUser  (username in varchar, bytes out integer);
END ;
/

-- definition du corps
CREATE OR REPLACE package body DataArchitecture AS 

function blocksNumberPerUser (username varchar) return integer
IS
blocks# integer ;
BEGIN
SELECT count(blocks) INTO blocks# FROM dba_segments
WHERE owner = upper(username) 
group by owner ;
return blocks#; 
EXCEPTION WHEN NO_DATA_FOUND THEN dbms_output.put_line('usager inexistant '||username);
END;

procedure ConsumedSpacePerUser  (username in varchar, bytes out integer)
is
BEGIN
SELECT value*blocksNumberPerUser(username) INTO bytes FROM v$parameter WHERE name='db_block_size';
EXCEPTION WHEN NO_DATA_FOUND THEN dbms_output.put_line('usager inexistant '||username);
WHEN OTHERS THEN dbms_output.put_line(sqlerrm);
END;
END ;
/

-- exemples utilisation
set linesize 180
col username for a20
select username, nvl(DataArchitecture.blocksNumberPerUser(username),0) as nbreBlocs from dba_users;

set serveroutput on

declare
octets integer;
begin 
DataArchitecture.ConsumedSpacePerUser('E20200011127',octets);
dbms_output.put_line('nombre octets consomme : '||octets||' octets');
end ;
/

-- test exception
select nvl(DataArchitecture.blocksNumberPerUser('E2020'),0) as nbreBlocs from dual;

---




SELECT table_name FROM dictionary WHERE table_name like 'DBA_%' ORDER BY table_name;

Partie 1.1
 
prompt exemples de requete

desc v$instance

--1 nom hote, date demarrage plus autres infos
select instance_number, instance_name, host_name, version, startup_time from v$instance;
 
--2 a retenir : prodpeda-oracle.umontpellier.fr et 1/10/2020
desc v$database
select dbid, name, created, log_mode, db_unique_name from v$database ;
-- a retenir meme nom que instance, archivage non pris en charge, creation 12/04/2019

--3 fonctionnalites etendues
desc v$option
-- disponible
select parameter from v$option where value = 'TRUE';
-- la surcouche spatiale ou les index bitmap sont disponibles

-- non accessible
select parameter from v$option where value = 'FALSE';
-- La version distribuee oracle (RAC) et les fonctions de tests de performance de stockage (Automatic Storage Management)
-- ne sont pas disponibles

--4
select banner from v$version;
-- oracle 12C - Linux 64 bits

--5 architecture dediee : 1 processus serveur par processus client
select distinct server from v$session;

Partie 1.2

--1 taille de la SGA
show sga 
-- puis
select 3221225472/1024/1024 en_Mo from dual; 
-- environ 3 Go taille maximale sachant qu'une partie est variable et seule une partie est fixe et allouee au demarrage de l'instance

select name, bytes/1024/1024 from v$sgainfo;

NAME				 BYTES/1024/1024
-------------------------------- ---------------
Fixed SGA Size			      8,39035797
Redo Buffers				7,609375
Buffer Cache Size			     832
In-Memory Area Size			       0
Shared Pool Size			     416
Large Pool Size 			      32
Java Pool Size				      16
Streams Pool Size			      32
Shared IO Pool Size			      64
Data Transfer Cache Size		       0
Granule Size				      16
Maximum SGA Size			    3072
Startup overhead in Shared Pool       191,510918
Free SGA Memory Available		    1728

-- 2 detail sur les differentes sous-structures
select pool, name, bytes/1024 en_Ko from v$sgastat;


--3 les vues concernant les ordres SQL dernierement executes par les usagers : v$sql, v$sqlarea, v$sqltext
-- exemple de requete  qui renvoie une portion de l'ordre sql, le nom du schema utilisateur, le temps d execution, le nombre 
-- d'executions et la date de derniere execution des ordres provenant de schemas utilisateur commencant par E
select substr(sql_text,1,60), parsing_schema_name, elapsed_time/1000000 elap_sec, executions, last_active_time from v$sqlarea where parsing_schema_name like 'E%';
-- la library cache est une zone memoire dediee a garder disponible les derniers ordres passes et leur plan d'execution afin de 
-- pouvoir les rexecuter le plus efficacement possible

--4 processus arriere plan : un seul processus ecrivain reference, idem pour log writer 
select p.pid, bg.name, bg.description, p.program from v$bgprocess bg, v$process p where bg.paddr = p.addr order by p.pid;


--5 -- connaitre la taille du bloc de donnees echange entre la RAM et le disque
show parameter db_block_size
-- 8192 octets
select name, value from v$parameter where name like 'db%';
-- tout n'est pas renseigne


Partie 1.3

--1 desc dba_tablespaces
select tablespace_name, block_size, initial_extent, min_extents, max_extents, max_size, max_size, pct_increase from dba_tablespaces;
-- Parmi les espaces de table : DATA_ETUD qui est le tablespace avec lequel tous les usagers habituels (etudiants et enseignants)
-- interagissent

--2 tailles allouees aux fichiers de donnees associes aux differents tablespaces
select FILE#, creation_time, bytes/1024/1024/1024 en_Go , blocks, name from v$datafile;

--3 trois groupes obligatoires, 1 membre online par groupe : c'est presque le minimum syndical car il en faut au moins 2
-- possibilite de multiplexage (plusieurs copies avec ecriture simultanee) mais en standard un seul fichier avec ecriture circulaire
select group#, status, type, member from v$logfile;

--4 la taille du bloc est de 16ko (2 fois plus importante donc), taille des fichiers importante et au nombre de 3
-- les fichiers de controle sont en permanence exploites par le systeme pour en controler l'etat
-- multiplexage : meme info ecrite en simultane sur les differentes copies du fichier
select name, block_size, file_size_blks from v$controlfile;


--5 correspondance organisation logique - organisation physique
col tablespace_name for a60
col file_name for a60
select tablespace_name, file_name from dba_data_files ;
-- dans le cas present :  1 fichier - 1 tablespace 



Partie 1.4
-- il s'agit des blocs de donnes en en cours de modification (on voit les structures ici de table concernees et les identifiants
-- des usagers. Le fichier de donnees de destination est cible aussi data_etud 

col object_name for a20
col owner for a20
select file#, block#, class#, dirty, objd, object_name, owner from v$bh, dba_objects where dirty = 'Y' and objd = object_id;

-- exemple des blocs de donnees en cache qui correspondent a un schema utilisateur
 select file#, block#, class#, dirty, objd, object_name, owner from v$bh, dba_objects where objd = object_id and owner = 'P00000009432';

-- class = 1 bloc de donnee ou d'index
-- class = 4 en tete de segment


-- nombre de blocs contenus dans le cache de donnees (dispo pour les activites liees aux usagers)
 select count(block#) from v$bh;


Partie 1.5

-- de moindre interet car ne renvoie que la requete en cours de sa session utilisateur
select to_char(logon_time, 'DD/MM/YYYY HH24:MI:SS'), username, program, sql_text from v$session, v$sqlarea where v$session.sql_address = v$sqlarea.address order by username, program ;

-- lister les usagers connectes et la date de debut de connexion
select to_char(logon_time, 'DD/MM/YYYY HH24:MI:SS'), username, osuser from v$session where type='USER';

--
select sql_id, substr(sql_text,1,60), disk_reads from v$sql where rownum <20 order by disk_reads desc;
-- exemple de sortie

SQL_ID	      SUBSTR(SQL_TEXT,1,60)					   DISK_READS
------------- ------------------------------------------------------------ ----------
g9vnd557800wp		  SELECT /*+ opt_param('optimizer_adaptive_feature     231386
34rznuxy8h2a4 insert into histgrm$(obj#,intcol#,row#,bucket,endpoint,col#,	    9
2z0udr4rc402m select exptime, ltime, astatus, lcount from user$ 		    0
gngtvs38t0060 SELECT /*+ CONNECT_BY_FILTERING */ s.privilege# FROM sys.sys	    0
....

-- retourner les informations sur les requetes des usagers dont le schema commence par E : cout en temps CPU, nombre de tuples
-- retournes, nombre d'executions de la requete, nombre de blocs accedes
-- attention c'est cumulatif : par exemple le temps CPU est additionne pour toutes les executions
set linesize 200
col parsing_schema_name for a20
select substr(sql_text,1,80), parsing_schema_name, cpu_time/1000000 cpu_sec, fetches, executions, disk_reads+buffer_gets from v$sqlarea where parsing_schema_name like 'E%' order by cpu_time/1000000;


-- retourner le nom des schemas et la date des dernieres activites sur la base 
select parsing_schema_name, to_char(last_active_time,'mm/dd/yyyy hh:mi:ss am') from v$sqlarea where parsing_schema_name <> 'SYS';


Partie 1.6

--1 tout est dans DATA_ETUD pour moi
select table_name, tablespace_name from user_tables;
-- DATA_ETUD pour les autres etudiants aussi 
select distinct tablespace_name from dba_tables where owner like 'E%';

-- exemple pour permettre la collection des statistiques sur le schema entier (pensez a utiliser votre schema)
exec dbms_stats.gather_schema_stats('P00000009432')

--2/3 utilisez la procedure remplissage fournie pour inserer des tuples dans la table test

 create table test(num char(3), commentaire char(97), constraint num_dom check (num between 0 and 999));
 analyze table test compute statistics

 create table test(num char(3), commentaire char(97), constraint num_dom check (num between 0 and 999));
 analyze table test compute statistics ;
-- apres  creation de la table
% select num_rows, blocks, empty_blocks, avg_space, avg_row_len from user_tables where table_name ='TEST'; 
%  NUM_ROWS     BLOCKS EMPTY_BLOCKS  AVG_SPACE AVG_ROW_LEN
%--------- ---------- ------------ ---------- -----------
%	 0	    0		 0	    0		0
-- aucun bloc alloue a la table qui est vide pour le moment

-- apres insertion de 51 lignes
-- penser à faire analyze table test compute statistics avant de consulter user_tables
% NUM_ROWS     BLOCKS EMPTY_BLOCKS  AVG_SPACE AVG_ROW_LEN
%---------- ---------- ------------ ---------- -----------
%	51	    5		 3	 6982	      105
-- le nombre de blocs est de 5 (pour ecriture) + 3 (vides pour le moment)
-- ce qui est tres largement plus que necessaire mais le systeme cherche a eviter trop 
-- de reorganisations a allouant 8 blocs contigus des une premiere insertion
-- les blocs sont peu remplis : 6982 octets libres en moyenne par bloc
-- la taille moyenne du tuple est de 105 octets 


-- apres insertion de 100 lignes supplementaires
% NUM_ROWS     BLOCKS EMPTY_BLOCKS  AVG_SPACE AVG_ROW_LEN
%---------- ---------- ------------ ---------- -----------
%       151	    5		 3	 4840	      105
-- le nombre de blocs est inchange, en effet les 8 blocs suffisent amplement
-- au stockage des 151 tuples. Les  champs de la table sont fixes et donc la taille moyenne du tuple est inchangee

-- apres insertion de 100 lignes supplementaires
% SQL> select num_rows, blocks, empty_blocks, avg_space, avg_row_len from user_tables where table_name ='TEST'; 
%  NUM_ROWS     BLOCKS EMPTY_BLOCKS  AVG_SPACE AVG_ROW_LEN
%---------- ---------- ------------ ---------- -----------
%       251	    5		 3	 2699	      105
-- le nombre de blocs est inchange, en effet les 8 blocs suffisent toujours
-- espace libre diminue cependant

-- apres insertion de 100 lignes supplementaires
% SQL> select num_rows, blocks, empty_blocks, avg_space, avg_row_len from user_tables where table_name ='TEST'; 
%  NUM_ROWS     BLOCKS EMPTY_BLOCKS  AVG_SPACE AVG_ROW_LEN
%---------- ---------- ------------ ---------- -----------
%       351	   13		 3	 5183	      105
-- le nombre de blocs est double (16 blocs donc 3 vides)

--4 suppression du tiers des tuples
select num from test where mod(num,3) = 0;
-- avec delete
delete from test where mod(num,3) = 0;

% SQL> select num_rows, blocks, empty_blocks, avg_space, avg_row_len from user_tables where table_name ='TEST'; 
%  NUM_ROWS     BLOCKS EMPTY_BLOCKS  AVG_SPACE AVG_ROW_LEN
% ---------- ---------- ------------ ---------- -----------
%       232	   13		 3	 6144	      105
--  delete : les blocs alloués le restent, seul espace libere dans les blocs

delete from test;
commit;
SQL> select num_rows, blocks, empty_blocks, avg_space, avg_row_len from user_tables where table_name ='TEST'; 

  NUM_ROWS     BLOCKS EMPTY_BLOCKS  AVG_SPACE AVG_ROW_LEN
---------- ---------- ------------ ---------- -----------
	 0	   13		 3	 8018		0
-- meme constatation : les blocs restent alloues meme apres le commit
-- on peut aussi constater que sur 8192 octets, 8018 octets sont reellement exploitables

-- avec truncate
truncate table test;

% SQL> select num_rows, blocks, empty_blocks, avg_space, avg_row_len from user_tables where table_name ='TEST'; 
%  NUM_ROWS     BLOCKS EMPTY_BLOCKS  AVG_SPACE AVG_ROW_LEN
% ---------- ---------- ------------ ---------- -----------
%	 0	    0		 8	    0		0
-- truncate : les blocs sont desalloues a moitie (8 blocs vides sont encore visibles)
-- truncate opere aussi un commit implicite

Exercice 2
-- cet indice (buffer_hit_ratio) permet de connaitre en moyenne le nombre de blocs a aller chercher sur le disque par rapport 
-- aux blocs deja presents en memoire vive. Plus la valeur est proche de 1, et plus le systeme sera performant lors du requetage
-- la vue v$sysstat qui contient des statistiques sur le systeme est exploitee
-- physical reads : blocs lus sur le disque
-- db block gets : blocs etat courant dans le cache de donnees 
-- consistent gets : blocs etat consistant (a un moment donne) dans le cache de donnees 
Select 1- (phy.value / ( cons.value + db.value - phy.value))
from v$sysstat phy, v$sysstat cons, v$sysstat db
where phy.name ='physical reads' and cons.name ='consistent gets' and db.name ='db block gets';

-- valeur susceptible de varier mais ici 0.88 ce qui est dans la moyenne haute
1-(PHY.VALUE/(CONS.VALUE+DB.VALUE-PHY.VALUE))
---------------------------------------------
				   ,882328663



Exercice 3
-- un exemple de procedure qui evalue, l'espace total et l'espace restant libre dans les fichiers, a partir
-- de l'usage fait des blocs des tablespaces

create or replace procedure Etat_Fichiers as
cursor c is 
SELECT t.tablespace_name,nvl(sum(f.bytes/1024/1024),0) free_mo, nvl(df.bytes/1024/1024,0) total_mo
FROM dba_free_space f right join dba_tablespaces t on
 f.tablespace_name =t.tablespace_name left join dba_data_files df 
on t.tablespace_name = df.tablespace_name
group by t.tablespace_name, df.bytes/1024/1024
order by 2;
difference float := 0 ;
begin
dbms_output.put_line(rpad('Tablespace',16)||rpad('Libre (Mo)',22)||rpad('Total (Mo)',22)||rpad('Utilise (Mo)',22));
for c_t in c
loop
difference := c_t.total_mo - c_t.free_mo ;
dbms_output.put_line(rpad(c_t.tablespace_name,16)||rpad(c_t.free_mo,22)||rpad(c_t.total_mo,22)||rpad(difference,22));
end loop;
end ;
/



set serveroutput on

exec Etat_Fichiers


