package com.masterips.javaeeproject.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.service.AppService;
import com.masterips.javaeeproject.service.AppServiceImplementation;
import com.sun.istack.NotNull;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;

@RestController("rest")
@RequestMapping("json")
public class RestRoutes {
	
	@Autowired
	private AppService appService;

	
	
	
//	Lieu routes	
	
    @PostMapping("lieu")
	public Lieu addLieu(@Valid @NotNull @RequestBody Lieu lieu) {
		return appService.addLieu(lieu);
	}
	
	@GetMapping("lieu/{codeInsee}")
	public Lieu getLieu(@PathVariable String codeInsee) {
		return appService.getLieu(codeInsee);
	}
	

    @GetMapping("lieux/page/{pageNumber}")
	public List<Lieu> getAllLieux(Model model, @PathVariable("pageNumber") int currentPage) {
		Sort sort = Sort.by("codeInsee").descending();

		Page<Lieu> page = appService.getAllLieux(currentPage, 9, sort);
		List<Lieu> lieux = page.getContent();
		
        model.addAttribute("lieux", lieux);

		return lieux;
	}








	
//	@RequestMapping("/lieuxPage", method=RequestMethod.GET)
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


	@GetMapping("monument/{codeM}")
	public Monument getMonument(@PathVariable String codeM) {
		return appService.getMonument(codeM);
	}

	
	@GetMapping("monuments/page/{pageNumber}")
	public List<Monument> getAllMonuments(Model model, @PathVariable("pageNumber") int currentPage) {
		Sort sort = Sort.by("codeM").descending();

		Page<Monument> page = appService.getAllMonuments(currentPage, 4, sort);
		List<Monument> monuments = page.getContent();
		
        model.addAttribute("monuments", monuments);

		return monuments;
	}

	
	
    
//    //Display all monument page by page
//	@RequestMapping("/monuments/{page}")
//    Page<Monument> pageMonument(Pageable pageable){
//        return appService.findAll(pageable);
//    }

//    //Display all monument sorted
//	@RequestMapping("/monuments/sorted")
//    Page<Monument> monumentSorted(Pageable pageable){
//        return appService.findAllSorted();
//    }
    

	
	



    
    
    


}
