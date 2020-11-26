package com.masterips.javaeeproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.entities.Lieu;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, String> {

	@Query("select d from Departement d where d.num_dep=:x")
	Departement findOne(@Param("x") String numDep);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Departement d SET d.num_dep = :num_dep and d.chef_lieu = :chef_lieu and d.nom_dep = :nom_dep WHERE d.num_dep = :num_dep")
    Departement updateDepartement(@Param("num_dep") String numDep, @Param("nom_dep") String nomDep, @Param("chef_lieu") Lieu codeInsee);

}
