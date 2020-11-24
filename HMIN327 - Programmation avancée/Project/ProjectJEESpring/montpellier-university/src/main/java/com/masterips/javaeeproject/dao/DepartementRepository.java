package com.masterips.javaeeproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masterips.javaeeproject.entities.Departement;

public interface DepartementRepository extends JpaRepository<Departement, String> {

	@Query("select d from Departement d where d.numDep=:x")
	Departement findOne(@Param("x") String numDep);


}
