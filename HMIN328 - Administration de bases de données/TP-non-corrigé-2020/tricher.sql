Suppression d un trigger:
drop trigger T1;

PART2 :
Ajout de clé primaire :
ALTER TABLE emp ADD CONSTRAINT emp_pk primary key(num);

ALTER TABLE dept ADD CONSTRAINT dept_pk primary key (n_dept);


ajout de clé étrangere:
ALTER TABLE emp ADD FOREIGN KEY (N_DEPT) references dept(N_DEPT) on delete cascade;


PART3 :
1- Construisez un trigger qui v´erifie que le salaire des employ´es est toujours positif, a l’insertion
comme a la mise a jour (en lieu et place d’une contrainte de domaine de type CHECK sur
l’attribut salaire):
CREATE TRIGGER salaire_positif 
	before insert or update of salaire ON emp 
	FOR EACH ROW
	begin	
		if :new.salaire < 0 then 
			raise_application_error	(-2022, 'salaire négatif' )	
		end if;
	end;
/
ou sinon :
CREATE OR REPLACE TRIGGER salary_positive
  BEFORE INSERT OR UPDATE OF salaire ON emp
  FOR EACH ROW
  BEGIN
    IF :new.salaire < 0 then
        raise_application_error(-20000, 'Nop. Salaire < 0');
    END IF;
  END;
/


2. Construisez un trigger qui v´erifie que le salaire des employ´es est toujours sup´erieur a 1000 euros,
a l’insertion comme a la mise a jour, mais uniquement pour des employ´es qui travaillent dans
le d´epartement localis´e a Rennes :
CREATE OR REPLACE TRIGGER salary_smic
BEFORE INSERT OR UPDATE OF salaire ON emp
FOR EACH ROW

DECLARE
  CURSOR citys IS SELECT * FROM dept WHERE NOM = 'Rennes';

BEGIN
  dbms_output.put_line('NOUVEAU SALAIRE : '||:new.salaire);
  for city IN citys LOOP
    IF :new.salaire < 1000 AND city.n_dept = :new.n_dept then
      raise no_data_found;
    END IF;
  END loop;
END;
/


3. Les triggers ont souvent une ´ecriture tres simple (regle ECA) et s’appuient sur des proc´edures
qui en masquent la complexit´e. Vous reprendrez l’´ecriture du trigger ouvrable. Vous d´efinirez
une proc´edure JoursEtHeuresOuvrables sans argument qui v´erifie que la date du jour n’est pas
un samedi ni un dimanche et qui renvoie un message d’erreur autrement. Vous red´efinirez le
trigger ouvrable qui fera appel a cette proc´edure, dans le contexte de la table Emp. Vous en
testerez les effets:
CREATE OR REPLACE PROCEDURE JoursEtHeuresOuvrables IS
  BEGIN
    IF trim(to_char(sysdate, 'DAY')) = 'samedi' or trim(to_char(sysdate, 'DAY')) = 'diamanche' THEN
  	 raise_application_error(-20000, 'Closed');
    END IF;
  END;
/

CREATE OR REPLACE TRIGGER trigger_available_day
  BEFORE INSERT OR UPDATE OR DELETE ON emp
  FOR EACH ROW
  BEGIN
    JoursEtHeuresOuvrables;
  END;
/


4. Les triggers ont souvent un rˆole de monitoring aupres des administrateurs de bases de donn´ees
(indicateurs de la bonne sant´e des bases de donn´ees dont ils ont la charge). Vous cr´eerez une
table historique (dateOperation, nomUsager, typeOperation) qui va permettre de conserver la
trace de toutes les op´erations r´ealis´ees sur la table Dept. Vous cr´eerez le trigger qui va permettre
d’alimenter cette table
Le nom de l’usager et la date systeme sont connus au travers des descripteurs USER et SYSDATE (cf. select user, sysdate from dual ;):
CREATE TABLE historique
  (dateOperation DATE,
  nomUsager VARCHAR2(20),
  typeOperation CHAR
);
CREATE OR REPLACE TRIGGER trigger_store_operation
  AFTER INSERT OR UPDATE OR DELETE ON DEPT
  FOR EACH ROW
  BEGIN
    IF inserting THEN
      INSERT INTO historique (dateOperation, nomUsager, typeOperation) VALUES(CURRENT_DATE, :new.NOM, 'I');
    ELSIF deleting THEN
      INSERT INTO historique (dateOperation, nomUsager, typeOperation) VALUES(CURRENT_DATE, :old.NOM, 'D');
    ELSIF updating THEN
      INSERT INTO historique (dateOperation, nomUsager, typeOperation) VALUES(CURRENT_DATE, :new.NOM, 'U');
    END IF;
  END;
