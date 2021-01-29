package com.masterips.javaeeproject.controllers;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
//	@Autowired
//    private ModelMapper modelMapper;


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
    		Page<Departement> page = appService.getAllDepartements(currentPage, 15, sort);
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
              Page<Departement> page = appService.getAllDepartements(currentPage, 6, sort);
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
              model.addAttribute("message",e);
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

      













//
//
//
//      	  //departement add form
//
//	  @GetMapping("departements/add")
//	  String add(Model model, @ModelAttribute("newDepartement") Departement newDepartement) {
//	
//          color(model);
//          model.addAttribute("newDepartement",new Departement());
//
//	    return "departement/add";
//	    
//	  }
//
//	  	// new departement
////	    @ResponseStatus(HttpStatus.CREATED)
//	    @PostMapping("departements")
//	    public String saveDep(Model model, @Valid @NotNull @ModelAttribute("newDepartement") Departement newDepartement, BindingResult result, RedirectAttributes ra){
//	    	try {
//	    		
//	    		System.out.println("\n\n\n newDepartement: "+ newDepartement + "\n\n\nlieu: " + newDepartement.getLieu().getCodeInsee());
//	    		appService.addDepartement(newDepartement);
//	    		model.addAttribute("newDepartement", newDepartement);
//	    		model.addAttribute("message","Departement" +newDepartement.getNumDep() +" added successfully");
//				
//			} catch (Exception e) {
//				model.addAttribute("message",e);
//					return "departement/add";
//			
//	    }
//	    	return "redirect:/departements/page/1";       
//	    }
//	    
//        
//        
//
//












//  Departement New Form
//  Departement update form
//  Departement Details Form
	  @GetMapping(value = {"departements/{mode}", "departements/{mode}/{id}"})
	  public String monumentForm(Model model, @ModelAttribute("sampleEntity") Departement sampleEntity, @PathVariable(value="id") Optional<String> id, @PathVariable(value="mode") Optional<String> mode) {
		  color(model);
	
		  if(id.isPresent() && mode.isPresent()) {
			  Departement existedSampleEntity = appService.getDepartement(id.get());
			  model.addAttribute("sampleEntity",existedSampleEntity);
		  }
		  
		  if(id.isEmpty()) {
			  model.addAttribute("sampleEntity",new Departement());
			  model.addAttribute("mode","add");
		  }

	    return "departement/update";
	    
	  }


//	Departement New
    @PostMapping("departements")
    public String saveDepartement(Model model, @Valid @NotNull @ModelAttribute("sampleEntity") Departement sampleEntity, BindingResult result, RedirectAttributes ra){
        
      Lieu lieu = sampleEntity.setLieu(appService.getLieu(sampleEntity.getLieu().getCodeInsee()));

  	  if(lieu != null) sampleEntity.setLieu(lieu);
  	  else sampleEntity.setLieu(new Lieu(sampleEntity.getLieu().getCodeInsee()));
		  appService.addDepartement(sampleEntity);
		  if(result.hasErrors()){
			  ra.addAttribute("message", result.getAllErrors());
		      return "departement/update";
		  }
		  ra.addFlashAttribute("sampleEntity", sampleEntity);
          ra.addAttribute("message", sampleEntity.getNumDep() + " updated successfully");

		  return "redirect:/departements/page/1";
		}























        
//		Departement delete
        @GetMapping("departements/delete/{id}")
        public String deleteDepartementById(Model model, @PathVariable String id) {
            try {
        		appService.deleteDepartementById(id);
        		model.addAttribute("message",id +" is successfully deleted");
			} catch (Exception e) {
				model.addAttribute("message",e);
				}
//            return "redirect:/departements/page/1";
            return "history.go(-2)";
            }



// ---------------------------- End Departements   ----------------------------




// ---------------------------- Begin Lieux   ----------------------------

//  Lieux all table pagination
    @GetMapping("lieux/page/{pageNumber}")
	public String allLieux(Model model, @PathVariable("pageNumber") int currentPage, @RequestParam(defaultValue="codeInsee") String sortField, @RequestParam(defaultValue="asc") String sortDirection)   {
    	try {
        	Sort sort = Sort.by(sortField);
        	sort=  sortDirection.equals("asc") ? sort.ascending() : sort.descending();
    		Page<Lieu> page = appService.getAllLieux(currentPage, 12, sort);
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
    		model.addAttribute("message",e);
    	}
		return "lieu/all";
	}
    
    
