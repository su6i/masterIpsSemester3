-- positionner affichage
set serveroutput on;


-- exemple 1 de trigger
create or replace trigger T1
before insert or delete on emp
for each row
begin
dbms_output.put_line('operation reussie');
end;
/


--tester effets du declencheur
insert into emp (nom, num, salaire)
values ('tim', 9999, 1500);


--exemple 2: interdire une insertion
create or replace trigger T1 
before insert or delete on emp 
for each  row
begin 
raise _application_error (-20099, 'action interdite');
end;
/


--
create or replace trigger T1 
before insert or delete on emp 
for each  row
begin
dbms_output.put_line('operation reussie pour emp'||:new.num);
end;
/

-- tester effect :new
insert into emp (nom, num, salaire)
values ('mary', 888, 1800);


--tester le if et inserting et deletin
create or replace trigger T1 
before insert or delete on emp 
for each  row
begin
if inserting
then dbms_output.put_line('operation reussie pour emp:'||:new.num);
elsif deleting
then dbms_output.put_line('suppression reussie pour emp:'||:old.num);
end if;
end;
/

--test
delete from emp where nom ='mary';

-- ajout pour mise à jour : updating
create or replace trigger T1 
before insert or delete on emp 
for each  row
begin
if inserting
then dbms_output.put_line('operation reussie pour emp:'||:new.num);
elsif deleting
then dbms_output.put_line('suppression reussie pour emp:'||:old.num);
elsif deleting
then dbms_output.put_line('mise à jour reussie pour emp:'||:old.num||'et nouvelle valeur'||:new.num);
end if;
end;
/

--test
update emp set num=1111 where nom='BARA';


-- suppression du declencheur
drop trigger T1;

-- ajout contraintes integrite
alter table emp add constraint emp_pk primary key(num);
alter table dept add constraint dept_pk primary key(n_dept);
alter table emp add constraint emp_fk foreign key (n_dept) references dept(n_dept) on delete cascade;

update emp set n_dept=10 where n_dept=100;

alter table emp add constraint emp_fk2 foreign key (n_sup) references emp(num) on delete cascade;

select constraint_name, constraint_type from user_constraints where table_name in ('EMP', 'DEPT');




--exercice 1
--en lieu et en place contrainte de domaine
--Alter table emp add constraint salare_dom CHECK (salaire >= 0);
--Alter table emp drop constraint salaire_dom

create or  replace trigger exo1
before insert or update of salaire on emp for each row
begin
if :new.salaire < 0
then raise_application_error(-20000, 'salaire toujours positif');
end if;
end;
/


update emp set salaire = -100 where num = 9999;


--drop trigger exo1;

---------------------------------------------------------------------------------------

--exo2

--exemple de curseur explicite (plusieur lignes retournees)

declare
cursor C is select num, nom, fonction from emp where fonction='commercial';
begin
for C_t in C
loop
dbms_output.put_line('nom du commercial :'||C_t.nom);
end loop;
end;
/




--curseur implicite : un seul tuple retourne

declare
nom_employe emp.nom%type;
salaire_employe emp.salaire%type;
begin
select nom, salaire into nom_employe, salaire_employe from emp where num= 27546;
dbms_output.put_line('nom et salaire :'||nom_employe|| ' '|| salaire_employe);
exception
when too_many_rows then dbms_output.put_line('Attention un seul tuple attendu');
end;
/



-- exercice 2
create or replace trigger exo2
before insert or update of salaire on emp for each row
declare
localisation dept.lieu%type;
begin
select lieu into localisation from dept where n_dept = :new.n_dept;
if localisation = 'Rennes' and :new.salaire < 1000
then raise_application_error(-20100, 'attention salaire trop bas pour la ville '||localisation);
end if;
end;
/


insert into emp (nom, num, salaire, n_dept) values ('zoe', 11111, 500, 10);


delete from emp where num=11111;



-----------------------------------------
create or replace procedure p1 (dep in integer, nomDep out varchar)
is
begin
select nom into nomDep from dept where n_dept=dep;
end;
/



---
select procedure_name from user_procedures where procedure_name is not null;


---------
set serveroutput on;


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
select nom into nomD from dept where n_dept= dep;
return nomD;
end;
/


select f1(10) from dual;

-------------------------------------------------------------------------

---exo 3
-- 
select trim(to_char(sysdate, 'DAY')) from dual;

create or replace procedure p_ouvrable
is
begin
if trim(to_char(sysdate, 'DAY'))= 'FRIDAY'
then dbms_output.put_line('on ne travaille pas le vendredi');
end if;
end;
/

