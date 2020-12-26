package com.masterips.javaeeproject.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.masterips.javaeeproject.entities.Celebrite;

@Transactional(readOnly = true) 
public interface CelebriteRepository extends JpaRepository<Celebrite, Long> {
	
	
	@Query("select c from Celebrite c where  c.prenom=:x")	
	public List <Celebrite> getCelebriteByName(@Param("x") String prenom);

	@Query("select c from Celebrite c where  c.nom=:x")	
	public List <Celebrite> getCelebriteByFamily(@Param("x") String nom);
	
	@Query("select c from Celebrite c where  c.nom like :x")
	public Page<Celebrite> findCelebritetByName(@Param("x")String mc, Pageable pageable);
	
//	@Query("select c from Celebrite c where  c.nom like :x")
	public List <Celebrite> findCelebritetBynom(@Param("x")String mc);
	
	//@Query("select c from Celebrite c where  c.nom like:x")
	public List<Celebrite> findByNomContaining(String nom);

	

	
	
	

}
