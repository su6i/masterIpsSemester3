package com.masterips.javaeeproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.service.AppService;

// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class PagesController {
	
	@GetMapping("/")
	// @ResponseBody
	public String home(@RequestParam(defaultValue= "World") String name, ModelMap modelMap) {
		
		modelMap.put("name", name);
		return "home";
	}
	
	@Autowired
	private AppService appService;
	@RequestMapping("/department")
	public String getDepartmnet(@RequestParam(defaultValue= "34") String dep, ModelMap modelMap) {
		
		try {
			Departement d = appService.getDepartement(dep);
			modelMap.put("dep", dep);
			modelMap.addAttribute("departement", d);
		} catch (Exception e) {
			modelMap.addAttribute("execption", e);
		}
		
		return "department";
		
	}
	

}
