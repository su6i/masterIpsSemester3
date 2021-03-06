package com.masterips.javaeeproject.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.masterips.javaeeproject.entities.Celebrite;



@Repository("jpa")
@Transactional(readOnly = false) 
public interface CelebriteRepository extends JpaRepository<Celebrite, Long> {
	
	
	
	@Query("select c from Celebrite c where  c.prenom=:x")	
	public List <Celebrite> getCelebriteByName(@Param("x") String prenom);

	@Query("select c from Celebrite c where  c.nom=:x")	
	public List <Celebrite> getCelebriteByFamily(@Param("x") String nom);
		
	@Query("select c from Celebrite c where  c.nom like CONCAT('%',:x,'%')")
	public List<Celebrite> getByNameContaining(@Param("x") String nom);

	@Query("select c from Celebrite c where  c.numCelebrite = :x")
	public Celebrite getCelebriteById(@Param("x") long numCelebrite);

	@Modifying
	@Query("delete from Celebrite c where  c.numCelebrite = ?1")
	public int deleteCelebriteById(long numCelebrite);

	@Modifying(clearAutomatically = true)
    @Query("UPDATE Celebrite c SET c.nom =:nom , c.prenom =:prenom , c.nationalite =:nationalite, c.epoque =:epoque WHERE c.numCelebrite = :numCelebrite")
    int updateCelebrite(@Param("nom") String nom, @Param("prenom") String prenom, @Param("nationalite") String nationalite, @Param("epoque") String epoque, @Param("numCelebrite") long numCelebrite);


	

	
	
	

}
