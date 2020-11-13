-- Tete TP1
create or replace package tpnote1 as
procedure StructureCommune; procedure VueLibrairieRequetes; end tpnote1;
/
-- Body TP1
create or replace package body tpnote1 as
procedure StructureCommune as
cursor c is avg_row_len cursor c1
select tablespace_name, num_rows, avg_space, blocks, empty_blocks, from user_tables where table_name='COMMUNE';
is select
dbms_rowid.rowid_block_number(rowid) as numero_bloc, count(dbms_rowid.rowid_row_number(rowid)) as nbre_tuples
from commune
group by dbms_rowid.rowid_block_number(rowid) order by nbre_tuples desc;
i integer;
begin
i :=0;
for c_t in c1
loop
dbms_output.put_line(' numero bloc : '||c_t.numero_bloc||' || nombre tuples/bloc : '||c_t.nbre_tuples);
i :=i+1;
end loop;
dbms_output.put_line(' ');
dbms_output.put_line('nombre de blocs parcourus '||i); dbms_output.put_line('******************************************* ');
for c_t in c
loop
dbms_output.put_line('Espace de tables : '||c_t.tablespace_name||' || nbre tuples total : '||c_t.num_rows||
' || blocs +/- remplis : '||c_t.blocks||' || blocs vides : '||c_t.empty_blocks|| ' || espace libre/bloc (octets) : '||c_t.avg_space); dbms_output.put_line('******************************************* '); dbms_output.put_line(' || taille moyenne tuple (octets) : '||c_t.avg_row_len||'
|| espace stockage (Mo) : '||round((c_t.num_rows*c_t.avg_row_len)/(1024*1024),2) );
end loop;
end ;
procedure VueLibrairieRequetes is
cursor C is select parsing_schema_name, count(*) as nbrReq, sum(cpu_time)/1000000 as tempsCPU,
sum(disk_reads) as lecturesDisque, sum(buffer_gets) as lecturesCache from v$sqlarea where parsing_schema_name is not null group by parsing_schema_name; begin
dbms_output.put_line('consultation du cache de requetes (library cache) :'); dbms_output.put_line('************************************************'); dbms_output.put_line(rpad('Schema ',25)||rpad('Req ',15)||rpad('CPU sec ',20)|| rpad('Disk ',15)||rpad('Cache ',15));
for t in C
loop dbms_output.put_line(
rpad(t.parsing_schema_name,25) || rpad(t.nbrReq, 15) || rpad(t.tempsCPU,20) ||
rpad(t.lecturesDisque,15) ||
rpad(t.lecturesCache,15)
);
end loop;
dbms_output.put_line('************************************************'); end;
 end tpnote1; /
exec tpnote1.structurecommune exec tpnote1.vuelibrairieRequetes
-------------------------------------------------------------------
create or replace trigger T1
before insert or delete or update on emp
for each row
begin
if inserting
then dbms_output.put_line('insertion reussie pour emp : '||:new.nom);
elsif deleting
then dbms_output.put_line('suppression reussie pour emp : '||:old.nom); elsif updating
then dbms_output.put_line('mise a jour reussie pour emp : '||:old.num||' et nouvelle valeur: '||:new.num);
end if;
end;
/
update emp set num=15000 where nom ='BARA'; ---------------------------------------------------------------------
explain plan for select nom_com, latitude, longitude from commune where dep in ( select dep from departement where nom_dep in('HERAULT','GARD'));
--deuxième cas (join)
explain plan for select nom_com, latitude, longitude from commune c inner join departement d on c.dep = d.dep
where nom_dep in('HERAULT','GARD');
select plan_table_output from table(dbms_xplan.display()); --troisième cas (exists)
explain plan for select nom_com, latitude, longitude from commune c where exists( select * from departement d where c.dep = d.dep and nom_dep in ('HERAULT','GARD'));
--Quatrieme cas Union
explain plan for select nom_com, latitude, longitude from commune c, departement d where c.dep=d.dep and nom_dep='HERAULT'
union select nom_com, latitude, longitude from commune c, departement d where c.dep=d.dep and nom_dep='GARD';
