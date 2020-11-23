

select rowid, code_insee from imougenot.commune;

-- create la table avec les variable de table commune de isabel
create table commune as select * from imougenot.commune;

--creation primary key
alter table commune add constraint commune_pk PRIMARY KEY (code_insee);


select index_name, index_type from user_indexes where table_name = 'COMMUNE';

select index_name, blevel, index_type from user_indexes where table_name = 'COMMUNE';

select index_name, blevel, uniqueness, num_rows from user_indexes where table_name = 'COMMUNE';


analyze index commune_pk validate structure;



select lf_rows as tuplesFeuilles, lf_blks as blocksFuilles, lf_rows_len as tailleFeuilles, br_rows as tuplesBranches, br_blks as blockBranches, br_rows_len as taillBranches from index_stats;


select rowid, rownum, code_insee from commune ;


select index_name, leaf_blocks from user_indexes;



select blocks, segment_name, segment_type from dba_segments where owner='MNASRI' and segment_type='INDEX';

select * from commune where dep like '34%';



analyze table commune compute statistics;

select avg_row_len, avg_space, blocks, table_name from user_tables where table_name = 'COMMUNE';



select tablespace_name, segment_type from user_segments where segment_name in ('COMMUNE', 'COMMUNE_PK');




-------3


DECLARE
object_no integer;
row_no integer;
row_id ROWID;
BEGIN
SELECT ROWID INTO row_id FROM commune WHERE code_Insee = '34172';
object_no := DBMS_ROWID.ROWID_OBJECT(row_id);
row_no := DBMS_ROWID.ROWID_ROW_NUMBER(row_id);
DBMS_OUTPUT.PUT_LINE('The obj. # is '||object_no||' '||row_no);
END;
/


SELECT DBMS_ROWID.ROWID_BLOCK_NUMBER(rowid), DBMS_ROWID.ROWID_OBJECT(rowid), nom_com FROM Commune where code_Insee = '34172';


select DBMS_ROWID.ROWID_BLOCK_NUMBER(rowid), count(*) from commune group by DBMS_ROWID.ROWID_BLOCK_NUMBER(rowid);


create or replace procedure memeQue (code in varchar)
as
cursor C is
select c1.code_insee, c1.nom_com, DBMS_ROWID.ROWID_BLOCK_NUMBER(c1.rowid) as numBloc from commune c1, commune c2 where DBMS_ROWID.ROWID_BLOCK_NUMBER(c1.rowid) = DBMS_ROWID.ROWID_BLOCK_NUMBER(c2.rowid) and c2.code_insee = code and c1.code_insee <> code;
begin
for c_t in C
loop
dbms_output.put_line(c_t.code_insee||' '||c_t.nom_com||' dans meme bloc '||c_t.numBloc);
end loop;
end;
/


exec memeQue('34172')



--4.2
--6
select nom_Com from commune where code_insee >= 34 ;
--7
select nom_Com from commune where code_insee in ('09330','09331','09332','09334') ;







select /*+ NO_INDEX(Commune) */ code_insee, nom_com from commune where code_insee = '09342'; -- il outilise pas index



--5

create index com_idx on commune(nom_com);

select trim(to_char(bytes, '999,999,999')) actual_bytes from user_segments where segment_name in ('COM_IDX', 'COMMUNE_PK');


















