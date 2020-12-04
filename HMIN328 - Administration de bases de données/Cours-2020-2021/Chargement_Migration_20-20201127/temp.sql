CREATE OR replace PROCEDURE Info_population_4 IS
CURSOR myCursor IS SELECT owner, segment_type, Count(segment_name),
SUM(bytes) octets, SUM(blocks) blocs,
SUM(extents) extensions FROM dba_segments GROUP BY owner, segment_type ORDER
BY octets ASC;
BEGIN
    FOR line IN myCursor
    LOOP
        dbms_output.Put_line(Chr(10) || 'owner: ' || line.owner || Chr(10) ||  ' Segment type: ' || line.segment_type || Chr(10) || ' Bytes: ' || line.octets ||
    Chr(10) || ' Blocks: ' || line.blocs);
    END LOOP;
END;
/
set serveroutput ON
exec info_population_4;