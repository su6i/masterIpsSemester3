package com.masterips.javaeeproject.controller;


import org.springframework.stereotype.Controller;
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


    @RequestMapping(value="/all", method=RequestMethod.GET)
	public String alldepartement() {
		return "departement/allDepartement";
	}
    
    @RequestMapping(value="/menu", method=RequestMethod.GET)
	public String menu() {
		return "navbar";
	}
	





}
