table v$sgastat
la vue v$sgastat: taille des structures System Global Area (SGA)
par exemple espace libre dans la SGA:
select * from v$sgastat where name='free memory'

----------------------------------------------------------------------------------


