package com.masterips.javaeeproject.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.masterips.javaeeproject.entities.Monument;

@Transactional(readOnly = true) 
public interface MonumentRepository extends PagingAndSortingRepository<Monument, String> {
	
	
	public List<Monument>findByNomM(String nomM, Pageable pageable);
//	public Page<Monument>findByNomMPage(String nomM, Pageable pageable);
	
	@Query("select m from Monument m order by m.nomM desc")
	public List<Monument> getAllMonuments(Pageable pageable);

    @Query("select m from Monument m order by m.nomM desc")
	public Slice<Monument> getAllMonumentsSlice(Pageable pageable);

//    @Query("select m from Monument m order by m.nomM desc")
//	public List<Monument> getAllMonumentsSorted(Sort sort);
	
	

}