//  Lieux card pagination
    @GetMapping("lieux/card/page/{pageNumber}")
    public String allLieuxCard(Model model, @PathVariable("pageNumber") int currentPage)   {
    	try {
        	Sort sort = Sort.by("codeInsee").ascending();
            Page<Lieu> page = appService.getAllLieux(currentPage, 9, sort);
            List<Lieu> lieux = page.getContent();
            long totalItems = page.getTotalElements();
            int totalPages = page.getTotalPages();


            color(model);
            
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("lieux", lieux);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);


            
        }catch (Exception e) {
            model.addAttribute("message",e);
        }
        return "lieu/card";
    }
    
    
    
    
    
    
//    example
//    @RequestMapping(value = {"/article", "/article/{id}"}")
//    public Article getArticle(@PathVariable Optional<Integer> optionalArticleId) {
//        if (optionalArticleId.isPresent()) {
//            Integer articleId = optionalArticleId.get();
//            //...
//        } else {
//            //...
//        }
//    }
    
    
//    Optional<String> opt = Optional.of("baeldung");
//    opt.ifPresent(name -> System.out.println(name.length()));
//    String name = Optional.ofNullable(nullName).orElse("john");
//    String name = Optional.ofNullable(nullName).orElseGet(() -> "john");
//    String name = Optional.ofNullable(nullName).orElseThrow(
//    	      IllegalArgumentException::new);
//    	}

//    Integer year = 2016;
//    Optional<Integer> yearOptional = Optional.of(year);
//    boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
//    assertTrue(is2016);
//    boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
//    assertFalse(is2017);


    
    

//    Lieu New Form
//    Lieux update form
//    Lieu Details Form
	  @GetMapping(value = {"lieux/{mode}", "lieux/{mode}/{id}"})
	  String add(Model model, @ModelAttribute("sampleEntity") Lieu sampleEntity, @PathVariable(value="id") Optional<String> id, @PathVariable(value="mode") String mode) {
		  color(model);
	
		  try {
			  if(id.isPresent()) {
				  model.addAttribute("id", id.get());
				  Lieu existedSampleEntity = appService.getLieu(id.get());
				  model.addAttribute("sampleEntity",existedSampleEntity);
			  }
			  else {
				  model.addAttribute("id","none");
				  model.addAttribute("state","add");
				  model.addAttribute("sampleEntity",new Lieu());
				  
			  }
			
			} catch (Exception e) {
				model.addAttribute("message",e);
			}
			  model.addAttribute("mode", mode);
//		  
//		  if(id.isPresent()) {
//			  Lieu existedSampleEntity = appService.getLieu(id.get());
//			  model.addAttribute("sampleEntity",existedSampleEntity);
//			  if(mode == "details") {
//				  model.addAttribute("state","details");
//			  }
//			  if(mode == "update") {
//	            try {
//	                model.addAttribute("state","update");
//	            }catch (Exception e) {
//	                model.addAttribute("message",e);
//	            }
//
//			  } 
//		  }
//		  

	    return "lieu/update";
	    
	  }


//	  Lieux New
      @PostMapping("lieux")
      public String saveLieu(Model model, @Valid @NotNull @ModelAttribute("sampleEntity") Lieu sampleEntity, BindingResult result, RedirectAttributes ra){
          
          Departement dep = sampleEntity.setDepartement(appService.getDepartement(sampleEntity.getDepartement().getNumDep()));;

    	  if(dep != null) sampleEntity.setDepartement(dep);
    	  else sampleEntity.setDepartement(new Departement(sampleEntity.getDepartement().getNumDep()));
		  appService.addLieu(sampleEntity);
		  if(result.hasErrors()){
			  ra.addAttribute("message", result.getAllErrors());
		      return "lieu/update";
		  }
		  ra.addFlashAttribute("sampleEntity", sampleEntity);
          ra.addAttribute("message", sampleEntity.getCodeInsee() + " updated successfully");

		  return "redirect:/lieux/page/1";
		}
        
        

