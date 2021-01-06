package com.masterips.javaeeproject.controllers;


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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sun.istack.NotNull;

import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.dao.CelebriteRepository;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.services.AppService;



@Controller
public class PagesController {

	@Autowired
	private AppService appService;
	
	@Autowired
	private MonumentRepository monumentRepository;
	
	@Autowired
	private CelebriteRepository celebriteRepository;


	private HttpSession colorSession;
	
	
    
	@GetMapping("profile")
	public String profile() {
		return "user/profile";
	}

// ---------------------------- Begin Departements   ----------------------------

    @GetMapping(value="departements/page/{pageNumber}")
	public String allDepartements(Model model, @PathVariable("pageNumber") int currentPage, @RequestParam(defaultValue="nomDep") String sortField, @RequestParam(defaultValue="asc") String sortDirection)   {
    	try {
        	Sort sort = Sort.by(sortField);
        	sort=  sortDirection.equals("asc") ? sort.ascending() : sort.descending();
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
            model.addAttribute("sortField", "nomDep");
    		model.addAttribute("sortDirection", "asc");	
    		String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
    		model.addAttribute("reverseSortDirection", reverseSortDirection);		


    		
    	}catch (Exception e) {
    		model.addAttribute("message",e);
    	}
		return "departement/all";
    }
    
   
  //Departements card
    
      @GetMapping("departements/card/page/{pageNumber}")
      public String allDepartementsCard(Model model, @PathVariable("pageNumber") int currentPage) {
          try {
        	  Sort sort = Sort.by("nomDep").descending();
              Page<Departement> page = appService.getAllDepartements(currentPage, 18, sort);
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
              model.addAttribute("message",e);
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
              model.addAttribute("message",e);
          }
          return "departement/data-table";
      }

      

      	  // Form for add a new departement

	  @GetMapping("departements/add")
	  String add(Model model, @ModelAttribute("newDepartement") Departement newDepartement) {
	
          color(model);
          model.addAttribute("newDepartement",new Departement());

	    return "departement/add";
	    
	  }

	  
	    @PostMapping("departements")
	    public String saveEmp(@Valid @NotNull @ModelAttribute("newDepartement") Departement newDepartement, BindingResult result, RedirectAttributes ra){
	        appService.addDepartement(newDepartement);
	        if(result.hasErrors()){
	            return "departement/add";
	        }
	        ra.addFlashAttribute("newDepartement", newDepartement);
	        return "redirect:/departements/card/page/1";
	    }

        @DeleteMapping("departements/{id}")
        public ResponseEntity<String> deleteDepartementById(@PathVariable String numDep) {
    
            var isRemoved = appService.deleteDepartementById(numDep);
    
            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    
            return new ResponseEntity<>(numDep, HttpStatus.OK);
        }


// ---------------------------- End Departements   ----------------------------




// ---------------------------- Begin Lieux   ----------------------------

    //Lieux
      
    @GetMapping("lieux/page/{pageNumber}")
	public String allLieux(Model model, @PathVariable("pageNumber") int currentPage, @RequestParam(defaultValue="codeInsee") String sortField, @RequestParam(defaultValue="asc") String sortDirection)   {
    	try {
        	Sort sort = Sort.by(sortField);
        	sort=  sortDirection.equals("asc") ? sort.ascending() : sort.descending();
    		Page<Lieu> page = appService.getAllLieux(currentPage, 27, sort);
            List<Lieu> lieux = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();

            color(model);
            
            model.addAttribute("lieux", lieux);
            model.addAttribute("totalItems", totalItems);
    		model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("sortField", "codeInsee");
    		model.addAttribute("sortDirection", "asc");	
    		String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
    		model.addAttribute("reverseSortDirection", reverseSortDirection);		

    		
    		
    		
    	}catch (Exception e) {
    		model.addAttribute("exception",e);
    	}
		return "lieu/all";
	}
    
    
  // Lieux card
    
