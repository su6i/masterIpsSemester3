select index_name, table_name from user_indexes;
-- mettre ajour les statistiques 
exec dbms_stats.gather_schema_stats(USER);
---creé un index
select index_name, table_name from user_indexes;


-------""""""""""" EXERCICE 1 """""""""""""""----------


--1. select code insee from commune ;

explain plan for select /*+ no_index(c)*/ code_insee from commune c;

select plan_table_output from table(dbms_xplan.display());

explain plan for select code_insee from commune c;

--       Resultat Avec Index
-----------------------------------------------------------------------------------
| Id  | Operation	     | Name	  | Rows  | Bytes | Cost (%CPU)| Time	  |
-----------------------------------------------------------------------------------
|   0 | SELECT STATEMENT     |		  | 36318 |   212K|    24   (0)| 00:00:01 |
|   1 |  INDEX FAST FULL SCAN| COMMUNE_PK | 36318 |   212K|    24   (0)| 00:00:01 |
-----------------------------------------------------------------------------------

--       Resultat Sans Index

-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    | 36318 |	212K|	 65   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| COMMUNE | 36318 |	212K|	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------




-- Le cout d'execution de la requête avec index " (cost) = 24" est moins que  celui de la requête sans index (cost) = 65 !!
-- le systeme parcour toute la table commune en utilisant l'index en l'ocurence la clé primaire

----------------------------    2. select nom Com from commune ;-------------------

explain plan for select /*+ no_index(c)*/ nom_com from commune c;

select plan_table_output from table(dbms_xplan.display());

explain plan for select nom_com from commune c;

--     Avec Index
-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    | 36318 |	425K|	 65   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| COMMUNE | 36318 |	425K|	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------


-- sans Index

-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    | 36318 |	425K|	 65   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| COMMUNE | 36318 |	425K|	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------

--- pour les deux requetes avec ou sans index on a eu le même tepms d'execution et le même cout d'execution
--- puisque on utilise pas l'index(code_insee) pour la requette


--------------------------- 3. select nom Com, code_insee from commune ;

explain plan for select /*+ no_index(c)*/ nom_com, code_insee from commune c;

select plan_table_output from table(dbms_xplan.display());

explain plan for select nom Com, code_insee from commune;




---    Avec index

-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    | 36318 |	425K|	 65   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| COMMUNE | 36318 |	425K|	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------


----- Sans Indexes
-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    | 36318 |	638K|	 65   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| COMMUNE | 36318 |	638K|	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------


-- Dans ce cas la on eu le meme resultat puisque l'optimiseur n'utilise pas l'indexe
--pour parcourir la table




------------------------4. select nom Com from commune where code insee=’34192’ ;

explain plan for select /*+ no_index(c)*/ nom_com from commune c;

select plan_table_output from table(dbms_xplan.display());

explain plan for select nom_Com from commune where code_insee=’34192’;

--   avec Index
-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    | 36318 |	638K|	 65   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| COMMUNE | 36318 |	638K|	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------

-- sans Index

-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    | 36318 |	425K|	 65   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| COMMUNE | 36318 |	425K|	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------


-- -- Dans ce cas la on eu le meme resultat puisque l'optimiseur n'utilise pas l'indexe pour la recherche
--- et le systeme parcour toute la table pour chercher le nom de toutes les commune
--    puisque l'indexe est pres dans le code_com pas sur le nom_com
-- le systeme n'utilise pas l'indexe 


----    5. select nom-Com from commune where code_insee like ’34%’ ;
            
explain plan for select /*+ no_index(c)*/ nom_com from commune c;

select plan_table_output from table(dbms_xplan.display());

explain plan for select nom_Com from commune where code_insee like '34%';

--   avec Index
------------------------------------------------------------------------------------------
| Id  | Operation		    | Name	 | Rows  | Bytes | Cost (%CPU)| Time	 |
------------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT	    |		 |    17 |   306 |     4   (0)| 00:00:01 |
|   1 |  TABLE ACCESS BY INDEX ROWID| COMMUNE	 |    17 |   306 |     4   (0)| 00:00:01 |
|*  2 |   INDEX RANGE SCAN	    | COMMUNE_PK |    17 |	 |     2   (0)| 00:00:01 |
------------------------------------------------------------------------------------------

--  Sans Index

-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    | 36318 |	425K|	 65   (0)| 00:00:01 |
|   1 |  TABLE ACCESS FULL| COMMUNE | 36318 |	425K|	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------


