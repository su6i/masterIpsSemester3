--les tables
v$database, v$session, v$option, v$instance, v$lock, dba_users, v$sga, v$sgastat,

--Question 1
--v$instance
desc v$instance

set linesize 180
col host_name for a20


select instance_name, startup_time, host_name, version from v$instance;



--question 2
desc v$database

select name, created, log_mode, current_scn from v$database;



--question 3
-- desc v$option
col parameter for a30 -- taghire size har col moghe affichage dar terminal
col value for a30

select * from v$option;


--question 4
select banner from v$version;


--question 5
--desc v$session
select username, sid, server, terminal, SQL_ADDRESS, logon_time from v$session;

select username, sid, server, terminal, SQL_ADDRESS, logon_time from v$session where type='USER' and username is not null;



--question
select pool, sum(bytes)/1024 Ko from v$sgastat group by pool;
select pool, sum(bytes)/1024/1024 Mo from v$sgastat group by pool;

col value for 99999999999999999.99
select name, value from v$sga;


select name, value from v$parameter where name like 'db_block%';

--db_block_size = 8129 octets(bytes)



-- identifier les processus arriere_plan
select p.pid, bg.name, bg.description, p.program from v$bgprocess bg, v$process p where bg.paddr=p.addr order by p.pid;


--desc dba_tablespaces
select tablespace_name, block_size, initial_extent from dba_tablespaces;

select tablespace_name, file_name from dba_data_files;


--desc dba_segments
select owner, segment_name from dba_segments;

select owner, segment_name, segment_type from dba_segments;

select owner, segment_name, segment_type from dba_segments where segment_name like 'SYS%';

select owner, segment_name, segment_type from dba_segments where owner ='MNASRI';

select owner, sum(bytes), count(blocks)*8192 from dba_segments group by owner order by count(blocks);
select owner, sum(bytes), sum(blocks)*8192 from dba_segments group by owner order by sum(blocks);

select owner, sum(bytes), sum(blocks)*8192 from dba_segments where rownum < 10 group by owner order by sum(blocks) desc;


--desc v$bh
select block#, objd from v$bh;

select count(distinct block#) from v$bh;

--1.4
select file#, block#, class#, dirty, objd, object_name, owner from v$bh, dba_objects where dirty='Y' and objd = object_id;

select block#, class#, dirty, objd, object_name, owner from v$bh, dba_objects where owner='MNASRI' and objd = object_id;



--1.5 memoire cache
select sql_id, disk_reads from v$sql where rownum < 20 order by disk_reads desc;

select q.sql_id, disk_reads, elapsed_time/100000, username from v$sql q, v$session s where s.sql_id = q.sql_id and type ='USER';

--affichage les requets que j'ai fais
select parsing_schema_name, substr(sql_text,1,100) from v$sqlarea where parsing_schema_name='MNASRI';

-- pour savoir qui deponse pas mal !!! combien ça me coute en temps de reponse sur une requette
select sql_FullText,(cpu_time/100000) "Cpu Time (s)", (elapsed_time/1000000) "Elapsed time (s)", fetches, buffer_gets, disk_reads, executions FROM v$sqlarea WHERE Parsing_Schema_Name ='MNASRI' AND rownum <50 order by 3 desc;



select sql_id, child_number, plan_hash_value, parsing_schema_name from v$sql;



-- je veux avoir les informations sur :! on doit déja rafrichir votre table ex; emp
 analyze table EMP compute statistics;
 
select table_name, tablespace_name, num_rows, blocks, empty_blocks, avg_space from user_tables where table_name='EMP';



































