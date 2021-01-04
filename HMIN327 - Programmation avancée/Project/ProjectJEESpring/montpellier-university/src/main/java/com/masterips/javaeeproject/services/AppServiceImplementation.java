package com.masterips.javaeeproject.services;

import java.util.Arrays;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;


import com.masterips.javaeeproject.dao.LieuRepository;
import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.dao.CelebriteRepository;
import com.masterips.javaeeproject.dao.DepartementRepository;

import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;


@Service
public class AppServiceImplementation implements AppService {
	
	
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private MonumentRepository monumentRepository;

	@Autowired
	private CelebriteRepository celebriteRepository;
	
	@Autowired
	private DepartementRepository departementRepository;
	
	
//	Department
	
	@Override
	public Departement getDepartement(String numDep) {
		
		 if(numDep==null || (!departementRepository.existsById(numDep))) throw new RuntimeException("Can't find entered department");
		 
		 return departementRepository.findById(numDep).get();
	}
	
	@Override
	public Page<Departement> getAllDepartements(int pageNumber, int items, Sort sort) {
		
		Pageable pageable = PageRequest.of(pageNumber - 1, items, sort);
		return departementRepository.findAll(pageable);
	}
	
	@Override
	public Departement addDepartement(Departement departement) {
		return departementRepository.save(departement);
	}
    
    @Override
	public List<Departement> findAll(){
		return departementRepository.findAll();
	}
    
	
	@Override
	public List<Departement> getByNameDepartementContaining(String nom) {
		// TODO Auto-generated method stub
		return departementRepository.getByNameDepartementContaining(nom);
	}

	@Override
	public boolean deleteDepartementById(String numDep) {
		// TODO Auto-generated method stub
		return departementRepository.deleteDepartementById(numDep);
	}

	
	
	




//	Lieu 


	@Override
	public Lieu getLieu(String codeIsee) {
		return lieuRepository.getLieu(codeIsee);
	}
	
	@Override
	public Page<Lieu> getAllLieux(int pageNumber, int items, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber - 1, items, sort);
		return lieuRepository.findAll(pageable);
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
	
	
	@Override
	public List<Lieu> getByNameLieuContaining(String nom) {
		// TODO Auto-generated method stub
		return lieuRepository.getByNameLieuContaining(nom);
	}

	@Override
	public boolean deleteLieuById(String codeIsee) {
		// TODO Auto-generated method stub
		return lieuRepository.deleteLieuById(codeIsee);
	}

	
	
	
	
	
	
	
	
    
    

//	Monument
	
    @Override
	public Monument getMonument(String codeM) {
	return monumentRepository.findById(codeM).get();
	}

//	public List<Monument> getAllMonuments() {
//		
//		return monumentRepository.findAll();
//	}
	

//	@Override
//	public Page<Monument> getAllMonuments(int pageNumber) {
//		Pageable pageable = PageRequest.of(pageNumber - 1, 17);
//		return monumentRepository.findAll(pageable);
//	}

    
	@Override
	public Page<Monument> getAllMonuments(int pageNumber, int items, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber - 1, items, sort);
		return monumentRepository.findAll(pageable);
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
	public List<Monument> getByNameMonumentContaining(String nom) {
		// TODO Auto-generated method stub
		return monumentRepository.getByNameMonumentContaining(nom);
	}

	
	@Override
	public boolean deleteMonumentById(String codeM) {
		// TODO Auto-generated method stub
		return monumentRepository.deleteMonumentById(codeM);
	}

	
//	@Override
//	public List<Monument> getListMonumentsByDep(String nomDep) {
//		// TODO Auto-generated method stub
//		return monumentRepository.getListMonumentsByDep(nomDep);
//	}
//
//	@Override
//	public List<Monument> getListMonumentsByLieu(String nomCom) {
//		// TODO Auto-generated method stub
//		return monumentRepository.getListMonumentsByLieu(nomCom);
//	}

	
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
	public List<Celebrite> getCelebriteByName(String prenom) {
		return celebriteRepository.getCelebriteByName(prenom);
	}

    @Override
	public List<Celebrite> getCelebriteByFamily(String nom) {
		return celebriteRepository.getCelebriteByFamily(nom);
	}

    @Override
	public Page<Celebrite> getAllCelebrities(int pageNumber, int items, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber - 1, items, sort);
		return celebriteRepository.findAll(pageable);
	}

	
	@Override
	public Celebrite addCelebrite(Celebrite celebrite) {
		return celebriteRepository.save(celebrite);
	}

    @Override
	public Celebrite getCelebriteById(String numCelebrite) {
		return celebriteRepository.getCelebriteById(numCelebrite);
	}

    @Override
    public List<Celebrite> getByNameCelebriteContaining(String nom) {
    	return celebriteRepository.getByNameContaining(nom);
    }
    
	

	@Override
	public boolean deleteCelebriteById(String numCelebrite) {
		return celebriteRepository.deleteCelebriteById(numCelebrite);
	}








	
	


	



}
