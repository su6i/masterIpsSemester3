--tp note 2018 Mehdi NASRI

create or replace procedure InformationStockageTable(Usager in varchar, nomtable in varchar)
as
cursor c is
select segment_name, segment_type, t.tablespace_name as tablespace_name, s.extents as extents, s.blocks as blocks, num_rows, avg_row_len from dba_segments s, user_tables t where t.table_name = s.segment_name and owner = Usager and table_name = nomtable;
personne exception
begin
dbms_output.put_line('************************************************');
for c_t in c
loop
dbms_output.put_line('nom segment : '||c_t.segment_name|| '    type segment : '||c_t.segment_type|| '     espace table : '||c_t.tablespace_name|| '    nbre exte : '||c_t.extents|| '    nbre blocs : '||c_t.blocks || '   Taill en Mo : '||c_t.num_rows/(1024*1024));
end loop;
dbms_output.put_line('************************************************');
if 
end;
/

--fin operation 
  if IndexOuTablevide then raise IndexOuTablevide_e;
 --end if utilisateurvide then raise utilisateur_e;
    end if;
 exception
when IndexOuTablevide then  dbms_output.put_line('Pas de table ou de index correspondante pour le nom  donné!!');
when utilisateurvide_e then dbms_output.put_line('nom d utilisateur incorrecte');
when others then dbms_output.put_line('others problemes');
 end;
/ 



begin
InformationsTAblesEtInde'IHAROUNAIBRA','emp_pk');
end;
/




if nomtable is null
then exception
raise_application_error(-20099, 'usager ou table inexistants');
end if;
end;
/


exec InformationStockageTable('MNASRI', 'COMMUNE')







create or replace procedure InformationStockageIndex(Usager in varchar, nomtable in varchar)
as
cursor c is
select segment_name, segment_type, s.tablespace_name as tablespace_name, s.extents as extents, s.blocks as blocks, num_rows from dba_segments s, user_indexes t where (t.table_name = s.segment_name or t.index_name=s.segment_name) and s.owner =Usager and t.table_name = nomtable;
begin
for c_t in c
loop
dbms_output.put_line('************************************************');
dbms_output.put_line(' ');
dbms_output.put_line('nom segment : '||c_t.segment_name|| '    type segment : '||c_t.segment_type|| '     espace table : '||c_t.tablespace_name|| '    nbre exte : '||c_t.extents|| '    nbre blocs : '||c_t.blocks || '   Taill en Mo : '||c_t.num_rows/(1024*1024));
end loop;
dbms_output.put_line('************************************************');
end;
/


exec InformationStockageIndex('MNASRI', 'COMMUNE')




create or replace procedure blocsCache(Usager in varchar, typeObjet in varchar)
as
cursor c is
select distinct o.object_name, o.object_type, b.block#, b.class#, b.status from dba_objects o, v$bh b where owner = Usager and o.object_id = b.objd and object_type = typeObjet;
begin
dbms_output.put_line('Example retour');
dbms_output.put_line(' ');
dbms_output.put_line(' ');
for c_t in c
loop
dbms_output.put_line(rpad('nom objet',25)||rpad('type object',25)||rpad('numero bloc',25)||rpad('classe',15)||rpad('statut',15));
dbms_output.put_line(rpad(c_t.object_name,25)||rpad(c_t.object_type,25)||rpad(c_t.block#,25)||rpad(c_t.class#,15)||rpad(c_t.status,15));
dbms_output.put_line('**************************************************************');
end loop;
end;
/






exec blocsCache('MNASRI', 'INDEX')





create or replace procedure InformationStockageTable(Usager in varchar, nomtable in varchar)
as
cursor c is
select segment_name, segment_type, t.tablespace_name as tablespace_name, s.extents as extents, s.blocks as blocks, num_rows, avg_row_len from dba_segments s, user_tables t where t.table_name = s.segment_name and owner = Usager and table_name = nomtable;
begin
dbms_output.put_line('************************************************');
dbms_output.put_line(rpad('nom Segment',25)||rpad('type Segment',20)||rpad('Espace table',20)||rpad('Nbre extensions',20)||rpad('Nbre blocs alloues',20)||rpad('Taille en Mo',20));
for c_t in c
loop
dbms_output.put_line(rpad(c_t.segment_name,25) || rpad(c_t.segment_type,20) || rpad(c_t.tablespace_name,20) || rpad(c_t.extents,20) || rpad(c_t.blocks,20) || rpad(round((c_t.num_rows*c_t.avg_row_len)/(1024*1024),2),20));
end loop;
dbms_output.put_line('************************************************');
exception
when Usager is null then dbms_output.put_line(' Attention un seul tuple attendu');
when nomtable is null then dbms_output.put_line(' Attention un seul tuple');
when other then dbms_output.put_line(' Attention un');
end;
/

















/*select segment_name, segment_type, s.tablespace_name as tablespace_name, s.extents as extents, s.blocks as blocks, num_rows from dba_segments s, user_indexes t where (t.table_name = s.segment_name or t.index_name=s.segment_name) and s.owner ='MNASRI' and t.table_name = 'COMMUNE';*/


/*select segment_name, segment_type, t.tablespace_name as tablespace_name, s.extents as extents, s.blocks as blocks, num_rows, avg_row_len from dba_segments s, user_tables t where t.table_name = s.segment_name and owner = 'MNASRI' and table_name = 'COMMUNE';*/



/*begin
dbms_output.put_line('************************************************');
for c_t in c
loop
dbms_output.put_line('nom segment : '||c_t.segment_name|| '    type segment : 'c_t.segment_type|| '     espace table : 'c_t.tablespace_name|| '    nbre exte :'c_t.extents|| '    nbre b'c_t.blocks || '   Taill en Mo : '(round((c_t.num_rows*c_t.avg_row_len)/(1024*1024),2));
end loop;*/