/


5. Lors de la d´efinition de contraintes de cl´es ´etrangeres, Oracle autorise certaines fonctionnalit´es
telle que la suppression en cascade des tuples d´ependants au travers de la syntaxe r´eserv´ee on
delete cascade. A la diff´erence de PostgreSQL, Oracle ne permet pas l’exploitation de la syntaxe
on update cascade qui permettrait de modifier les tuples d´ependants en cons´equence.
Vous construirez un trigger nomm´e cascade qui porte sur la table Dept et qui se charge a chaque
´evenement de suppression ou de modification d’un d´epartement (n dept) dans Dept de supprimer ou de modifier dans la table Emp, 
les tuples d’employ´es d´ependants de ce d´epartement.
Pensez ensuite a annuler les effets des suppressions ou modifications par rollback sur la transaction:
CREATE OR REPLACE TRIGGER dept_cascade
  BEFORE DELETE OR UPDATE OF n_dept ON Dept
  FOR EACH ROW
  BEGIN
    IF deleting THEN
      DELETE FROM emp WHERE n_dept = :old.n_dept;
    ELSIF UPDATING THEN
      UPDATE emp SET n_dept = :new.n_dept WHERE n_dept = :old.n_dept;
    END IF;
  END;
/



PART 4:
Vous construirez un trigger qui se d´eclenche au niveau de votre sch´ema utilisateur et qui affiche
un message indiquant un changement du modele a chaque ordre de cr´eation:

CREATE OR REPLACE TRIGGER changement_modele
  AFTER CREATE ON DATABASE 
  BEGIN
    dbms_output.put_line('changement du modele');
  END;
/

PART 5:
Vous construirez un trigger qui se déclenche au niveau de chaque connexion utilisateur (évènement
qui se produit au niveau de la base de données). L’action découlant de cet évènement devra être une
insertion dans une table nommée QuiSeConnecte que vous aurez préalablement définie. La définition
de cette catégorie de trigger nécessite le privilège : ”administer database trigger”, et donc l’octroi de
ce privilège par le DBA, de la manière suivante :
grant administer database trigger to public;
Vous utiliserez la fonction sys context qui donne des informations sur l’environnement de l’utilisateur
et qui peut être exploitée à partir de la table à tout faire dual.
select sys_context(’USERENV’,’IP_ADDRESS’) from dual ;
select sys_context(’USERENV’,’SESSION_USER’) from dual ;




grant administer database trigger to public;
already have............

CREATE TABLE QuiSeConnecte (
c_user varchar2(30),
o_user varchar2(30),
c_date date
);

CREATE OR REPLACE TRIGGER logon_db
after logon ON DATABASE
declare
	ipp_adress varchar2(30);
	session_u varchar2(30);
begin
		select sys_context('USERENV','IP_ADDRESS') into ipp_adress from dual ;
		select sys_context('USERENV','SESSION_USER') into session_u from dual ;
		INSERT INTO QuiSeConnecte VALUES (ipp_adress, session_u, sysdate);
	commit;
end;
/
/



-- Trigger on a CREATE, ALTER, DROP, GRANT, DENY, 
-- REVOKE or UPDATE statement (DDL Trigger)  
  
CREATE [ OR ALTER ] TRIGGER trigger_name   
ON { ALL SERVER | DATABASE }   
[ WITH <ddl_trigger_option> [ ,...n ] ]  
{ FOR | AFTER } { event_type | event_group } [ ,...n ]  
AS { sql_statement  [ ; ] [ ,...n ] | EXTERNAL NAME < method specifier >  [ ; ] }  
  
<ddl_trigger_option> ::=  
    [ ENCRYPTION ]  
    [ EXECUTE AS Clause ] 

