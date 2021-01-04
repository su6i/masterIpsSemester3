package um2.m2ips.projet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import um2.m2ips.projet.entities.Departement;
import um2.m2ips.projet.metier.IProjectMetier;
import um2.m2ips.projet.models.DepartementForm;

@Controller
public class ProjectController {
	@Autowired
	private IProjectMetier metier;
	
	@RequestMapping(value="/")
	public String indexDefault(){
		return "home";
	}
	
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("departementForm", new DepartementForm());
		return "myProject";
	}
	
	@RequestMapping(value="/chargerDepartement")
	public String chargerD(DepartementForm df, Model model){ //données à stocké dans le model puis à ajouter au formulaire df
		Departement dp = metier.getDepartement(df.getNumDep()); // appel de la couche metier pour recuperer un departement
		df.setDepartement(dp); // stock du resultat dans le model
		model.addAttribute("departementForm", df); // ajout du model au formulaire 
		return "myProject";
	}
}
