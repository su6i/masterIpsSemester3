Requête auto-jointure pour exercice 3 Index 
(transformer en curseur explicite)


select v1.nomcommaj, dbms_rowid.rowid_block_number(v1.rowid) as numBloc, dbms_rowid.rowid_row_number(v1.rowid) as numLigne, dbms_rowid.rowid_object(v1.rowid) as idTable  from commune v1, commune v2 where dbms_rowid.rowid_block_number(v1.rowid) = dbms_rowid.rowid_block_number(v2.rowid) and v2.nomcommaj='MONTPELLIER' and v1.nomcommaj <> 'MONTPELLIER';


select count(distinct dbms_rowid.rowid_block_number(rowid)) as nombredeBlocsPourCommune 
from commune; 

analyze table commune compute statistics;


select blocks, empty_blocks, blocks+empty_blocks from user_tables where table_name ='COMMUNE'

le nombre de blocs alloués pour la table Commune est bien supérieur à ce qui est utilisé.


nombre de tuples dans chacun des blocs de données de la table commune
select count(*) as valeurs from commune group by dbms_rowid.rowid_block_number(rowid) order by valeurs;  


select segment_name, bytes/1024/1024 enMo, blocks from user_segments where segment_name ='COMMUNE';

select extent_id, bytes, blocks, segment_name from user_extents where segment_name ='COMMUNE';

desc dba_segments

-- espace de stockage par usager trié par espace croissant

select sum(bytes)/1024/1024, sum(blocks), owner from dba_segments group by owner order by sum(blocks);



Exercice 4
select * from ABC where rownum < 100;

analyze table ABC compute statistics;

SQL> select blocks, empty_blocks, avg_row_len, num_rows from user_tables where table_name = 'ABC';

    BLOCKS EMPTY_BLOCKS AVG_ROW_LEN   NUM_ROWS
---------- ------------ ----------- ----------
      7300	    124 	 50    1000000










