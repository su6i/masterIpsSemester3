package com.masterips.javaeeproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.masterips.javaeeproject.dao.CelebriteRepository;
import com.masterips.javaeeproject.dao.DepartementRepository;
import com.masterips.javaeeproject.dao.LieuRepository;
import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.services.AppService;

@SpringBootApplication
public class MontpellierUniversityApplication implements CommandLineRunner{
	
	@Autowired
	private DepartementRepository departementRepository;
	
	@Autowired
	private MonumentRepository monumentRepository;
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private CelebriteRepository celebriteRepository;
	
	@Autowired
	private AppService appService;

	

	public static void main(String[] args) {
		SpringApplication.run(MontpellierUniversityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
//		— pouvoir consulter la base de données au sujet des communes, de leurs monuments ou de leurs célébrités (prévoyez un jeu d’au moins 10 requètes à cet effet)
//		— pouvoir mettre à jour la base de données au travers d’opérations de: 
//																			modification, 
//																			suppression et 
//																			insertion (prévoyez au moins un exemple de chaque)

//		— pouvoir lancer des traitements sur les données : par exemple 
//																pouvoir connaître la distance entre deux monuments cibles dans la même commune ou 
//																dans deux communes différentes
//		
//		— pouvoir se connecter au système au travers de différents rôles et donc de différents droits en matière d’accès en lecture/écriture sur les données. Vous pouvez par exemple 
//			définir les rôles 
//							”Administrateur”, 
//							”Voyagiste”, 
//							”Touriste” qui donneront lieu à des vues externes différentes du système.
		
//		— ...
//		L’originalité sera une plus-value à votre travail et les aspects relatifs à l'orgonomie et à la restitution visuelle seront également considérés.

		  // First added department

//		Departement herault = departementRepository.save(new Departement("34","MONTPELLIER",new Lieu("34172"))); 	//l34172
//		Departement isere   = departementRepository.save(new Departement("38","ISERE", new Lieu("38185")));  		//"38185"
//		Departement ajaccio = departementRepository.save(new Departement("2A","CORSE-DU-SUD",new Lieu("2A004"))); 	//l34172

//		Lieu l34172 = lieuRepository.save(new Lieu("34172","MONTPELLIER",3.876716,43.610769,new Departement("34")));
//		Lieu l34198 = lieuRepository.save(new Lieu("34198","PEROLS",3.954211,43.563782,new Departement("34")));
//		Lieu l2A004 = lieuRepository.save(new Lieu("2A004","Ajaccio",8.7386,41.9192,new Departement("2A")));
				
//		Monument spfb05nwqmvu = monumentRepository.save(new Monument("spfb05nwqmvu","HOTEL DE GANGES","PRIVE","HOTEL_PARTICULIER",3.87639,43.611334,l34172));
		
//		 (String nom, String prenom, String nationalite, String epoque)
//		 Celebrite amir    = celebriteRepository.save(new Celebrite("SHIRALI POUR"  ,"Amir"           ,"Iranienne"           ,"1981"));
//         Celebrite obama   = celebriteRepository.save(new Celebrite("OBAMA"         ,"BARAK"          ,"Americaine"          ,"1981"));
//         Celebrite hassina = celebriteRepository.save(new Celebrite("BOUFATIS"      ,"Hassina"        ,"Algérienne"          ,"1881"));
//         Celebrite madonna = celebriteRepository.save(new Celebrite("Ciccone"       ,"Madonna Louise" ,"Americaine-Italienne","1958")); 
        
//		Celebrite amir3 = celebriteRepository.save(new Celebrite("SHIRALI POUR","Amir","Iranienne","1981"));
		
//		departementRepository.updateDepartement(numDep, nomDep);
		
//		String query = "ALTER TABLE tablename MODIFY `col 1` INT(5)";
//		Statement stmt = co.createStatement();
//		int rslt = stmt.executeUpdate(query);


		
//		System.out.println("\n\n\n"+herault+ "\n\n\n");
//		System.out.println("\n"+l34172+ "\n");
//		System.out.println("\n"+spfb05nwqmvu+ "\n");
//		System.out.println("\n"+amir+ "\n");
		
		for(Celebrite celeb: this.celebriteRepository.findAll()) 
			System.out.println(celeb.toString());

		
		
		
	}

}
