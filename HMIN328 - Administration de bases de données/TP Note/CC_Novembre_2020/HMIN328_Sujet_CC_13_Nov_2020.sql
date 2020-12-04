-- Amir SHIRALI POUR 

-- package

create or replace package DataCacheMetrics as
function blocksNumberInDataCache;
function allocatedBytesInDataCache;
function ratioCachePerUser;
function blocksNumberPerTablespace;
end DataCacheMetrics;
/

-- Body TP1
create or replace package body DataCacheMetrics as

function blocksNumberInDataCache return integerisblocks# integer ;
begin
SELECT count(*) INTO blocks# 
FROM v$bh;
return blocks#;
exception when others then 
return -1;
end;
/


-- 1. La fonctionallocatedBytesInDataCacheexploite la vue v$bh (par le biais de 
-- la fonctionblocks-NumberInDataCache) ainsi que la vue v$parameter pour avoir 
-- la taille du blocs dedonn ́ees enoctets, et renvoie le nombre d’octets, en cours 
-- d’exploitation, dans le cache de donn ́ees



--------------------------Second function first question------------------------------------
--------------------------allocatedBytesInDataCache-----------------------------------------


FUNCTION allocatedBytesInDataCache RETURN INTEGER IS
blocks# INTEGER ;
ln_size NUMBER;
result INTEGER ;
BEGIN
SELECT VALUE INTO ln_size FROM v$parameter WHERE name = 'DB_BLOCK_SIZE';
blocks# := blocksNumberInDataCache;
result :=ln_size*blocks#;
RETURN result ;
END ;
/




exec DataCacheMetrics.blocksNumberInDataCache 
exec DataCacheMetrics.allocatedBytesInDataCache


----3. Partie 2 : Library Cache et Data Buffer Cache 6 points---------------
-----3.1  Question 1 : requˆete SQL portant sur v$sqlarea-------------------------------


-- Réponnse:  

-- avec set linesize 200; on va changer la taille de largeur de l'ecran à 200 caractères;
-- avec col osuser for a30; on va changer la largeur de la colonne osuser à 30 caractères.
--- s.sid , s.osuser : 
---- on choisis le ID et l'email d'utilisateur

--- substr(a.sql_text,1,60) : 
--- la methode substr va prendre le 60 premier caractères de contenu de colonne sql.text de la 
--- table (vue) v$sqlarea 

--- plan_hash_value:
--- un identifieur unique pour chaque sqltext de la table v$sqlarea


---- PLAN_HASH_VALUE:
----- Numeric representation of the SQL plan for this cursor. Comparing one PLAN_HASH_VALUE to another 
---- easily identifies whether or not two plans are the same (rather than comparing the two plans 
---- line by line).

-- on a changé le nom de v$session à s et v$sqlarea à a

-- join : 
-- on a fait une jointure entre deux tables v$session et v$sqlarea

-- a.hash_value = s.prev_hash_value :
-- avec cette condition on verifie si les deux valeurs sont égales, la requête va 
-- afficher les données demandé

set linesize 200;
col osuser for a30;
select s.sid, s.osuser, substr(a.sql_text,1,60), plan_hash_value 
from v$session s join v$sqlarea aon a.hash_value = s.prev_hash_value ;




-- 2.3  Question 3 : Exemples de mise en œuvre
