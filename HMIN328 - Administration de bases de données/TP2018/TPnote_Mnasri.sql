--TP note Mehdi Nasri


--declare package
create or replace package TPnote
as
procedure InformationStockageTable(Usager in varchar, nomtable in varchar);
procedure InformationStockageIndex(Usager in varchar, nomtable in varchar);
procedure blocsCache(Usager in varchar, typeObjet in varchar);
end TPnote;
/



--body package
create or replace package body TPnote
as

procedure InformationStockageTable(Usager in varchar, nomtable in varchar)
as
cursor c is
select segment_name, segment_type, t.tablespace_name as tablespace_name, s.extents as extents, s.blocks as blocks, num_rows, avg_row_len from dba_segments s, user_tables t where t.table_name = s.segment_name and owner = Usager and table_name = nomtable;
--Usager exception
begin
dbms_output.put_line('************************************************');
dbms_output.put_line(rpad('nom Segment',25)||rpad('type Segment',20)||rpad('Espace table',20)||rpad('Nbre extensions',20)||rpad('Nbre blocs alloues',20)||rpad('Taille en Mo',20));
for c_t in c
loop
dbms_output.put_line(rpad(c_t.segment_name,25) || rpad(c_t.segment_type,20) || rpad(c_t.tablespace_name,20) || rpad(c_t.extents,20) || rpad(c_t.blocks,20) || rpad(round((c_t.num_rows*c_t.avg_row_len)/(1024*1024),2),20));
end loop;
dbms_output.put_line('************************************************');
/*if (Usager or nomtable) is null then raise Usager;
end if;
exception
when Usager then dbms_output.put_line('Usager ou table inexistants');
when Usager then dbms_output.put_line('Error');*/
end;




procedure InformationStockageIndex(Usager in varchar, nomtable in varchar)
as
cursor c is
select segment_name, segment_type, s.tablespace_name as tablespace_name, s.extents as extents, s.blocks as blocks, num_rows, leaf_blocks from dba_segments s, user_indexes t where (t.table_name = s.segment_name or t.index_name=s.segment_name) and s.owner =Usager and t.table_name = nomtable;
begin
for c_t in c
loop
dbms_output.put_line('************************************************');
dbms_output.put_line(rpad('nom Segment',25)||rpad('type Segment',20)||rpad('Espace table',20)||rpad('Nbre extensions',20)||rpad('Nbre blocs alloues',20)||rpad('Taille en Mo',20));
dbms_output.put_line(rpad(c_t.segment_name,25) || rpad(c_t.segment_type,20) || rpad(c_t.tablespace_name,20) || rpad(c_t.extents,20) || rpad(c_t.blocks,20) || rpad(round((c_t.num_rows*c_t.leaf_blocks)/(1024*1024),2),20));
end loop;
dbms_output.put_line('************************************************');
end;



procedure blocsCache(Usager in varchar, typeObjet in varchar)
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

end TPnote;
/



exec TPnote.InformationStockageTable('MNASRI', 'COMMUNE')
exec TPnote.InformationStockageIndex('MNASRI', 'COMMUNE')
exec TPnote.blocsCache('MNASRI', 'INDEX')