exec p_ouverable

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

--test
insert into emp(num) values (1234);

alter trigger t_ouvrable disable;


---------------
-- exo 4

create table historique
(dateOperation date, nomUsager varchar(15), typeOperation varchar(15));


create or replace trigger t4
before insert or update or delete on emp
begin
if inserting then insert into historique values (sysdate, user, 'insertion');
elsif updating then insert into historique values (sysdate, user, 'modofocation');
elsif deleting then insert into historique values (sysdate, user, 'suppression');
end if;
end;
/


insert into emp (num, nom) values (12, 'bob');




create or replace trigger onCascade
before delete or update on dept
for each row
begin
if deleting then delete from emp where n_dept= :old.n_dept;
elsif updating then update emp set n_dept= :new.n_dept where n_dept = :old.n_dept;
end if;
end;
/

delete from dept where n_dept=10;

update dept set n_dept = 90 where n_dept=10;

alter table emp drop constraint EMP_FK1;




create or replace procedure supp_functions is
cursor c is select object_name from user_procedures where object_type='FUNCTION';
begin
for c_t in c
loop
dbms_output.put_line('nom de la fonction '||c_t.object_name);
execute immediate 'drop function '||c_t.object_name;
end loop;
end;
/


exec supp_fonctions



----------TP2
--2

create table QuiSeConnecte
	(usager varchar(20),
	os_usager varchar(20),
	date_conn date,
	ipaddress varchar(20),
	terminal varchar(20),
	hote varchar(20));




create or replace trigger logon_db
after logon ON database
declare 
o_user varchar2(30);
ipAddress QuiSeConnecte.ipAddress%type;
hote QuiSeConnecte.hote%type;
terminal QuiSeConnecte.terminal%type;
begin
select sys_context('USERENV', 'OS_USER'), sys_context('USERENV','IP_ADDRESS'), sys_context('USERENV','SERVER_HOST'), sys_context('USERENV','TERMINAL') into o_user, ipAddress, hote, terminal  from dual;
insert into QuiSeConnecte values ('user', o_user, sysdate, ipAddress, hote, terminal);
commit;
end;
/

select * from quiseconnecte where usager not in ('SYS', 'SYSTEM');

------tp2
--4.1

create or replace procedure EmployesDuDepartement (dep in integer, listeEmp out varchar)
is
cursor c is select * from emp where n_dept=dep;
personne exception;
begin
for c_t in c
loop
listeEmp := listeEmp||' '||c_t.num||' '||c_t.nom||' '||c_t.fonction;
end loop;
if listeEmp is null then raise personne;
end if;
exception
when personne then listeEmp := 'departemant non reference';
when others then listeEmp := 'problem';
end;
/


set serveroutput on

create or replace procedure edp (dep in integer)
is
lesEmployes varchar(1000);
begin
EmployesDuDepartement(dep, lesEmployes);
dbms_output.put_line ( 'employes du departement'||dep||' : '||lesEmployes);
end;
/

exec edp(10)

create or replace function coutSalarialDepartement ( dep in integer) return float is
cout float;
begin
select sum(salaire) into cout from emp where n_dept = dep group by n_dept;
return cout;
exception
when no_data_found then return -1;
when others then return -1;
end;
/

select coutSalarialDepartement(10) from dual;

select coutSalarialDepartement(n_dept) from emp;



--paquetage
create or replace package Supervision
as
function tauxUtilisation return float;
procedure infosUsagers (nomu out varchar, nombreTables out integer);
end supervision;
/



create or replace package body Supervision
as
function tauxUtilisation return float
is
nbreUsagers integer;
nbreConnectes integer;
begin
select count (distinct username) into nbreConnectes from v$session where type ='USER';
select count (username) into nbreUsagers from dba_users;
return (nbreConnectes/nbreUsagers) * 100;
end;

procedure infoUsagers (nomu out varchar, nombreTables out integer)
is
cursor c is select username, count(table_name) as nombreTables from v$session, dba_tables where type='USER' and username= owner group by username;
begin
for c_t in c
dbms_output.put_line(c_t.username ||'  '|| c_t.nombreTables||'tables ')
end loop;
end;


end Supervision;
/

--test
select Supervision.tauxUtilisation from dual;


















--declare
select to_char(sysdate, 'DAY') from dual;

select user, to_char(sysdate, 'DAY') from dual

select sys_context('USERENV', 'IP_ADDRESS') from dual;

select * from QuiSeConnecte where usager not in ('SYS', 'SYSTEM') order by date connexion asc;

--server uni
desc dba_users

select username, created from dba_users order by username;



