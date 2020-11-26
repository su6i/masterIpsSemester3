// https://postgis.net/workshops/postgis-intro/geography.html
// geohash in Java

package com.masterips.javaeeproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.service.AppService;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.dao.LieuRepository;
import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.dao.CelebriteRepository;
import com.masterips.javaeeproject.dao.DepartementRepository;

import java.sql.*;

@SpringBootApplication
public class MontpellierUniversityApplication implements CommandLineRunner {

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
		Departement herault = departementRepository.save(new Departement("34","MONTPELLIER", null)); //l34172
		departementRepository.save(herault);

		Departement isere = new Departement("38","ISERE", null);  //"38185"
		departementRepository.save(isere);

		
		Lieu l34172 = lieuRepository.save(new Lieu("34172","MONTPELLIER",3.876716,43.610769,herault));
		lieuRepository.save(l34172);
		
		Lieu l34198 = new Lieu("34198","PEROLS",3.954211,43.563782,herault);
		lieuRepository.save(l34198);
		
	
		Monument m1 = new Monument("spfb05nwqmvu","HOTEL DE GANGES","PRIVE","HOTEL_PARTICULIER",3.87639,43.611334,l34172);
		monumentRepository.save(m1);
		
		
//		 (String nom, String prenom, String nationalite, String epoque)
		Celebrite amir = new Celebrite("SHIRALI POUR","Amir","Iranienne","1981");
		celebriteRepository.save(amir);
		
		herault = departementRepository.updateDepartement("38","ISERE", l34172);
		departementRepository.save(herault);
		
//		String query = "ALTER TABLE tablename MODIFY `col 1` INT(5)";
//		Statement stmt = co.createStatement();
//		int rslt = stmt.executeUpdate(query);


		System.out.println("\n\n\n"+herault+ "\n\n\n");
		
		
		
		
		
		
		
		
		// Test
		appService.getDepartement("isere");
	}

}
