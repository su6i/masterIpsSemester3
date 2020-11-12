------2.3.7-------
create or replace procedure verou
as
cursor C is select l.sid, username
from v$lock l, v$session s where block=1 and l.sid=s.sid;
begin for C_t in C
loop
dbms_output.put_line('le bloqueur est :' ||C_t.sid||'  '||C_t.username);
end loop;
end;
/

exec verou
