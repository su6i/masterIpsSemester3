# Validation XML - DTD avec BaseX

pour charger la DTD, vous pouvez utiliser la fonction
Let $schemaDTD = doc("/chemin/vers/fichier/DTD/dans/mon/système/de/fichiers") return ( ... )
pour valider vos données utiliser la fonction validate:dtd() fournie par BaseX
validate:dtd($mesDonnees, $schemaDTD)
