exec dbms_stats.gather_schema-stats(USER)

--sql 89
explain plan for select nom_com, latitude, lonitude from commune c, departement d, regrion r
where c.dep = d.dep AND nom_reg = 'LANGUEDOC-ROUSSILLON' AND nom_com like 'M%' and d.reg = r.reg;



--sql 92
explain plan for select nom_com, latitude, longitude from commune c
inner join departement d on c.dep = d.dep
inner join region r on d.reg = r.reg
where nom_com like 'M%' and nom_reg = 'LANGUEDOC-ROUSSILLON';


select plan_table_output from table (dbms_xplan.display());




explain plan for select nom_com, latitude, longitude from commune where nom_com like 'M%' and dep in (
	select dep from departement where reg in (
		select reg from region where nom_reg = 'LANGUEDOC-ROUSSILLON' ));
		




explain plan for select nom_com, latitude, longitude from commune c where nom_com like 'M%' and exists ( 
	select * from departement d where c.dep = d.dep and exists (
		select * from region r where nom_reg = 'LANGUEDOC-ROUSSILLON' and d.reg = r.reg));
		
		
		
explain plan for 
create view v_commune as select nom_com, latitude, longitude from commune c
inner join departement d on c.dep = d.dep
inner join region r on d.reg = r.reg
where nom_com like 'M%' and nom_reg = 'LANGUEDOC-ROUSSILLON';
		




