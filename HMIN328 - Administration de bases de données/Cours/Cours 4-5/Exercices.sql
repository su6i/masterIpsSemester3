-- Requetes SQL
select tablespace_name, count(*) as nbresegments from dba_segments group by tablespace_name;

--1. Donner les espaces mémoires exploités par utilisateur, triés du plus petit consommateur au plus grand (dba segments)
select owner, sum(bytes)/1024/1024 as usedspace(Mo) from dba_segments where tablespace_name='USERS' group by owner order by 2;

--2. Quel utilisateur consomme l’espace de stockage memoire le plus important (dba segments et tablespace users) ?
select owner, sum(bytes)/1024/1024 as usedspace from dba_segments where tablespace_name='USERS' group by owner having sum(bytes) >= all (select sum(bytes) from dba_segments where tablespace_name='USERS' group by owner);

select owner, sum(bytes)/1024/1024 as usedspace from dba_segments where tablespace_name='USERS' group by owner having sum(bytes) = (select max(sum(bytes)) from dba_segments where tablespace_name='USERS' group by owner);


--3. Qui possede le plus d’index dans son schéma utilisateur (dba segments)?
--select distinct segment_type from dba_segments;

--select owner, count(*) from dba_segments where segment_type = 'INDEX' group by owner order by 2 asc;

select owner, count(*) from dba_segments where segment_type = 'INDEX' and tablespace_name='USERS' group by owner order by 2 asc;

select owner, count(*) from dba_segments where segment_type = 'INDEX' and tablespace_name='USERS' group by owner having count(*) = (select max(count(*)) from dba_segments where segment_type ='INDEX' and tablespace_name='USERS' group by owner);


--4. Qui n’a pas créé d’objets dans son schéma utilisateur depuis plus d’un an (dba objects)
--select object_name, object_type, created from dba_objects where owner='MNASRI';

--differance
select owner from dba_objects minus select owner from dba_objects where created >= '12-OCT-2017';

--test de non appartenance
select distinct owner from dba_objects where owner not in (select owner from dba_objects where created >= '12-OCT-2017');

--test de vacucité
select distinct owner from dba_objects d1 where not exists (select * from dba_objects d2 where created >= '12-OCT-2017' and d1.owner=d2.owner);



--5. Quels sont les objets et leurs propriétaires qui n’ont pas connu d’évolutions depuis plus d’un an (dba objects)
select distinct owner from dba_objects d1 where not exists(select * from dba_objects d2 where last_ddl_time >= '12-OCT-2017' and d1.owner=d2.owner);


--6. Quels sont les usagers qui écrivent respectivement sur le tablespace SYSTEM, SYSAUX et UNDOTBS1 (dba segments)
select distinct owner from dba_segments where tablespace_name='SYSTEM';

select distinct owner from dba_segments where tablespace_name='SYSAUX';

select distinct owner from dba_segments where tablespace_name='UNDOTBS1';

select distinct owner, tablespace_name from dba_segments where tablespace_name in ('SYSTEM', 'SYSAUX', 'UNDOTBS1');



--7. Quel(s) usager(s) a deux sessions (voire plus ouvertes (v$session)
select username, count(*) from v$session group by username having count(*) > 1;

select username, count(*) from v$session where type='USER' and username is not null group by username having count(*) > 1;


--8. Quel(s) usager(s) a une session, qui a posé des verrous bloquants pour une autre session (v$lock et v$session)
select username, s.sid from v$session s, v$lock l where s.sid = l.sid and block=1;


--9. Quelles sont les requêtes que j’ai exécutées qui sont encore prises en charge au niveau de la library cache (v$sql)
select sql_id, substr(sql_text, 1,60), cpu_time/1000000, disk_reads, buffer_gets from v$sql where parsing_schema_name='MNASRI' order by 3;

--variante
select parsing_schema_name, sql_id, substr(sql_text, 1,60), cpu_time/1000000, disk_reads, buffer_gets from v$sql where parsing_schema_name <> 'SYS' order by 3;



--10. Quelles sont les requêtes que j’ai exécutées plusieurs fois qui sont encore prises en charge au niveau de la library cache (v$sql)
select parsing_schema_name, sql_id, substr(sql_text, 1,60), cpu_time/1000000, disk_reads, buffer_gets from v$sql where parsing_schema_name='MNASRI' and executions>1 order by 3;

--variable liée
variable nom_e varchar2(20)
exec :nom_e :='CODD'

select num, fonction from emp where nom= :nom_e;
exec :nom_e :='BALIN'

select num, fonction from emp where nom= :nom_e;
exec :nom_e :='DELOBEL'

select num, fonction from emp where nom= :nom_e;
exec :nom_e :='BARA'

--11. Quels sont les objets de la base qui sont invalides et qui peuvent entrainer des points de contention au niveau du dictionary cache (dba objects et status)
select object_name, owner from dba_objects where status='INVALID';
--select object_name, owner from dba_objects where status='INVALID' and owner='MNASRI';


--12. Quels sont mes privilèges utilisateur (user tab privs) && 13. Quels sont mes privilèges systèmes (user sys privs)
--privileges utilisateur
select grantee, grantor, owner, table_name, privilege from user_tab_privs;

--privileges systeme
select username, privilege from user_sys_privs;



--14. Quels sont les rôles qui m’ont été attribués (dba roles et dba sys privs)
select distinct username, granted_role from user_role_privs;


--15. Quels sont les privilèges que me donnent ces rôles (dba role privs)



--16. Quel objet, et de quel utilisateur, utilise le plus de blocs du cache de données (dba objects, v$bh)

--correction partielle
select objd, count(block#), object_name from v$bh, dba_objects where objd=object_id and owner not in ('SYS', 'SYSMAN', 'XDB') group by objd, object_name, owner order by 2;

--select objd, count(block#), object_name from v$bh, dba_objects where objd=object_id and owner <>'SYS' group by objd, object_name, owner order by 2;









