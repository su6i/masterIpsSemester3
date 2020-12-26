package com.masterips.javaeeproject.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.masterips.javaeeproject.entities.Monument;

@Transactional(readOnly = true) 
public interface MonumentRepository extends JpaRepository<Monument, String> {
	
	
	public Page<Monument>findByNomM(String nomM, Pageable pageable);
	
	@Query("select m from Monument m order by m.nomM desc")
	public Page<Monument> getAllMonuments(Pageable pageable);
	
	

}