//		Lieux delete
        @GetMapping("lieux/delete/{id}")
        public String deleteLieuById(Model model, @PathVariable String id, RedirectAttributes ra) {
            try {
        		appService.deleteLieuById(id);
        		model.addAttribute("message",id +" is successfully deleted");
			} catch (Exception e) {
				model.addAttribute("message",e);
				}
            return "redirect:/lieux/page/1";        
            }




// ---------------------------- End Lieux ---------------------------------



// ---------------------------- Begin Monument ----------------------------


//  Monument all pagiation
    @GetMapping("monuments/page/{pageNumber}")
	public String allMonuments(Model model, @PathVariable("pageNumber") int currentPage, @RequestParam(defaultValue="codeM") String sortField, @RequestParam(defaultValue="asc") String sortDirection)   {
    	try {
        	Sort sort = Sort.by(sortField);
        	sort=  sortDirection.equals("asc") ? sort.ascending() : sort.descending();
            Page<Monument> page = appService.getAllMonuments(currentPage, 15, sort);
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
    
//  Monuments card pagination
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
    

    	  
// //    Monument New form
// 	  @GetMapping("monuments/add")
// 	  public String add(Model model, @ModelAttribute("newMonument") Monument newMonument) {
	
//           color(model);
//           model.addAttribute("newMonument",new Monument());

// 	    return "monument/add";
	    
// 	  }

// //	    Monument New
// 	    @PostMapping("monuments")
// 	    public String saveMonument(@Valid @NotNull @ModelAttribute("newMonument") Monument newMonument, BindingResult result, RedirectAttributes ra){
// 	        appService.addMonument(newMonument);
// 	        if(result.hasErrors()){
// 	            return "monument/add";
// 	        }
// 	        ra.addFlashAttribute("newMonument", newMonument);
// 	        return "redirect:/monuments/card/page/1";
// 	    }


// //	    Monument update form
//         @GetMapping("monuments/update/{id}")
//         public String monumentUpdateForm(Model model, @PathVariable(value="id") String id) {
//             try {
//                 color(model);
//                 Monument sampleEntity = appService.getMonument(id);
//                 model.addAttribute("sampleEntity",sampleEntity);
//             }catch (Exception e) {
//                 model.addAttribute("message",e);
//             }
//         return "monument/update";		    
//         }


// //	Monument update
//     @PostMapping("monuments/update")
//     public String monumentsUpdate(Model model, @ModelAttribute("sampleEntity") Monument sampleEntity, @ModelAttribute("id") String id) {
//         try {				  
//             model.addAttribute("message", sampleEntity.getCodeM() + " updated successfully");
//             appService.addMonument(sampleEntity);
// 	    } catch (Exception e) {
// 	        model.addAttribute("message", e);
// 	    	}
// 	        return "redirect:/monuments/page/1";
//     	}
    
    
    
    







    
//  Monument New Form
//  Monument update form
//  Monument Details Form
	  @GetMapping(value = {"monuments/{mode}", "monuments/{mode}/{id}"})
	  public String monumentForm(Model model, @ModelAttribute("sampleEntity") Monument sampleEntity, @PathVariable(value="id") Optional<String> id, @PathVariable(value="mode") Optional<String> mode) {
		  color(model);
	
		  if(id.isPresent() && mode.isPresent()) {
			  Monument existedSampleEntity = appService.getMonument(id.get());
			  model.addAttribute("sampleEntity",existedSampleEntity);
		  }
		  
		  if(id.isEmpty()) {
			  model.addAttribute("sampleEntity",new Monument());
			  model.addAttribute("mode","add");
		  }

	    return "monument/update";
	    
	  }


//	Monument New
    @PostMapping("monuments")
    public String saveMonument(Model model, @Valid @NotNull @ModelAttribute("sampleEntity") Monument sampleEntity, BindingResult result, RedirectAttributes ra){
        
      Lieu lieu = sampleEntity.setLieu(appService.getLieu(sampleEntity.getLieu().getCodeInsee()));

  	  if(lieu != null) sampleEntity.setLieu(lieu);
  	  else sampleEntity.setLieu(new Lieu(sampleEntity.getLieu().getCodeInsee()));
		  appService.addMonument(sampleEntity);
		  if(result.hasErrors()){
			  ra.addAttribute("message", result.getAllErrors());
		      return "monument/update";
		  }
		  ra.addFlashAttribute("sampleEntity", sampleEntity);
          ra.addAttribute("message", sampleEntity.getCodeM() + " updated successfully");

		  return "redirect:/monuments/page/1";
		}

    
    
    
    
    
    
    
    
    
    
    
    
    
    



//		Monument delete
        @GetMapping("monuments/delete/{id}")
        public String deleteMonumentById(Model model, @PathVariable String id) {
            try {
        		appService.deleteMonumentById(id);
        		model.addAttribute("message",id +" is successfully deleted");
			} catch (Exception e) {
				model.addAttribute("message",e);
				}
            return "redirect:/monuments/page/1";        
            }


// ---------------------------- End Monument   ----------------------------

    
    
    

// ---------------------------- Begin Celebrite   ----------------------------

//  Celebrities pagination
    @GetMapping("celebrities/page/{pageNumber}")
	public String allCelebrities(Model model, @PathVariable("pageNumber") int currentPage, @RequestParam(defaultValue="numCelebrite") String sortField, @RequestParam(defaultValue="asc") String sortDirection)   {
    	try {
        	Sort sort = Sort.by(sortField);
        	sort=  sortDirection.equals("asc") ? sort.ascending() : sort.descending();

    		Page<Celebrite> page = appService.getAllCelebrities(currentPage, 15, sort);
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


//    Celebrities card
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
  
      
//    Celebrities card details
      @GetMapping("celebrities/details/{numCelebrite}")
      public String celebritiesCardDetails(Model model, @PathVariable long numCelebrite) {
          try {
        	  Celebrite celebrite = appService.getCelebriteById(numCelebrite);
              model.addAttribute("celebrite", celebrite);
              color(model);
          }catch (Exception e) {
              model.addAttribute("message",e);
          }
          return "celebrite/details";
      }

      
      
//    A method for changing the color of cards 
  	  public void color(Model model) {
  	    int n = (int) (Math.random() * 7 + 1);
  	    List<String> border = Arrays.asList("border-primary","border-secondary","border-success","border-danger","border-warning","border-info","border-light","border-dark");
  	    List<String> text   = Arrays.asList("text-primary","text-secondary","text-success","text-danger","text-warning","text-info","text-light","text-dark");
  	    model.addAttribute("border", border.get(n));
  	    model.addAttribute("text", text.get(n));
  	    model.addAttribute("n", n);

  	}

      
//    Celebrity New form
	  @GetMapping("celebrities/add")
	  String add(Model model, Celebrite newCelebrite) {
		  try {
			  color(model);
//			  Celebrite celebrite = celebriteRepository.save(newCelebrite);
              model.addAttribute("newCelebrite",newCelebrite);
//              System.out.println("\n\n\n\nnum is ........" +celebrite.getNumCelebrite() + "\\n\\n\\n\\n");
	    	}catch (Exception e) {
	    		model.addAttribute("message",e);
	    	}
	    return "celebrite/add";
	    
	  }

	  
//      Celebrity New
	    @PostMapping("celebrities/add")
	    public String saveCelebriteOld(Model model, @ModelAttribute("newCelebrite") Celebrite newCelebrite, BindingResult result){		// , @Valid @NotNull 
	        try {
                appService.addCelebrite(newCelebrite);                
            } catch (Exception e) {
                model.addAttribute("message",e);
                return "celebrite/update";

            }
	        return "redirect:/celebrities/page/1";
	    }
		
	    
	    
//  Celebrity update form
    @GetMapping("celebrities/update/{id}")
    public String modifyCelebriteOld(Model model, @PathVariable(value="id") long id) {
        try {
            color(model);
            Celebrite newCelebrite = appService.getCelebriteById(id);
            model.addAttribute("newCelebrite",newCelebrite);
        }catch (Exception e) {
            model.addAttribute("message",e);
        }

    return "celebrite/update";
    
    }
          
          
//  Celebrity update
    @PostMapping("celebrities/update")
    public String replaceCelebriteOld(Model model, @ModelAttribute("newCelebrite") Celebrite newCelebrite, @ModelAttribute("id") String id) {		// @RequestBody Celebrite newCelebrite
        
        try {
//				  Celebrite updateCelebrite = appService.getCelebriteById(newCelebrite.getNumCelebrite());
            
//				  updateCelebrite.setNom(newCelebrite.getNom());
//				  updateCelebrite.setPrenom(newCelebrite.getPrenom());
//				  updateCelebrite.setNumCelebrite(newCelebrite.getNumCelebrite());
//				  updateCelebrite.setNationalite(newCelebrite.getNationalite());
//				  updateCelebrite.setImage(newCelebrite.getImage());
//				  updateCelebrite.setUrl(newCelebrite.getUrl());
//				  updateCelebrite.setParent_url(newCelebrite.getParent_url());
            
//				  celebriteRepository.updateCelebrite(newCelebrite.getNom(), newCelebrite.getPrenom(), newCelebrite.getNationalite(), 
//						  newCelebrite.getEpoque(), newCelebrite.getNumCelebrite());
//				  celebriteRepository.save(updateCelebrite);
//				  model.addAttribute("updateCelebrite", updateCelebrite);
//				  model.addAttribute("idf", id);
//				  model.addAttribute("newCelebrite_vodoudi", newCelebrite);
            
//				  appService.updateCelebriteObject(newCelebrite);
//				  celebriteRepository.saveAndFlush(updateCelebrite);
            
            model.addAttribute("message", newCelebrite.getNumCelebrite() + " updated successfully");
            appService.addCelebrite(newCelebrite);

    } catch (Exception e) {
        
        model.addAttribute("message", e);
    }
            
        return "redirect:/celebrities/page/1";
    }
















//  Celebrite New Form
//  Celebrite update form
//  Celebrite Details Form
	  @GetMapping(value = {"celebrities/{mode}", "celebrities/{mode}/{id}"})
	  public String celebriteForm(Model model, @ModelAttribute("sampleEntity") Celebrite sampleEntity, @PathVariable(value="id") Optional<Long> id, @PathVariable(value="mode") Optional<String> mode) {
		  color(model);
	
		  if(id.isPresent() && mode.isPresent()) {
			  Celebrite existedSampleEntity = appService.getCelebriteById(id.get());
			  model.addAttribute("sampleEntity",existedSampleEntity);
		  }
		  
		  
		  
		  
		  if(id.get() == 0) {
			  model.addAttribute("sampleEntity",new Celebrite());
			  model.addAttribute("mode","add");
		  }

	    return "celebrite/update";
	    
	  }


//	Celebrite New
    @PostMapping("celebrities")
    public String saveCelebrite(Model model, @Valid @NotNull @ModelAttribute("sampleEntity") Celebrite sampleEntity, BindingResult result, RedirectAttributes ra){
        
//      Monument monument = sampleEntity.setMonument(appService.getMonument(sampleEntity.getMonuments().codeM()));
//
//  	  if(monument != null) sampleEntity.setMonuments(monument);
//  	  else sampleEntity.setMonuments(new Monument(sampleEntity.getMonuments().codeM()));

    	
    	appService.addCelebrite(sampleEntity);
		  if(result.hasErrors()){
			  ra.addAttribute("message", result.getAllErrors());
		      return "celebrite/update";
		  }
		  ra.addFlashAttribute("sampleEntity", sampleEntity);
          ra.addAttribute("message", sampleEntity.getNumCelebrite() + " updated successfully");

		  return "redirect:/celebrities/page/1";
		}























//    Celebrity delete
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
    
        
        @RequestMapping(
        		  value = "/employee", 
        		  produces = { "application/json", "application/xml" }, 
        		  method = RequestMethod.GET)
        		public @ResponseBody List<Celebrite> getEmployeeById() {
        		    return celebriteRepository.findAll();
        		}

        
        
        
    
    // ---------------------------- End Celebrite   ----------------------------
        
        @GetMapping("toast")
        public String test(Model model) {
            model.addAttribute("name", "Amir SHIRALIPOUR");

			return "toast";
        }
        
        

}






