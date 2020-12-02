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

	@Autowired
	private AppService appService;

	
	
//	Department
	
	@Override
	public Departement addDepartement(Departement departement) {
		return departementRepository.save(departement);
	}

	@Override
	public Departement getDepartement(String numDep) {
		
		
//		 Departement d = departementRepository.findOne(numDep); 
//		 if(numDep==null) throw new RuntimeException("Can't find entered department");
//		 return d;
		 
		 return departementRepository.findOne(numDep);		 
	}
	
	@Override
	public List<Departement> getAllDepartements() {
		 return departementRepository.findAll();	 
	}
	
	

//	Lieu 
	@Override
	public Lieu getLieu(String codeIsee) {
		return lieuRepository.getLieu(codeIsee);
	}
	
	@Override
	public List<Lieu> getAllLieux() {
		return lieuRepository.findAll();
	}
	
	@Override
	public Lieu addLieu(Lieu lieu) {
		return lieuRepository.save(lieu);
	}
	
	
//	Monument
	
	
	@Override
	public void addMonument(Monument monument) {
		// TODO Auto-generated method stub
		
	}

	public List<Monument> getMonument(String codeM) {
	return appService.getMonument(codeM);
	}

	public List<Monument> getAllMonuments() {
		return appService.getAllMonuments();
	}

	@Override
	public void addMonumentToLieu(String codeM, String codeInsee) {
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

	
//	Celebrite
	@Override
	public Celebrite addCelebrite(Celebrite celebrite) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
