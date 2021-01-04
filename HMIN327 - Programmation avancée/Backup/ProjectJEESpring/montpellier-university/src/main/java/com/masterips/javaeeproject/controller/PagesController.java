package com.masterips.javaeeproject.controller;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.service.AppService;
import com.sun.istack.NotNull;


@Controller("html")
public class PagesController {

	@Autowired
	private AppService appService;
	
	@Autowired
	private MonumentRepository monumentRepository;

	private HttpSession colorSession;

	@GetMapping
	public String home(@RequestParam(defaultValue= "World") String name, ModelMap modelMap) {
		
		modelMap.put("name", name);
		return "navbar";
    }
    
	@GetMapping("profile")
	public String profile() {
		return "user/profile";
	}

// Departements

    @GetMapping(value="departements/page/{pageNumber}")
	public String allDepartements(Model model, @PathVariable("pageNumber") int currentPage) {
    	try {
    		Sort sort = Sort.by("nomDep").descending();
    		Page<Departement> page = appService.getAllDepartements(currentPage, 28, sort);
    		List<Departement> departements = page.getContent();
    		model.addAttribute("departements", departements);
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();

            color(model);
            
            model.addAttribute("departements", departements);
            model.addAttribute("totalItems", totalItems);
    		model.addAttribute("currentPage", currentPage);
    		model.addAttribute("totalPages", totalPages);

    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "departement/all";
    }
    
   
  //Departements card
    
      @GetMapping("departements/card/page/{pageNumber}")
      public String allDepartementsCard(Model model, @PathVariable("pageNumber") int currentPage) {
          try {
        	  Sort sort = Sort.by("nomDep").descending();
              Page<Departement> page = appService.getAllDepartements(currentPage, 24, sort);
              List<Departement> departements = page.getContent();
              long totalItems = page.getTotalElements();
              int totalPages = page.getTotalPages();
  
              color(model);
              
              model.addAttribute("currentPage", currentPage);
              model.addAttribute("departements", departements);
              model.addAttribute("totalItems", totalItems);
              model.addAttribute("totalPages", totalPages);

              int n = (int) (Math.random() * 8 + 1);
              List<String> border = Arrays.asList("border-primary","border-secondary","border-success","border-danger","border-warning","border-info","border-light","border-dark");
              List<String> text   = Arrays.asList("text-primary","text-secondary","text-success","text-danger","text-warning","text-info","text-light","text-dark");
              model.addAttribute("border", border.get(n));
              model.addAttribute("text", text.get(n));
              model.addAttribute("n", n);
              
          }catch (Exception e) {
              model.addAttribute("exception",e);
          }
          return "departement/card";
      }

      






      @GetMapping("departements/table")
      public String table(Model model) {
          try {
            //   List<Departement> departements = appService.findAll();
  
              color(model);
              
            //   model.addAttribute("departements", departements);
  
              
          }catch (Exception e) {
              model.addAttribute("exception",e);
          }
          return "departement/table";
      }



      @GetMapping("departements/data-table")
      public String datatable(Model model) {
          try {
               List<Departement> departements = appService.findAll();
  
              color(model);
              
               model.addAttribute("departements", departements);
  
              
          }catch (Exception e) {
              model.addAttribute("exception",e);
          }
          return "departement/data-table";
      }

      






    //Lieux
      
    @GetMapping("lieux/page/{pageNumber}")
	public String allLieux(Model model, @PathVariable("pageNumber") int currentPage) {
    	try {
    		Sort sort = Sort.by("codeInsee").descending();
    		Page<Lieu> page = appService.getAllLieux(currentPage, 27, sort);
            List<Lieu> lieux = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();

            color(model);
            
            model.addAttribute("lieux", lieux);
            model.addAttribute("totalItems", totalItems);
    		model.addAttribute("currentPage", currentPage);
    		model.addAttribute("totalPages", totalPages);
    		
    		
    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "lieu/all";
	}
    
    
  // Lieux card
    
    @GetMapping("lieux/card/page/{pageNumber}")
    public String allLieuxCard(Model model, @PathVariable("pageNumber") int currentPage) {
        try {
    		Sort sort = Sort.by("nomCom").descending();
            Page<Lieu> page = appService.getAllLieux(currentPage, 6, sort);
            List<Lieu> lieux = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();


            color(model);
            
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("lieux", lieux);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);

            
        }catch (Exception e) {
            model.addAttribute("exception",e);
        }
        return "lieu/card";
    }
    
    


    //Monument
    
    @GetMapping("monuments/page/{pageNumber}")
	public String allMonuments(Model model, @PathVariable("pageNumber") int currentPage) {
    	try {
    		Sort sort = Sort.by("codeM").descending();
            Page<Monument> page = appService.getAllMonuments(currentPage, 17, sort);
            List<Monument> monuments = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();

            color(model);
            
            model.addAttribute("currentPage", currentPage);
    		model.addAttribute("monuments", monuments);
    		model.addAttribute("totalItems", totalItems);
    		model.addAttribute("totalPages", totalPages);
            
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "monument/all";
	}

//    @RequestParam(required = false, defaultValue = "someValue").
    
  // Monuments card
    @GetMapping("monuments/card/page/{pageNumber}")
	public String allMonumentsCard(Model model, @PathVariable("pageNumber") int currentPage) {
    	try {
    		Sort sort = Sort.by("codeM").descending();
            Page<Monument> page = appService.getAllMonuments(currentPage, 6, sort);
            List<Monument> monuments = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();

            color(model);
            
    		model.addAttribute("currentPage", currentPage);
    		model.addAttribute("monuments", monuments);
    		model.addAttribute("totalItems", totalItems);
    		model.addAttribute("totalPages", totalPages);

    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "monument/card";
	}
    
    
    
    
    


    // Celebrities
    @GetMapping("celebrities/page/{pageNumber}")
	public String allCelebrities(Model model, @PathVariable("pageNumber") int currentPage)   {
    	try {
        	Sort sort = Sort.by("nom").ascending();

    		Page<Celebrite> page = appService.getAllCelebrities(currentPage, 30, sort);
            List<Celebrite> celebrities = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();
            
            color(model);

            model.addAttribute("celebrities", celebrities);
            model.addAttribute("totalItems", totalItems);
    		model.addAttribute("currentPage", currentPage);
    		model.addAttribute("totalPages", totalPages);
    		
    		
    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "celebrite/all";
	}


      // Celebrities card
      @GetMapping("celebrities/card/page/{pageNumber}")
      public String allCelebritiesCard(Model model, @Param("sortField") String sortField, @Param("sortDirection")  String sortDirection, @PathVariable("pageNumber") int currentPage) {
          try {
          	  Sort sort = Sort.by(sortField);
        	  sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();

              Page<Celebrite> page = appService.getAllCelebrities(currentPage, 6, sort);
              List<Celebrite> celebrities = page.getContent();
              long totalItems = page.getTotalElements();
              int totalPages = page.getTotalPages();
  
              color(model);
              
              model.addAttribute("currentPage", currentPage);
              model.addAttribute("celebrities", celebrities);
              model.addAttribute("totalItems", totalItems);
              model.addAttribute("totalPages", totalPages);
  
              
          }catch (Exception e) {
              model.addAttribute("exception",e);
          }
          return "celebrite/card";
      }
  

      
      // A method for changing the color of cards 
  	  public void color(Model model) {
  	    int n = (int) (Math.random() * 7 + 1);
  	    List<String> border = Arrays.asList("border-primary","border-secondary","border-success","border-danger","border-warning","border-info","border-light","border-dark");
  	    List<String> text   = Arrays.asList("text-primary","text-secondary","text-success","text-danger","text-warning","text-info","text-light","text-dark");
  	    model.addAttribute("border", border.get(n));
  	    model.addAttribute("text", text.get(n));
  	    model.addAttribute("n", n);

  	}

      
	  // Form for add a new celebrity

	  @GetMapping("celebrities/add")
	  String add(Model model, @ModelAttribute("newCelebrite") Celebrite newCelebrite) {
	
		  color(model);
	    return "celebrite/add";
	    
	  }

	  
	    @PostMapping("celebrities")
	    public String saveEmp(@Valid @NotNull @ModelAttribute("newCelebrite") Celebrite newCelebrite, BindingResult result, RedirectAttributes ra){
	        appService.addCelebrite(newCelebrite);
	        if(result.hasErrors()){
	            return "celebrite/add";
	        }
	        ra.addFlashAttribute("newCelebrite", newCelebrite);
	        return "redirect:/celebrities/card/page/1";
	    }

        @DeleteMapping("celebrities/{id}")
        public ResponseEntity<String> deletePost(@PathVariable String numCelebrite) {
    
            var isRemoved = appService.deleteCelebriteById(numCelebrite);
    
            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    
            return new ResponseEntity<>(numCelebrite, HttpStatus.OK);
        }
    

}
