package com.masterips.javaeeproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masterips.javaeeproject.dao.DepartementRepository;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.service.AppService;

import java.util.List;

// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class PagesController {
	
	@Autowired
	private AppService appService;
	
//	Department routes

	@RequestMapping(value="/", method=RequestMethod.GET)
	// @ResponseBody
	public String home(@RequestParam(defaultValue= "World") String name, ModelMap modelMap) {
		
		modelMap.put("name", name);
		return "home";
	}
	
	
	@RequestMapping(value="/departement", method=RequestMethod.POST)
	public Departement addDepartement(@RequestBody Departement departement) {
		return appService.addDepartement(departement);
	}

	@RequestMapping(value="/departement{dep}", method=RequestMethod.GET)
	public Departement getDepartement(String dep) {
		return appService.getDepartement(dep);
	}	
	
	@RequestMapping(value="/departements", method=RequestMethod.GET)
	public List<Departement> getAllDepartements() {
		return appService.getAllDepartements();
	}
	
//	@RequestMapping("/department")
//	public String getDepartmnet(@RequestParam(defaultValue= "34") String dep, ModelMap modelMap) {
//		
//		try {
//			Departement d = appService.getDepartement(dep);
//			modelMap.put("dep", dep);
//			modelMap.addAttribute("departement", d);
//		} catch (Exception e) {
//			modelMap.addAttribute("execption", e);
//		}
//		
//		return "department";
//		
//	}
	
	
	
//	Lieu routes	
	
	@RequestMapping(value="/lieu", method=RequestMethod.POST)
	public Lieu addLieu(@RequestBody Lieu lieu) {
		return appService.addLieu(lieu);
	}
	
	@RequestMapping(value="/lieu{codeInsee}", method=RequestMethod.GET)
	public Lieu getLieu(String codeIsee) {
		return appService.getLieu(codeIsee);
	}
	

	@RequestMapping(value="/lieux", method=RequestMethod.GET)
	public List<Lieu> getAllLieus() {
		return appService.getAllLieux();
	}

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

	public Celebrite addCelebrite(Celebrite celebrite) {
		return appService.addCelebrite(celebrite);
	}

	
	

}
