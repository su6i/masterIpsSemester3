create or replace procedure supp (prefixe in varchar)
 is
 cursor c is select table_name from user_tables where table_name like 
 upper(prefixe)||'%';
 vide boolean := true ;
 vide_e exception;
 begin
 for c_t in c
 loop
 dbms_output.put_line('taille '||c%rowcount); 
 execute immediate 'drop table '||c_t.table_name||' cascade 
 constraints';
 dbms_output.put_line('table '||c_t.table_name||' detruite ');
 vide := false;
 end loop;
 if vide then raise vide_e;
	end if;
 exception
when vide_e then  dbms_output.put_line('Pas de table correspondante ');
when others then dbms_output.put_line('Pb sur la suppression ');
 end;
 /


set serveroutput on
--create table ttest (a integer);

begin
supp('tt');
end;
/

create or replace procedure suppAll
 is
 cursor c is select table_name from user_tables ;
 begin
 for c_t in c
 loop
 dbms_output.put_line('taille '||c%rowcount); 
 execute immediate 'drop table '||c_t.table_name||' cascade 
 constraints';
 dbms_output.put_line('table '||c_t.table_name||' detruite ');
 end loop;

 exception
when others then dbms_output.put_line('Pb sur la suppression ');
 end;
 /

exec suppAll

purge recyclebin;

