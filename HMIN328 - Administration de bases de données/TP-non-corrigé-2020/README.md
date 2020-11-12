# Administration De Base De Donnees

Useful commands:

---

`show user;`  
`col "name of table" for a10;`  
`col VALUE off` -- deactivate col command  
`set serveroutput on;` -- environmental variable  
`grant execute on Finances to public;` -- to all  
`grant execute on Finances to E20190009681;` -- to a specific person  
`desc dbms_metadata;`  
`select table_name from user_tables;`  
`select constraint_name, table_name, column_name from user_cons_columns;`  
`select OSUSER from v$session;`  
`select OSUSER, machine from v$session;`  
`DROP TRIGGER hr.salary_check;`  
`SELECT * FROM USER_TRIGGERS;`  
`SELECT trigger_name FROM USER_TRIGGERS;`  
`show errors;`  
`show all;` -- show all environmental variable  
`select trim(to_char(sysdate, 'DAY')) from emp;`

`SELECT table_name,comments FROM dictionary WHERE table_name like 'DBA_USERS_%' ORDER BY table_name;`  
`SELECT table_name,comments FROM dictionary WHERE table_name like 'ALL_%' ORDER BY table_name;`  
`SELECT table_name,comments FROM dictionary WHERE table_name like 'USER_%' ORDER BY table_name;`

`select * from ALL_USERS;`  
`select * from ALL_TRIGGERS;`

`select * from v$instance;`  
`select HOST_NAME from v$instance; --> prodpeda-oracle.umontpellier.fr select * from v$database;`

`select name,value/1024/1024 from v$sga;` --> divide value colomn by 1MB

v$sga, v$sgainfo, v\$sgastat ou show SGA

v$sql, v$sqlarea, v\$sqltext associate to the "library cache" (sharedpool)

`select osuser from v$session;` -- Show connected users  
We can't do the command "col" on the "desc" colomns.

`select substr(sql_text, 1,60), parsing_schema_name from v$sqlarea where rownum < 10;`

Notes:

---

une instance en base de données = un processus et une structures cache  
user error numbers are between [-20000, -20100]
