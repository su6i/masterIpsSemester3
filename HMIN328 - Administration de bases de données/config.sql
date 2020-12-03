set linesize 400;
col table_name for a30;
col table_type for a10;
col name for a30;
col type for a30;
col OSUSER for a50;
select * from cat;
select s.sid, s.osuser, substr(a.sql_fulltext,1), plan_hash_value from v$session s join v$sqlarea a on a.hash_value = s.prev_hash_value;