create or replace package tpnote2
as
procedure ListeTuples(numeroBloc in integer);
procedure BlocsMoinsRemplisCommune;
procedure AllocationTous;
procedure AllocationUsager (usager in varchar);
end tpnote2;
/

create or replace package body tpnote2
as

procedure ListeTuples(numeroBloc in integer)
is 
cursor c is select rowid, code_insee from commune where dbms_rowid.rowid_block_number(rowid)=numeroBloc;
begin 
for t in c
  loop
    dbms_output.put_line('identifiant tuple (rowid) : '||t.rowid|| ' -- code insee  '||t.code_insee);
  end loop;
end;

procedure BlocsMoinsRemplisCommune
is 
cursor c is select dbms_rowid.rowid_block_number(rowid) as numeroBloc,count(*) as nbreTuples from commune 
group by dbms_rowid.rowid_block_number(rowid)
having count(*) <= all (select count(*) from commune group by dbms_rowid.rowid_block_number(rowid));
cpt NUMBER := 0;
BEGIN
for t in c
  loop
    dbms_output.put_line('numero bloc : '||t.numeroBloc|| '  ||  '||'nombre de tuples par bloc : '||t.nbreTuples);
    ListeTuples(t.numeroBloc);
    cpt := c%rowcount;
  end loop;
  dbms_output.put_line('******************************************');
  dbms_output.put_line('Nombre de blocs les moins remplis : '|| cpt);
  dbms_output.put_line('******************************************');
end;

procedure AllocationUsager (usager in varchar)
is 
cursor c is select owner, segment_type, SUM(blocks) AS blocs ,SUM(bytes) as octets from dba_segments where owner = usager group by owner, segment_type order by owner;

begin 
dbms_output.put_line(rpad('Usager',20)||rpad('Type segment',20)||rpad('Blocs',15)||rpad('Taille (octets)',15));
dbms_output.put_line('------------------------------------------------------------------');
for t in c
  loop
    dbms_output.put_line(rpad(t.owner,20)||rpad(t.segment_type,20)||rpad(t.blocs,15)||rpad(t.octets,15));
  end loop;
end;

procedure AllocationTous
is cursor c is select owner, segment_type, SUM(blocks) AS blocs ,SUM(bytes) as octets from dba_segments group by owner, segment_type order by owner;
begin 
dbms_output.put_line(rpad('Usager',20)||rpad('Type segment',20)||rpad('Blocs',15)||rpad('Taille (octets)',15));
dbms_output.put_line('------------------------------------------------------------------');
for t in c
  loop
    dbms_output.put_line(rpad(t.owner,20)||rpad(t.segment_type,20)||rpad(t.blocs,15)||rpad(t.octets,15));
  end loop;
end;

end tpnote2;
/

exec tpnote2.BlocsMoinsRemplisCommune
exec tpnote2.AllocationUsager('MNASRI')
exec tpnote2.AllocationTous