---  le cout d'execusion de la requete avec index et moi que celui de sans index
--   puisque L'optimiseur a utiliser l'index pour parcourir la table commune pour chercher 
--- le nom_com qui correspond au code_insee qui commence avec 34


----             6. select nom_Com from commune where code_insee like '%392' ;



--   avec Index

explain plan for select nom_Com from commune where code_insee like '%392';

select plan_table_output from table(dbms_xplan.display());

-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    |  1816 | 32688 |	 65   (0)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| COMMUNE |  1816 | 32688 |	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

   1 - filter("CODE_INSEE" LIKE '%392')


---- sans Index
explain plan for select /*+ no_index(c)*/ nom_com from commune c where code_insee like '%392';

select plan_table_output from table(dbms_xplan.display());

-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    |  1816 | 32688 |	 65   (0)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| COMMUNE |  1816 | 32688 |	 65   (0)| 00:00:01 |
-----------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

   1 - filter("CODE_INSEE" LIKE '%392')

-- dans ce ca le systeme n'utilise pas l'index car  



------------7. select nom_Com from commune where code_insee >= 34 ;------------

explain plan for select nom_Com from commune where code_insee >= 34;
select plan_table_output from table(dbms_xplan.display());
explain plan for select /*+ no_index(c)*/ nom_com from commune c where code_insee >= 34;


----------Resultat avec Indexe
-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    |  1816 | 36320 |	 66   (2)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| COMMUNE |  1816 | 36320 |	 66   (2)| 00:00:01 |
-----------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------

   1 - filter(TO_NUMBER("CODE_INSEE")>=34)
   
----------Resultat sans Indexe

-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    |  1816 | 36320 |	 66   (2)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| COMMUNE |  1816 | 36320 |	 66   (2)| 00:00:01 |
-----------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------

   1 - filter(TO_NUMBER("CODE_INSEE")>=34)
   
---dans ce ca le systeme n'utilise pas l'index car la requête est posée sur un type numérique,
---le code_insee etant de type varchar2
-- donc l'otimiseur ne peut pas utiliser l'index commune_pk



-----------------8---select nom_Com from commune where code_insee in (’09330’,’09331’,’09332’,’09334’);

explain plan for select nom_Com from commune where code_insee in ('09330','09331','09332','09334');
select plan_table_output from table(dbms_xplan.display());
explain plan for select /*+ no_index(c)*/ nom_Com from commune c where code_insee in ('09330','09331','09332','09334');

--------------------Resultat avec Index
-------------------------------------------------------------------------------------------
| Id  | Operation		     | Name	  | Rows  | Bytes | Cost (%CPU)| Time	  |
-------------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT	     |		  |	4 |    80 |	6   (0)| 00:00:01 |
|   1 |  INLIST ITERATOR	     |		  |	  |	  |	       |	  |
|   2 |   TABLE ACCESS BY INDEX ROWID| COMMUNE	  |	4 |    80 |	6   (0)| 00:00:01 |
|*  3 |    INDEX UNIQUE SCAN	     | COMMUNE_PK |	4 |	  |	5   (0)| 00:00:01 |
-------------------------------------------------------------------------------------------


PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Predicate Information (identified by operation id):
---------------------------------------------------

   3 - access("CODE_INSEE"='09330' OR "CODE_INSEE"='09331' OR "CODE_INSEE"='09332'
	      OR "CODE_INSEE"='09334')


--------------Resultat sans Index
-----------------------------------------------------------------------------
| Id  | Operation	  | Name    | Rows  | Bytes | Cost (%CPU)| Time     |
-----------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |	    |	  4 |	 80 |	 66   (2)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| COMMUNE |	  4 |	 80 |	 66   (2)| 00:00:01 |
-----------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

   1 - filter("CODE_INSEE"='09330' OR "CODE_INSEE"='09331' OR
	      "CODE_INSEE"='09332' OR "CODE_INSEE"='09334')


---Dans ce cas l'optimiseur utilise l'index contrairement a la requête 5 dont la quelle on utilise les intervales
-----donc il va au préalable stocker les valeurs recuperées grasse au code_insee de l'index commune_pk dans une liste
------- pour recuperer ensuite les noms des communes dans la table commune


------------#################################  exercice 2   ############################
--------------------------
----------------------------------
--------------------------------------------
--------------------------------------------------------
-------------------------------------------------------------
explain plan for select * from commune where nom_com like 'MO%';

