package com.masterips.javaeeproject.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.entities.Monument;

@Repository
@Transactional(readOnly = true) 
public interface MonumentRepository extends JpaRepository<Monument, String> {
	
	
	public List<Monument>findByNomM(String nomM, Pageable pageable);
//	public Page<Monument>findByNomMPage(String nomM, Pageable pageable);
	
	@Query("select m from Monument m order by m.nomM desc")
	public Page<Monument> getAllMonuments(Pageable pageable);

    @Query("select m from Monument m order by m.nomM desc")
	public Slice<Monument> getAllMonumentsSlice(Pageable pageable);

	@Query("select c from Monument c where  c.nomM like CONCAT('%',:x,'%')")
	public List<Monument> getByNameMonumentContaining(@Param("x") String nom);
	
	@Modifying
	@Query("delete from Monument c where  c.codeM = ?1")
	public boolean deleteMonumentById(String codeM);


}
