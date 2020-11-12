package com.masterips.javaeeproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PagesController {
	
	@GetMapping("/")
	// @ResponseBody
	public String home(@RequestParam(defaultValue= "World") String name, ModelMap modelMap) {
		
		modelMap.put("name", name);
		return "pages/home";
	}

}