select plan_table_output from table(dbms_xplan.display());
-----------------------------------------------------------------------------------------
| Id  | Operation		    | Name	| Rows	| Bytes | Cost (%CPU)| Time	|
-----------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT	    |		|     7 |   259 |     9   (0)| 00:00:01 |
|   1 |  TABLE ACCESS BY INDEX ROWID| COMMUNE	|     7 |   259 |     9   (0)| 00:00:01 |
|*  2 |   INDEX RANGE SCAN	    | COM_INDEX |     7 |	|     2   (0)| 00:00:01 |
-----------------------------------------------------------------------------------------

Predicate Information (identified by operation id):

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------

   2 - access("NOM_COM" LIKE 'MO%')
       filter("NOM_COM" LIKE 'MO%')
       
 --------   2
 explain plan for select * from departement where dep >= 25 and dep <= 45;
select plan_table_output from table(dbms_xplan.display());

| Id  | Operation	  | Name	| Rows	| Bytes | Cost (%CPU)| Time	|
---------------------------------------------------------------------------------
|   0 | SELECT STATEMENT  |		|     1 |    22 |     3   (0)| 00:00:01 |
|*  1 |  TABLE ACCESS FULL| DEPARTEMENT |     1 |    22 |     3   (0)| 00:00:01 |
---------------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

   1 - filter(TO_NUMBER("DEP")>=25 AND TO_NUMBER("DEP")<=45)
   
    --------   3
   
explain plan for select /*+ no_index(c)*/ nom_com, nom_dep, nom_reg from commune c, departement d, region r where d.dep = c.dep and r.reg=d.reg;
 select plan_table_output from table(dbms_xplan.display());
       
    
    alter table departement add constraint departement_pk primary key (dep);
    --alter table commune add constraint commune_fk1 foreign key (dep) references departement(dep);
    create index idx_commune_dept on commune(dep);
    --table region
    alter table region add constraint region_pk primary key (reg);
    create index idx_departement_region on departement(reg);
    
    explain plan for select nom_com, nom_dep, nom_reg from commune c, departement d, region r where d.dep = c.dep and r.reg=d.reg;
    select plan_table_output from table(dbms_xplan.display());
    
    --------------------------------------------------------------------------------------------------------
| Id  | Operation		      | Name		       | Rows  | Bytes | Cost (%CPU)| Time     |
--------------------------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT	      | 		       | 36318 |  1808K|    72	 (3)| 00:00:01 |
|*  1 |  HASH JOIN		      | 		       | 36318 |  1808K|    72	 (3)| 00:00:01 |
|   2 |   MERGE JOIN		      | 		       |   101 |  3434 |     6	(17)| 00:00:01 |
|   3 |    TABLE ACCESS BY INDEX ROWID| DEPARTEMENT	       |   101 |  1818 |     2	 (0)| 00:00:01 |
|   4 |     INDEX FULL SCAN	      | IDX_DEPARTEMENT_REGION |   101 |       |     1	 (0)| 00:00:01 |
|*  5 |    SORT JOIN		      | 		       |    27 |   432 |     4	(25)| 00:00:01 |

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
|   6 |     TABLE ACCESS FULL	      | REGION		       |    27 |   432 |     3	 (0)| 00:00:01 |
|   7 |   TABLE ACCESS FULL	      | COMMUNE 	       | 36318 |   602K|    65	 (0)| 00:00:01 |
--------------------------------------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   1 - access("D"."DEP"="C"."DEP")
   5 - access("R"."REG"="D"."REG")
       filter("R"."REG"="D"."REG")
       
       
       
------4
explain plan for select count (*), nom_dep from commune c, departement d where c.dep = d.dep group by nom_dep;
select plan_table_output from table(dbms_xplan.display());
-------------------------------------------------------------------------------------------
| Id  | Operation	       | Name		  | Rows  | Bytes | Cost (%CPU)| Time	  |
-------------------------------------------------------------------------------------------
|   0 | SELECT STATEMENT       |		  |   101 |  1919 |    27  (12)| 00:00:01 |
|   1 |  HASH GROUP BY	       |		  |   101 |  1919 |    27  (12)| 00:00:01 |
|*  2 |   HASH JOIN	       |		  | 36318 |   673K|    25   (4)| 00:00:01 |
|   3 |    TABLE ACCESS FULL   | DEPARTEMENT	  |   101 |  1515 |	3   (0)| 00:00:01 |
|   4 |    INDEX FAST FULL SCAN| IDX_COMMUNE_DEPT | 36318 |   141K|    21   (0)| 00:00:01 |
-------------------------------------------------------------------------------------------

PLAN_TABLE_OUTPUT
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------

   2 - access("C"."DEP"="D"."DEP")
   
   
