package com.masterips.javaeeproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.service.AppService;

@RestController
public class RestRoutes {
	
	@Autowired
	private AppService appService;

	
	
//	Department routes

	@RequestMapping(value="/departement", method=RequestMethod.POST)
	public Departement addDepartement(@RequestBody Departement departement) {
		return appService.addDepartement(departement);
	}

	@RequestMapping(value="/departement/{dep}", method=RequestMethod.GET)
	public Departement getDepartement(@PathVariable String dep) {
		return appService.getDepartement(dep);
	}	
	
	@RequestMapping(value="/departementsJson", method=RequestMethod.GET)
	public List<Departement> getAllDepartements() {
		return appService.getAllDepartements();
	}
	
	
	
//	Lieu routes	
	
	@RequestMapping(value="/lieu", method=RequestMethod.POST)
	public Lieu addLieu(@RequestBody Lieu lieu) {
		return appService.addLieu(lieu);
	}
	
	@RequestMapping(value="/lieu/{codeInsee}", method=RequestMethod.GET)
	public Lieu getLieu(@PathVariable String codeInsee) {
		return appService.getLieu(codeInsee);
	}
	
	
	@RequestMapping(value="/lieuxJSON", method=RequestMethod.GET)
	public List<Lieu> getAllLieux() {
		return appService.getAllLieux();
	}

	
//	@RequestMapping(value="/lieuxPage", method=RequestMethod.GET)
//	public Page<Lieu> getAllLieux(@PathVariable(value = "0") int page, @PathVariable(value = "10") int size) {
//		return appService.getAllLieuxPage(new PageRequest(page, size));
//	}


//Monument

	public void addMonument(Monument monument) {
		appService.addMonument(monument);
	}

	public void addMonumentToLieu(String codeM, String codeInsee) {
		appService.addMonumentToLieu(codeM, codeInsee);
	}

	public float getDistanceBetweenMonuments(String nomMonA, String nomMonB) {
		return appService.getDistanceBetweenMonuments(nomMonA, nomMonB);
	}

	public List<Monument> getListMonumentsByDep(String nomDep) {
		return appService.getListMonumentsByDep(nomDep);
	}

	public List<Monument> getListMonumentsByLieu(String nomCom) {
		return appService.getListMonumentsByLieu(nomCom);
	}


	@RequestMapping(value="/monument/{codeM}", method=RequestMethod.GET)
	public Monument getMonument(@PathVariable String codeM) {
		return appService.getMonument(codeM);
	}


	@RequestMapping(value="/monumentsJSON", method=RequestMethod.GET)
	public List<Monument> getAllMonuments(int currentPage) {
		Page<Monument> monuments = appService.findAll(currentPage);
		List<Monument> listMonuments = monuments.getContent();
		return listMonuments;
    }
    
//    //Display all monument page by page
//	@RequestMapping(value="/monuments/{page}")
//    Page<Monument> pageMonument(Pageable pageable){
//        return appService.findAll(pageable);
//    }

//    //Display all monument sorted
//	@RequestMapping(value="/monuments/sorted")
//    Page<Monument> monumentSorted(Pageable pageable){
//        return appService.findAllSorted();
//    }
    

	
//	Celebrite	

	public Celebrite addCelebrite(Celebrite celebrite) {
		return appService.addCelebrite(celebrite);
	}
	
	
	@RequestMapping(value="/celebrite/prenom/{prenom}", method=RequestMethod.GET)
	public List<Celebrite> getCelebriteByName(@PathVariable String prenom) {
		return appService.getCelebriteByName(prenom);
	}

	@RequestMapping(value="/celebrite/nom/{nom}", method=RequestMethod.GET)
	public List<Celebrite> getCelebriteByFamily(@PathVariable String nom) {
		return appService.getCelebriteByFamily(nom);
	}
	
	@RequestMapping(value="/celebritiesJSON", method=RequestMethod.GET)
	public List<Celebrite> getAllCelebrities() {
		return appService.getAllCelebrities();
	}


}
