package com.masterips.javaeeproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.dao.CelebriteRepository;
import com.masterips.javaeeproject.dao.DepartementRepository;
import com.masterips.javaeeproject.dao.LieuRepository;
import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;



@Service
@Transactional
public class AppServicelmplementation implements AppService {
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private MonumentRepository monumentRepository;
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private CelebriteRepository celebriteRepository;

	@Override
	public void addDepartement(Departement departement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Departement getDepartement(String numDep) {
		
		
		 Departement d = departementRepository.findOne(numDep); 
		 if(numDep==null) throw new RuntimeException("Can't find entered department");
		 
		 return d;
	}
	
	
	
	@Override
	public void addLieu(Lieu lieu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMonument(Monument monument) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMonumentToLieu(Long codeM, String codeInsee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getDistanceBetweenMonuments(String nomMonA, String nomMonB) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Monument> getListMonumentsByDep(String nomDep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Monument> getListMonumentsByLieu(String nomCom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Celebrite addCelebrite(Celebrite celebrite) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
