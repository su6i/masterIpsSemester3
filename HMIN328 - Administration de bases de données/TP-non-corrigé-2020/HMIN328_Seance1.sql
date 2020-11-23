-- positionner affichage
set serveroutput on

-- exemple 1
create or replace trigger T1 
before insert or delete on emp
for each row
begin
dbms_output.put_line('operation reussie');
end;
/

--tester effets du declencheur
insert into emp (nom, num, salaire) 
values ('tom', 99999, 1500);


-- exemple 2 : interdire insertion
create or replace trigger T1 
before insert on emp
for each row
begin
raise_application_error(-20099,'action interdite');
end;
/


--

create or replace trigger T1 
before insert on emp
for each row
begin
dbms_output.put_line('operation reussie pour emp '||:new.num);
end;
/


--tester effets :new

insert into emp (nom, num, salaire) 
values ('mary', 888, 1800);


-- tester le if et inserting et deleting
create or replace trigger T1 
before insert or delete on emp
for each row
begin
if inserting
then dbms_output.put_line('insertion reussie pour emp : '||:new.num);
elsif deleting
then dbms_output.put_line('suppression reussie pour emp : '||:old.num);
end if;
end;
/

-- test
delete from emp where nom = 'mary';

-- ajout pour mise a jour : updating

create or replace trigger T1 
before insert or delete or update on emp
for each row
begin
if inserting
then dbms_output.put_line('insertion reussie pour emp : '||:new.num);
elsif deleting
then dbms_output.put_line('suppression reussie pour emp : '||:old.num);
elsif updating
then dbms_output.put_line('mise a jour reussie pour emp :  '||:old.num|| 'et nouvelle valeur ' ||:new.num);
end if;
end;
/

update emp  set num=11111 where nom ='BARA';

-- suppression du declencheur
drop trigger t1;

-- ajout contraintes integrite

alter table emp add constraint emp_pk primary key (num);
alter table dept add constraint dept_pk primary key (n_dept);

alter table emp add constraint emp_fk1 foreign key (n_dept) references dept(n_dept) on delete cascade;

update emp set n_dept = 10 where n_dept=100;


alter table emp add constraint emp_fk2 foreign key (n_sup) references emp(num) on delete cascade;


select constraint_name, constraint_type from user_constraints where table_name in ('EMP','DEPT');


-- exercice 1
-- en lieu et en place contrainte de domaine
-- alter table emp add constraint salaire_dom CHECK (salaire >= 0);
--  alter table emp drop constraint salaire_dom;

create or replace trigger exo1 
before insert or update of salaire on emp
for each row
begin
if :new.salaire < 0
then raise_application_error(-20000,'salaire toujours positif');
end if;
end;
/

update emp set salaire = -5 where num = 24831;

--drop trigger exo1;

--exo2

-- exemple de curseur explicite (plusieurs lignes retournees)

declare 
cursor C is select num, nom, fonction from emp where fonction='commercial';
begin 
for C_t in C
loop
dbms_output.put_line(' nom du commercial : '||C_t.nom);
end loop;
end;
/

-- curseur implicite : un seul tuple retourne

declare 
nom_employe emp.nom%type;
salaire_employe emp.salaire%type;
begin
select nom, salaire into nom_employe, salaire_employe from emp where num = 27546;
dbms_output.put_line('nom et salaire : '||nom_employe||'  '||salaire_employe);
exception 
when too_many_rows then dbms_output.put_line(' Attention un seul tuple attendu');
end;
/

-- exercice 2 

create or replace trigger exo2 
before insert or update of salaire on emp
for each row
declare
localisation dept.lieu%type;
begin
select lieu into localisation from dept where n_dept = :new.n_dept;
if localisation = 'Rennes' AND :new.salaire < 1000
then
raise_application_error(-20100,'attention salaire trop bas pour la ville '||localisation);
end if;
end;
/

insert into emp (nom,num,salaire,n_dept) values ('zoe',11111,500,10);

delete from emp where num =11111;
















 











