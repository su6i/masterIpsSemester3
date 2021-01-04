package com.masterips.javaeeproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.entities.Lieu;
import java.util.List;

@Repository
@Transactional(readOnly = true) 
public interface LieuRepository extends JpaRepository<Lieu, String> {
	
	
	@Query("select l from Lieu l where l.nomCom=:x")
	public List<Lieu> findLieuBynomCom(@Param ("x")String nomCom);
	
	//@Query("Select l from lieu l where l.nomCom like %:nomCom%")
	public List<Lieu> findByNomComContaining(@Param("%:nomCom%:") String nomCom);

	@Query("select l from Lieu l where l.codeInsee=:x")
	public Lieu getLieu(@Param("x")String codeInsee);

	@Query("select c from Lieu c where  c.nomCom like CONCAT('%',:x,'%')")
	public List<Lieu> getByNameLieuContaining(@Param("x") String nom);
	
	@Modifying
	@Query("delete from Lieu c where  c.codeInsee = ?1")
	public boolean deleteLieuById(String codeInsee);

	

}