    @GetMapping("lieux/card/page/{pageNumber}")
    public String allLieuxCard(Model model, @PathVariable("pageNumber") int currentPage)   {
    	try {
        	Sort sort = Sort.by("codeInsee").ascending();
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
    
    

          	  // Form for add a new Lieu

	  @GetMapping("lieux/add")
	  String add(Model model, @ModelAttribute("newLieu") Lieu newLieu) {
	
          color(model);
          model.addAttribute("newLieu",new Lieu());

	    return "lieu/add";
	    
	  }

	  
	    @PostMapping("lieux")
	    public String saveEmp(@Valid @NotNull @ModelAttribute("newLieu") Lieu newLieu, BindingResult result, RedirectAttributes ra){
	        appService.addLieu(newLieu);
	        if(result.hasErrors()){
	            return "lieu/add";
	        }
	        ra.addFlashAttribute("newLieu", newLieu);
	        return "redirect:/lieux/card/page/1";
        }
        
        

        @DeleteMapping("lieux/{id}")
        public ResponseEntity<String> deleteLieuById(@PathVariable String codeInsee) {
    
            var isRemoved = appService.deleteLieuById(codeInsee);
    
            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    
            return new ResponseEntity<>(codeInsee, HttpStatus.OK);
        }


        // End of Lieu

// ---------------------------- End Lieux   ----------------------------



// ---------------------------- Begin Monument   ----------------------------


    //Monument
    
    @GetMapping("monuments/page/{pageNumber}")
	public String allMonuments(Model model, @PathVariable("pageNumber") int currentPage, @RequestParam(defaultValue="codeM") String sortField, @RequestParam(defaultValue="asc") String sortDirection)   {
    	try {
        	Sort sort = Sort.by(sortField);
        	sort=  sortDirection.equals("asc") ? sort.ascending() : sort.descending();
            Page<Monument> page = appService.getAllMonuments(currentPage, 17, sort);
            List<Monument> monuments = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();

            color(model);
            
            model.addAttribute("currentPage", currentPage);
    		model.addAttribute("monuments", monuments);
    		model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("sortField", "codeM");
    		model.addAttribute("sortDirection", "asc");	
    		String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
    		model.addAttribute("reverseSortDirection", reverseSortDirection);		

            
    	}catch (Exception e) {
    		model.addAttribute("message",e);
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
    		model.addAttribute("message",e);
    	}
		return "monument/card";
	}
    

    	  // Form for add a new monument

	  @GetMapping("monuments/add")
	  String add(Model model, @ModelAttribute("newMonument") Monument newMonument) {
	
          color(model);
          model.addAttribute("newMonument",new Monument());

	    return "monument/add";
	    
	  }

	  
	    @PostMapping("monuments")
	    public String saveEmp(@Valid @NotNull @ModelAttribute("newMonument") Monument newMonument, BindingResult result, RedirectAttributes ra){
	        appService.addMonument(newMonument);
	        if(result.hasErrors()){
	            return "monument/add";
	        }
	        ra.addFlashAttribute("newMonument", newMonument);
	        return "redirect:/monuments/card/page/1";
	    }

        @DeleteMapping("monuments/{id}")
        public ResponseEntity<String> deleteMonumentById(Model model, @PathVariable String codeM) {
        	try {
        		 appService.deleteMonumentById(codeM);
        		 return new ResponseEntity<>(codeM, HttpStatus.OK);
        	} catch (Exception e) {
        			model.addAttribute("message", e);
        			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			}
           
    
    
            
        }



        // End of Monument

    // ---------------------------- End Monument   ----------------------------

    
    
    

    // ---------------------------- Begin Celebrite   ----------------------------

    // Celebrities
    @GetMapping("celebrities/page/{pageNumber}")
	public String allCelebrities(Model model, @PathVariable("pageNumber") int currentPage, @RequestParam(defaultValue="numCelebrite") String sortField, @RequestParam(defaultValue="asc") String sortDirection)   {
    	try {
        	Sort sort = Sort.by(sortField);
        	sort=  sortDirection.equals("asc") ? sort.ascending() : sort.descending();

    		Page<Celebrite> page = appService.getAllCelebrities(currentPage, 30, sort);
            List<Celebrite> celebrities = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();
            
            color(model);

            model.addAttribute("celebrities", celebrities);
            model.addAttribute("totalItems", totalItems);
    		model.addAttribute("currentPage", currentPage);
    		model.addAttribute("totalPages", totalPages);
    		model.addAttribute("sortField", "numCelebrite");
    		model.addAttribute("sortDirection", "asc");	
    		String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
    		model.addAttribute("reverseSortDirection", reverseSortDirection);		

    		
    		
    		
    	}catch (Exception e) {
    		model.addAttribute("message",e);
    	}
		return "celebrite/all";
	}


      // Celebrities card
      @GetMapping("celebrities/card/page/{pageNumber}")
      public String allCelebritiesCard(Model model, @PathVariable("pageNumber") int currentPage) {
          try {
          	  Sort sort = Sort.by("nom").ascending();

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
              model.addAttribute("message",e);
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
	  String add(Model model, Celebrite newCelebrite) {
		  try {
			  color(model);
			  celebriteRepository.save(newCelebrite);
              model.addAttribute("newCelebrite",newCelebrite);
              model.addAttribute("id", newCelebrite.getNumCelebrite());
			  
	    	}catch (Exception e) {
	    		model.addAttribute("message",e);
	    	}


	    return "celebrite/add";
	    
	  }

	  
	    @PostMapping("celebrities")
	    public String saveCelebrite(Model model, @Valid @NotNull @ModelAttribute("newCelebrite") Celebrite newCelebrite, BindingResult result){
	        try {
                    appService.addCelebrite(newCelebrite);
                    return "redirect:celebrities/page/1";
                
            } catch (Exception e) {
                model.addAttribute("message",e);
                    return "celebrite/add";

            }
	    }
		
		  @GetMapping("celebrities/modify/{id}")
		  public String modifyCelebrite(Model model, @PathVariable(value="id") long id) {
			  try {
				  color(model);
				  Celebrite newCelebrite = appService.getCelebriteById(id);
				  model.addAttribute("newCelebrite",newCelebrite);
				  model.addAttribute("id",id);
				  model.addAttribute("newCelebriteId",newCelebrite.getNumCelebrite());
				  
				  
		    	}catch (Exception e) {
		    		model.addAttribute("message",e);
		    	}

		    return "celebrite/modify";
		    
		  }
		  
		  @PostMapping("celebrities/modify")
		  public String replaceCelebrite(Model model, @ModelAttribute("newCelebrite") Celebrite newCelebrite, @ModelAttribute("id") String id) {		// @RequestBody Celebrite newCelebrite
			  
			  try {
				  Celebrite updateCelebrite = appService.getCelebriteById(newCelebrite.getNumCelebrite());
				  updateCelebrite.setNom(newCelebrite.getNom());
				  updateCelebrite.setPrenom(newCelebrite.getPrenom());
				  updateCelebrite.setNumCelebrite(newCelebrite.getNumCelebrite());
				  updateCelebrite.setNationalite(newCelebrite.getNationalite());
				  updateCelebrite.setImage(newCelebrite.getImage());
				  updateCelebrite.setUrl(newCelebrite.getUrl());
				  updateCelebrite.setParent_url(newCelebrite.getParent_url());
				  
//				  celebriteRepository.updateCelebrite(newCelebrite.getNom(), newCelebrite.getPrenom(), newCelebrite.getNationalite(), 
//						  newCelebrite.getEpoque(), newCelebrite.getNumCelebrite());
				  celebriteRepository.save(updateCelebrite);
				  model.addAttribute("updateCelebrite", updateCelebrite);
				  model.addAttribute("idf", id);
				  model.addAttribute("newCelebrite_vodoudi", newCelebrite);
//				  celebriteRepository.saveAndFlush(updateCelebrite);
			} catch (Exception e) {
				
				model.addAttribute("message", e);
			}
                    
		        return "redirect:/celebrities/page/1";
		  }

		  		  
		  




    /**
     * Delete a celebrity based on Id
     * Throw 404 exception if not found
     * @param numCelebrite
     */

//        @DeleteMapping("celebrities/{id}")
        @GetMapping("celebrities/delete/{id}")

        public String deleteCelebriteById(Model model, @PathVariable long id, RedirectAttributes ra) {
    
            
            try {
            		appService.deleteCelebriteById(id);
            		model.addAttribute("message",id +"is successfully deleted");
			} catch (Exception e) {
				model.addAttribute("message",e);

				}
            return "redirect:/celebrities/page/1";        
            }
    
        
        
        
    
    // ---------------------------- End Celebrite   ----------------------------

}






