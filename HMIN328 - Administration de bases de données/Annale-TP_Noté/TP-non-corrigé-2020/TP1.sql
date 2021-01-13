-- select s.osuser, substr(a.sql_fulltext,1) from v$session s join v$sqlarea a on a.hash_value = s.prev_hash_value ;
-- show user


-- 1. Schema de base de donnees


-- 2. Contraintes cl ́es primaires et  ́etrang`eres


-- 3. Triggers LMD

-- 3.1 Construisez un trigger qui v ́erifie que le salaire des employ ́es est toujours positif, 
-- `a l’insertioncomme `a la mise `a jour (en lieu et place d’une contrainte de domaine de type CHECK surl’attribut salaire).

create or replace trigger verif_salaire
before insert or update of salaire on EMP
for each row
begin
    if ( :new.salaire < 0 ) then
        raise_application_error(-20022, :new.nom|| 'n’’est pas assez paye.');
    end if ;
end ;
/


-- 3.2 2. Construisez un trigger qui verifie que le salaire des employes est toujours 
-- superieur `a 1000 euros,`a l’insertion comme `a la mise `a jour, mais uniquement pour des 
-- employes qui travaillent dansle d ́epartement localisé `a Rennes.


create or replace trigger salaire_plus_1000
before insert or update of salaire on EMP
for each row
when (new.n_dept = 10)
begin
    if ( :new.salaire < 1000 ) then
        raise_application_error(-20000, :new.n_dept|| 'Hey, the salary is not enough.');
    end if ;
end ;
/


-- 3.3 Les triggers ont souvent une ́ecriture très simple (regle ECA) et s’appuient 
-- sur des procedures qui en masquent la complexite. Vous reprendrez l’ecriture du trigger 
-- ouvrable. Vous definirez une procedure JoursEtHeuresOuvrables sans argument qui verifie 
-- que la date du jour n’est pas un samedi ni un dimanche et qui renvoie un message d’erreur 
-- autrement. Vous redefinirez le trigger ouvrable qui fera appel `a cette procedure, dans
-- le contexte de la table Emp. Vous entesterez les effets.



create or replace trigger triggerWorkDays
before insert or update or delete on EMP
FOR EACH ROW
begin
    JoursEtHeuresOuvrables;
end ;
/


CREATE OR REPLACE PROCEDURE JoursEtHeuresOuvrables IS
  BEGIN
    IF trim(to_char(sysdate, 'DAY')) = 'VENDREDI' or trim(to_char(sysdate, 'DAY')) = 'DIMANCHE' THEN
  	 raise_application_error(-20001, 'This is not a workday.');
    END IF;
  END;
/



-- 3.4 Les triggers ont souvent un role de monitoring aupr`es des administrateurs de bases
-- de donn ́ees(indicateurs de la bonne sant ́e des bases de donn ́ees dont ils ont la charge). 
-- Vous cr ́eerez unetable historique (dateOperation, nomUsager, typeOperation) qui va permettre
-- de conserver latrace de toutes les op ́erations r ́ealis ́ees sur la tableDept. Vous cr ́eerez 
-- le trigger qui va permettred’alimenter cette table.


