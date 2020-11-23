set serveroutput on


DECLARE
object_no
integer;
row_no integer;
row_id ROWID;
BEGIN
SELECT ROWID INTO row_id FROM commune
WHERE code_Insee = '34172';
object_no := DBMS_ROWID.ROWID_OBJECT(row_id);
row_no := DBMS_ROWID.ROWID_ROW_NUMBER(row_id);
DBMS_OUTPUT.PUT_LINE('The obj. # is '||object_no||' '||row_no);
END;
/



SELECT DBMS_ROWID.ROWID_BLOCK_NUMBER(rowid), count(*) 
FROM Commune group by DBMS_ROWID.ROWID_BLOCK_NUMBER(rowid);




create or replace procedure memeQue (code in varchar)
as
cursor C is select c1.code_insee code1, c1.nom_com, DBMS_ROWID.ROWID_BLOCK_NUMBER(c1.rowid) 
as numBloc, c2.code_insee code2
from commune c1, commune c2 where DBMS_ROWID.ROWID_BLOCK_NUMBER(c1.rowid) =
DBMS_ROWID.ROWID_BLOCK_NUMBER(c2.rowid) and c2.code_insee = code and c1.code_insee <> code ; 
begin
for c_t in C
loop
dbms_output.put_line (c_t.code1||' '||c_t.nom_com||' dans meme bloc '||c_t.numBloc||' que '||c_t.code2);
end loop;
end;
/

exec memeQue('34172')


