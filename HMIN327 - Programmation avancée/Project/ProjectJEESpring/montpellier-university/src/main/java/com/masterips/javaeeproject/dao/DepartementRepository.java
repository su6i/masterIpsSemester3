package com.masterips.javaeeproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masterips.javaeeproject.entities.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, String> {

	@Query("select d from Departement d where d.numDep=:x")
	Departement findOne(@Param("x") String numDep);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Departement d SET d.numDep =:x , d.nomDep =:nomDep WHERE d.numDep =:y")
    int updateDepartement(@Param("x") String numDep, @Param("y") String nomDep);
	
	public List<Departement> findDepartementBynomDep(String nomDep);
	
	public List<Departement> findByNomDepContaining(String nomDep);
	
	

}
