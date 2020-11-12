CREATE OR REPLACE PROCEDURE NBRELIKERS(V_NUM_INE IN varchar,V_NB_ETU OUT NUMBER ) 
AS
BEGIN

SELECT COUNT(*) INTO V_NB_ETU
FROM APPRECIE
WHERE NUMAMI=V_NUM_INE group by numami;

DBMS_OUTPUT.PUT_LINE('Nombre d''etudiant qui apprecient:'||V_NB_ETU);
END;
/

-- test procédure
DECLARE
NB_EUD NUMBER;
BEGIN
NBRELIKERS('20112345',NB_EUD); 
DBMS_OUTPUT.PUT_LINE('Nombre d''etudiant qui apprecient:'||NB_EUD);
END;
/
----------------------------------------------------------------------------------------------


create or replace procedure NBRELIKERS(numE in varchar, nbl out integer)
as
cursor c is select numAmi, count(*) as nbl from apprecie where numAmi = numE group by numAmi;
begin
for c_t in c
loop
dbms_output.put_line('Nombre Likers : '|| c_t.nbl);
end loop;
end;
/


DECLARE
NB_EUD NUMBER;
BEGIN
NBRELIKERS('20101234',NB_EUD); 
END;
/




---------------------------------------------------------------------------------------
Select distinct E1.NumEtudiant , E1.NumAmi from Apprecie E1, Apprecie E2 where E1.NumEtudiant=E2.NumEtudiant and E2.NumAmi=E1.NumAmi;
-------------------------------------------------------------------


insert into apprecie values('20101234','20112345');
insert into apprecie values('20112345','20101234');
insert into apprecie values('20101234','20102345');
insert into apprecie values('20112345','20102345');
insert into apprecie values('20123456','20102345');
insert into apprecie values('20123456','20101234');
insert into apprecie values('20102345','20101234');



Select distinct E1.NumEtudiant , E1. NumAmi from Apprecie E1, Apprecie E2 where E1.NumAmi=E2.NumEtudiant and E1.NumEtudiant=E2.NumAmi;





















