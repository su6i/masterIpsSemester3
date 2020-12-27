package com.masterips.javaeeproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

import java.util.Arrays;
import java.util.List;

// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@Controller
public class PagesController {
	
	@Autowired
	private AppService appService;
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	// @ResponseBody
	public String home(@RequestParam(defaultValue= "World") String name, ModelMap modelMap) {
		
		modelMap.put("name", name);
		return "navbar";
    }
    
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String profile() {
		return "user/profile";
	}

//Display all departements

    @RequestMapping(value="/departements", method=RequestMethod.GET)
	public String allDepartements(Model model) {
    	try {
    		List<Departement> departements = appService.getAllDepartements();
    		model.addAttribute("departements", departements);
    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "departement/all";
	}


    //Display all lieux
    @RequestMapping(value="/lieux", method=RequestMethod.GET)
	public String allLieux(Model model) {
    	try {
    		List<Lieu> lieux = appService.getAllLieux();
    		model.addAttribute("lieux", lieux);
    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "lieu/all";
	}
    


    //Display all monument
    @RequestMapping(value="/monuments", method=RequestMethod.GET)
	public String allMonuments(Model model) {
    	try {
    		List<Monument> monuments = appService.getAllMonuments();
    		model.addAttribute("monuments", monuments);
    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "monument/all";
	}
    
    
  //Display all monument
    @RequestMapping(value="/monumentsCard", method=RequestMethod.GET)
	public String allMonumentsCard(Model model) {
    	try {
    		List<Monument> monuments = appService.getAllMonuments();
    		model.addAttribute("monuments", monuments);
    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "monument/card";
	}
    
    


    //Display all celebrite
    @RequestMapping(value="/celebrities", method=RequestMethod.GET)
	public String allCelebrities(Model model) {
    	try {
    		List<Celebrite> celebrities = appService.getAllCelebrities();
    		model.addAttribute("celebrities", celebrities);
    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "celebrite/all";
	}






}
