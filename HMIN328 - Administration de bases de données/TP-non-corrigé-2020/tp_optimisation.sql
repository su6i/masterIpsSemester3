-- 1. Préambule
-- Le sch ́ema comprend les relations Commune, Departement et Region qui peuvent être créées
-- à partir du sch ́ema P00000009432. 
-- Tous les exercices sont `a faire `a la fois sur papier et sur machine.

-- 2. Exercices sur papier
-- Vous r ́epondrez aux questions suivantes :

-- 1. Exercice 1 : 
-- Vous  ́ecrirez en alg`ebre relationnelle et dediff ́erentes mani`eres, la requˆete qui renvoiele 
-- nom de la commune (en majuscules), ses latitude, longitude et numéro de département, 
-- quandle nom de la commune commence par un M et qu’elle se situe dans les d ́epartements 
-- 30 ou 34.Vous proposerez ces requˆetes  ́egalement dans le langage SQL. Vous indiquerez en 
-- le justifiantla forme qui vous semble la plus coˆuteuse.

-- First way:
select NOMCOMMAJ, LATITUDE, LONGITUDE, NUMDEP from commune where NOMCOMMAJ like 'M%' and (NUMDEP='30' or NUMDEP='34');
select NOMCOMMAJ, LATITUDE, LONGITUDE, NUMDEP from commune where NOMCOMMAJ like 'M%' and NUMDEP in ('30','34');

-- Second way:
select NOMCOMMAJ, LATITUDE, LONGITUDE, NUMDEP from commune where NOMCOMMAJ like 'M%' and NUMDEP='30';
select NOMCOMMAJ, LATITUDE, LONGITUDE, NUMDEP from commune where NOMCOMMAJ like 'M%' and NUMDEP='34';

-- Third way:
(select NOMCOMMAJ, LATITUDE, LONGITUDE, NUMDEP from commune where NOMCOMMAJ like 'M%' and NUMDEP='30')
UNION
(select NOMCOMMAJ, LATITUDE, LONGITUDE, NUMDEP from commune where NOMCOMMAJ like 'M%' and NUMDEP='34');


-- Probabely the third one has the most expensive rate and the first one has the cheapest rate of execuion.


-- 2. Exercice 2 :
CREATE TABLE departementAdministration AS SELECT * FROM P00000009432.departement;
CREATE TABLE region AS SELECT * FROM P00000009432.region;

-- (a) 
-- (a) Vous  ́ecrirez en alg`ebre relationnelle et de diff ́erentes mani`eres, la requˆete qui renvoie le nomde la commune 
-- (en majuscules), et le num ́ero de d ́epartement, quand cette commune se situedans le d ́epartement de l’AVEYRON. 
-- Vous indiquerez en le justifiant la forme alg ́ebriquequi vous semble la moins coˆuteuse
-- All below queries have the same result.
select NOMCOMMAJ, commune.NUMDEP from commune, DEPARTEMENTADMINISTRATION where (DEPARTEMENTADMINISTRATION.numdep = commune.numdep and NOMDEPMAJ = 'AVEYRON');
select NOMCOMMAJ, c.NUMDEP from commune c, DEPARTEMENTADMINISTRATION d where (d.numdep = c.numdep and NOMDEPMAJ = 'AVEYRON');
select NOMCOMMAJ, c.NUMDEP from commune c JOIN DEPARTEMENTADMINISTRATION d ON (d.numdep = c.numdep and NOMDEPMAJ = 'AVEYRON');

-- (b) 
-- (b) Cette requˆete peut ˆetre trait ́ee sous la forme d’une semi-jointure. Donnez l’ ́ecriture alg ́ebriquede cette 
-- semi-jointure et proposez deux fa ̧cons de l’exprimer en SQL (pr ́edicats EXISTS etIN)
select NOMCOMMAJ, c.NUMDEP from commune c where EXISTS (select * from DEPARTEMENTADMINISTRATION d where (d.numdep = c.numdep and NOMDEPMAJ = 'AVEYRON'));
select NOMCOMMAJ, c.NUMDEP from commune c where numdep in (select numdep from DEPARTEMENTADMINISTRATION d where (d.numdep = c.numdep and NOMDEPMAJ = 'AVEYRON'));


set autotrace on STATISTICS -- To have at the same time both statictics and the result


-- Verify the cost of two queries, USE_NL use nested loop 
set autotrace traceonly
select /*+ USE_NL (c,d) */ NOMCOMMAJ, c.NUMDEP from commune c join DEPARTEMENTADMINISTRATION d on (d.numdep = c.numdep);
select  NOMCOMMAJ, c.NUMDEP from commune c join DEPARTEMENTADMINISTRATION d on (d.numdep = c.numdep);



-- 3. Exercice 3 :
-- (a) Vous d ́efinirez plusieurs arbres alg ́ebriques pour la requˆete qui renvoie le nom d’une com-mune, sa latitude et 
-- sa longitude d`es lors que cette communevoit son nom commencer parun M et que cette commune est une commune de la r ́egion 
-- OCCITANIE. Vous justifierezle choix d’un plan d’ex ́ecution qui vous semble proche de celui qui pourrait ˆetre choisi 
-- parl’optimiseur.


-- (b) La requˆete propos ́ee est une requˆete qui peut s’exprimer en SQL au travers de jointuresnaturelles entre Commune, 
-- Departement et Region. Vous  ́ecrirez donc cette requˆete en SQL.Elle peut  ́egalement ˆetre trait ́ee comme une semi-jointure 
-- (seuls des attributs de Communesont retourn ́es). Vous  ́ecrirez en cons ́equence cette mˆeme requˆete sous une nouvelle 
-- formealg ́ebrique et de deux nouvelles fa ̧cons en SQL : recours au pr ́edicat EXISTS (test de vacuit ́e)et au pr ́edicat IN 
-- (test d’appartenance) avec du ”select” imbriqu ́e.


-- (c) Vous  ́ecrirez  ́egalement la requˆete SQL via une double jointure mais en sp ́ecifiant ces jointuresvia des INNER JOIN 
-- dans la clause FROM. Vous regarderez si cette syntaxe l ́eg`erementdiff ́erente modifie le plan d’ex ́ecution choisi par 
-- l’optimiseur.

