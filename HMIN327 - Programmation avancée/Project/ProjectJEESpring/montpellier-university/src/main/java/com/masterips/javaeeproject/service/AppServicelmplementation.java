package com.masterips.javaeeproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
	public Departement getDepartement(String numDep) {
		
		 if(numDep==null || (!departementRepository.existsById(numDep))) throw new RuntimeException("Can't find entered department");
		 
		 return departementRepository.findById(numDep).get();
	}
	
	@Override
	public List<Departement> getAllDepartements() {
		 return departementRepository.findAll();	 
	}
	
	@Override
	public Departement addDepartement(Departement departement) {
		return departementRepository.save(departement);
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
	
//	public Page<Lieu> getAllLieux(int page, int size) {
//		return lieuRepository.getAllLieuxPage(new PageRequest(page, size));
//	}
//
//	@Override
//	public Page<Lieu> getAllLieuxPage(PageRequest of) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	@Override
	public Lieu addLieu(Lieu lieu) {
		return lieuRepository.save(lieu);
	}
	
	
//	Monument
	
	
	public Monument getMonument(String codeM) {
	return monumentRepository.findById(codeM).get();
	}

	public List<Monument> getAllMonuments() {
		return monumentRepository.findAll();
	}


	@Override
	public void addMonument(Monument monument) {
		// TODO Auto-generated method stub
		
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

	public List<Celebrite> getCelebriteByName(String prenom) {
		return celebriteRepository.getCelebriteByName(prenom);
	}

	public List<Celebrite> getCelebriteByFamily(String nom) {
		return celebriteRepository.getCelebriteByFamily(nom);
	}

	public List<Celebrite> getAllCelebrities() {
		return celebriteRepository.findAll();
	}

	
	@Override
	public Celebrite addCelebrite(Celebrite celebrite) {
		// TODO Auto-generated method stub
		return null;
	}

	



}
