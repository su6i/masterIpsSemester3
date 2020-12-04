-- select s.osuser, substr(a.sql_fulltext,1) from v$session s join v$sqlarea a on a.hash_value = s.prev_hash_value ;

-- 1.1  Consultations d’ordre g ́en ́eral `a r ́ealiser

-- 1. consulter la vue portant sur l’instance (v$instance):

-- quel est le nom de l’hote sur lequel tourne l’instance, et depuis quand l’instance 
-- est demarree

select HOST_NAME from v$instance; --> prodpeda-oracle.umontpellier.fr   


-- 2. consulter la vue portant sur la base de donn ́ees (v$database) 
-- quel est le nom de la base et exploite t’elle le mode archive

select NAME from v$database;
select LOG_MODE from v$database; --> NOARCHIVELOG


-- 3. consulter la vue (v$option) portant sur les fonctionnalites du serveur de donnees:
--  de quelles options disposons-nous?

select * from v$option where VALUE = 'TRUE';

-- 4. consulter la vue (v$version) portant sur la version du SGBD Oracle sous-jacent
select * from v$version;

-- 5. consulter l’attribut server de la vue v$session pour connaˆıtre l’architecture
--  client-serveur retenue pour servir les connexions utilisateurs (architecture d ́ediee 
-- ou partag ́ee).

select USERNAME,server from v$session;  --> They are all "DEDICATED"


-- 1.2  structures m ́emoire et processus de tˆaches de fond

-- 1. consulter les vues portant sur la m ́emoire SGA (v$sga, v$sgainfo, v$sgastat ou show SGA) 
-- quelles sont les tailles allou ́ees `a la m ́emoire partag ́ee(shared pool), 
select NAME, BYTES/1024/1024, RESIZEABLE from v$sgainfo where NAME = 'Shared Pool Size';

-- au tampon de donn ́ees (data buffer cache), 
select NAME, BYTES/1024/1024, RESIZEABLE from v$sgainfo where NAME = 'Buffer Cache Size';

-- au tampon de journalisation (bufferredo) ? 
select NAME, BYTES/1024/1024, RESIZEABLE from v$sgainfo where NAME = 'Redo Buffers';

-- Exploiter aussi une commande de type show parameter shared_pool_size

-- 2. Pour aller plus dans le d ́etail, vous pouvez consulter la vue v$sgastat ;
-- 3. interroger les vues v$sql, v$sqlarea, v$sqltext qui sont associées `a la ”library cache” 
-- (sharedpool) et identifiez les informations contenues dans la ”library cache” et donc son rôle.

select substr(sql_text, 1,60), parsing_schema_name from v$sqlarea where rownum < 10;
select logon_time, username, program, sql_text from v$session, v$sqlarea where v$session.sql_address = v$sqlarea.address and v$session.username = 'E20190011387';

-- 4. une requête vous est donnée pour identifier les processus d’arrière-plan interagissant 
-- avec les structures mémoire et les fichiers de données

select p.pid, bg.name, bg.description, p.program 
    from v$bgprocess bg, v$process p 
        where bg.paddr = p.addr 
            order by p.pid;

-- Je n'ai pas compris elle fait quoi cette commande.

-- 5. avoir la connaissance de divers paramètres portant sur l’instance : 
select name, value from v$parameter;

-- Par exemple, trouver la taille du bloc de données qui va être l’unité d’échange montée
--  en mémoire vive.

-- Chercher pour tous les noms qui ont "block" dedans
select name, value from v$parameter where name like '%block%';

-- ou 

select name, value from v$parameter where name = 'db_block_size';

-- 1.3  Lien entre les structures logique et physique

-- 1.3.1. consulter les tablespaces définis (dba_tablespaces)
desc dba_tablespaces;

-- 2. consulter l’emplacement des fichiers de données (v$datafile) ;

select name from v$datafile;

-- 3. consulter l’emplacement des fichiers journaux (v$logfile);
select MEMBER from v$logfile;

-- 4. consulter l’emplacement des fichiers de contrôle (v$controlfile)
select name from v$controlfile;

-- 5. faire le lien entre espace de table et fichier de données : 
-- select tablespace_name, file_name from dba_data_files;
-- Combien de fichiers sont asservis `a chaque tablespace? ;

select tablespace_name, RELATIVE_FNO from dba_data_files;


-- 1.4  Tampon de données ”data buffer cache”
select file#, block#, class#, dirty, objd, object_name, owner 
    from v$bh, dba_objects 
        where dirty = 'Y' and objd = object_id;

-- Vous expliquerez le résultat obtenu.

-- La commande affiche tous les blocks qui sont dans la mémoire vive (cache)


-- Comment lister vos blocs de données qui se situent en mémoire cache ?

select object_name, object_id, owner 
    from v$bh, dba_objects 
        where objd = object_id and owner = 'E20190009681';




-- y ecriture




-- TP index
analyze index compk validate structure;
select height, lf_rows, lf_blks, br_rows, br_blks from index_stats;