--######################### exercice 3 ----------
----------
--------------------------
---------------------------------------------------------------
-------------------------------------------------------------------------------------------------

--1. Exprimez la requête donnez le nom, la latitude et la longitude des communes qui se situent dans les départements de l’Hérault et du Gard sous différentes formes (expression d’une semi-jointure) : sous forme de jointure puis sous forme de requête imbriquée (test de vacuité (exists) et test d’appartenance (in)). Quelle est l’écriture qui vous semble la moins coûteuse (utilisez set timing et explain ) ? Quels sont les opérateurs physiques exploités respectivement pour exprimer la jointure ? Exploiter les directives (hint) pour forcer le choix d’un opérateur (par exemple use nl). Construisez sur papier un arbre algébrique puis les plans physiques correspondant à chaque plan d’exécution choisi.


set timing on

--premier cas (in)

explain plan for select nom_com, latitude, longitude from commune where dep in (
	select dep from departement where nom_dep in('HERAULT','GARD'));

	
--deuxième cas (join)
 
explain plan for select nom_com, latitude, longitude from commune c
inner join departement d on c.dep = d.dep
where nom_dep  in('HERAULT','GARD');

select plan_table_output from table(dbms_xplan.display());

--troisième cas (exists)

explain plan for select nom_com, latitude, longitude from commune c where exists(
select * from departement d where c.dep = d.dep and nom_dep in ('HERAULT','GARD'));


--Quatrieme cas Union
explain plan for select nom_com, latitude, longitude from commune c, departement d where c.dep=d.dep and nom_dep='HERAULT' 
union select nom_com, latitude, longitude from commune c, departement d where c.dep=d.dep and nom_dep='GARD';




--2. Ecrivez la requête de différentes manières : donnez le nom des communes, le nom de leur département et de leur région respectifs lorsque ces communes sont situées dans les régions Midi-Pyrénées, Languedoc-Roussillon et Provence-Alpes-Côte d’Azur. Commentez les plans d’exécution obtenus.

--premiere cas (join)
select nom_com, nom_dep, nom_reg from commune c
	inner join departement d on c.dep = d.dep
		inner join region r on d.reg = r.reg
			where nom_reg in ('MIDI-PYRENEES', 'LANGUEDOC-ROUSSILLON', 'PROVENCE-ALPES-COTE D''AZUR') 
				and code_insee in(select codeLieu from monument where typemonument='musee');
				
---deuxieme cas UNION

select nom_com, nom_dep, nom_reg from commune c, departement d, region r, monument m 
where c.dep=d.dep and d.reg=r.reg and c.code_insee = m.codeLieu and nom_reg='MIDI-PYRENEES' and typemonument='musee'
union 
select nom_com, nom_dep, nom_reg from commune c, departement d, region r, monument m 
where c.dep=d.dep and d.reg=r.reg and c.code_insee = m.codeLieu and nom_reg='LANGUEDOC-ROUSSILLON' and typemonument='musee'
union 
select nom_com, nom_dep, nom_reg from commune c, departement d, region r, monument m 
where c.dep=d.dep and d.reg=r.reg and c.code_insee = m.codeLieu and nom_reg='PROVENCE-ALPES-COTE D''AZUR'and typemonument='musee';

---troisieme cas(Jointure)

select distinct nom_com, nom_dep, nom_reg from commune c, departement d, region r, monument m 
where c.dep=d.dep and d.reg=r.reg and c.code_insee = m.codeLieu and nom_reg 
in ('MIDI-PYRENEES','PROVENCE-ALPES-COTE D''AZUR' ,'LANGUEDOC-ROUSSILLON')and typemonument='musee';


--------------------------------------------- Exercice 5 ----------------------------------------------

explain plan set statement_id ='pe4'
for select nom_com, latitude, longitude, nom_dep from commune c, departement d
where d.dep in ('30','34') and c.dep = d.dep;



create or replace procedure exo5 (statementId in varchar)
is
cursor C is select id, parent_id, operation, object_name, object_type, cost, cardinality, bytes, time from plan_table where statement_id = statementId;
begin
for t in C
loop
	dbms_output.put_line(t.id ||'  (parent : '||t.parent_id||')  '||rpad('=',trunc(((t.parent_id)+1)/(1/2)),'=')||'  [ bytes: '||t.bytes||'  ,time: '||t.time||'  ,cost: '||t.cost||'  ]  '||rpad('=',trunc((t.parent_id+1)/(1/2)),'=')||'   '||t.operation||'    '||t.object_name);
end loop;
end;
/

exec exo5('pe4')




    










