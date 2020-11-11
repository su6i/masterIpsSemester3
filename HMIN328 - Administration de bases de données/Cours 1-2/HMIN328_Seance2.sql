create or replace procedure p1 (dep in integer, nomDep out varchar) 
   is
    begin 
   select nom into nomDep from dept where n_dept = dep;
    end;
    /


set serveroutput on

declare 
name dept.nom%type;
begin
p1(10,name);
dbms_output.put_line('le nom est '||name);
end;
/

create or replace function f1 (dep in integer) return varchar
is
nomD dept.nom%type;
begin 
 select nom into nomD from dept where n_dept = dep;
 return nomD;
end;
/



select f1(10) from dual;


--Exo 3 du TP1
-- enlever les espaces de la chaine
select trim(to_char(sysdate, 'DAY')) from dual;

create or replace procedure p_ouvrable
is
begin
if trim(to_char(sysdate, 'DAY'))= 'FRIDAY'
then dbms_output.put_line('on ne travaille pas le vendredi');
end if;
end;
/

exec p_ouvrable





create or replace procedure p_ouvrable
is
begin
if trim(to_char(sysdate, 'DAY'))= 'FRIDAY'
then raise_application_error(-20100,'on ne travaille pas le vendredi');
end if;
end;
/


create or replace trigger t_ouvrable
before insert or delete or update on emp
for each row
begin
p_ouvrable;
end;
/


insert into emp (num) values (1234);


alter trigger t_ouvrable disable;



--exo4

create table historique 
(dateOperation date, nomUsager varchar(15), typeOperation varchar(15));


create or replace trigger t4 
before insert or delete or update on emp
begin
if inserting then insert into historique values (sysdate, user, 'insertion');
elsif updating then insert into historique values (sysdate, user, 'modification');
elsif deleting  then insert into historique values (sysdate, user, 'suppression');
end if;
end;
/


insert into emp (num,nom) values (12,'bob');


-- exo5
create or replace trigger onCascade
before delete or update on dept
for each row
begin
if deleting then delete from emp where n_dept = :old.n_dept;
elsif updating then update emp set n_dept = :new.n_dept where n_dept = :old.n_dept;
end if;
end;
/

delete from dept where n_dept = 10;

update dept set n_dept = 90 where n_dept = 10;

alter table emp drop constraint EMP_FK1;



create or replace procedure supp_fonctions is
cursor c is select object_name from user_procedures where object_type='FUNCTION';
begin
for c_t in c
loop
dbms_output.put_line(' nom de la fonction '||c_t.object_name);
execute immediate 'drop function '||c_t.object_name;
end loop;
end;
/

exec supp_fonctions

-----

alter table QuiSeConnecte add ipAddress varchar(20);
alter table QuiSeConnecte add terminal varchar(8);
alter table QuiSeConnecte add hote varchar(10);


create or replace trigger logon_db
after logon ON DATABASE
declare
o_user varchar2(30);
ipAddress QuiSeConnecte.ipaddress%type;
hote QuiSeConnecte.hote%type;
terminal QuiSeConnecte.terminal%type;
begin
  select sys_context('USERENV','OS_USER'),sys_context('USERENV','IP_ADDRESS'), 
  sys_context('USERENV','SERVER_HOST'), sys_context('USERENV','TERMINAL')
   into o_user, ipAddress, hote, terminal from dual ;
  INSERT INTO QuiSeConnecte VALUES (user, o_user, sysdate, ipAddress, terminal, hote);
  commit;
end;
/


create or replace trigger logon_db
  after logon ON DATABASE
declare
o_user varchar2(30);
ipAddress QuiSeConnecte.ipaddress%type;
hote QuiSeConnecte.hote%type;
terminal QuiSeConnecte.terminal%type;
begin
  select sys_context('USERENV','OS_USER'),sys_context('USERENV','IP_ADDRESS')
   into o_user, ipAddress from dual ;
  INSERT INTO QuiSeConnecte VALUES (user, o_user, sysdate, ipAddress, '', '');
  commit;
end;
/

select * from quiseconnecte where usager not in ('SYS','SYSTEM');

-- tp2

create or replace procedure EmployesDuDepartement (dep in integer, listeEmp out varchar)
is
cursor c is select * from emp where n_dept = dep;
personne exception;
begin 
for c_t in c
loop
listeEmp := listeEmp||chr(10)||c_t.num||' '||c_t.nom||' '||c_t.fonction ;
end loop;
if listeEmp is null then raise personne;
end if;
exception
when personne then listeEmp := 'departement non reference';
when others then listeEmp := 'probleme';
end ;
/

set serveroutput on

create or replace procedure edp (dep in integer)
is
lesEmployes varchar(1000);
begin 
EmployesDuDepartement(dep, lesEmployes);
dbms_output.put_line('employes  du departement  '||dep||' : '||lesEmployes);
end;
/

exec edp(10)


create or replace function coutSalarialDepartement(dep in integer) return float is
cout float;
begin
select sum(salaire) into cout from emp where n_dept = dep group by n_dept;
return cout;
exception 
when no_data_found then return -1;
when others then return -2;
end ;
/

select coutSalarialDepartement(10) from dual;

-- test
select coutSalarialDepartement(n_dept) from emp;



-- paquetage

create or replace package Supervision
as
function tauxUtilisation return float;
procedure infosUsagers;
end Supervision;
/

create or replace package body Supervision
as

function tauxUtilisation return float
is
nbreUsagers integer;
nbreConnectes integer;
begin
select count(distinct username) into nbreConnectes from v$session where type ='USER';
select count(username) into nbreUsagers from dba_users;
return (nbreConnectes/nbreUsagers) * 100 ;
end ;

procedure infosUsagers
is
cursor c is select username, count(table_name) as nombreTables 
from v$session, dba_tables where type ='USER' 
and username = owner group by username;
begin
for c_t in c
loop 
dbms_output.put_line(c_t.username||' a   '||c_t.nombreTables||' tables');
end loop;
end; 

end Supervision ;
/

select Supervision.tauxUtilisation from dual;

declare 
nomUsager varchar(20);
nombreTables  integer;
begin 
Supervision.infosUsagers(nomUsager,nombreTables);
dbms_output.put_line('infos usagers connectes  '||nomUsager||' : '||nombreTables);
end;
/










