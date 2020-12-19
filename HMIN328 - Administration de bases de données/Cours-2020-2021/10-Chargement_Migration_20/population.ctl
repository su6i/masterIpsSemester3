-- Amir SHIRALI POUR

LOAD DATA 
CHARACTERSET UTF8
INFILE 'tuples.csv'
APPEND
INTO TABLE population
FIELDS TERMINATED BY ','
(
    codeinsee  "to_char(:codeinsee)",
    annee "to_number(:annee)",
    val_population "to_number(:val_population)"
)



