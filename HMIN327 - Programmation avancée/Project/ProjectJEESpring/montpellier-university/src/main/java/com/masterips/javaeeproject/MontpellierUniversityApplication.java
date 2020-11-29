// https://postgis.net/workshops/postgis-intro/geography.html
// geohash in Java

package com.masterips.javaeeproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.service.AppService;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.dao.LieuRepository;
import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.dao.CelebriteRepository;
import com.masterips.javaeeproject.dao.DepartementRepository;

//import java.sql.*;

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
		
//		Session session = new Configuration().configure()
//				.buildSessionFactory().openSession();
//		
//		Transaction t = session.beginTransaction();
//
//		
//		Celebrite amir = new Celebrite("SHIRALI POUR","Amir","Iranienne","1981");
//		Celebrite amir2 = new Celebrite("SHIRALI POUR","Amir","Iranienne","1981");
//
//		
//		session.saveOrUpdate(amir);
//		session.saveOrUpdate(amir2);
//        t.commit();
//        session.close();
//		
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

//		insert into departement values ('38','ISERE','38185');                                    
		  // First added department

		Departement herault = departementRepository.save(new Departement("34","MONTPELLIER","34172")); //l34172
		Departement isere   = departementRepository.save(new Departement("38","ISERE", "38185"));  //"38185"

//		insert into  lieu values ('34172','MONTPELLIER',3.876716,43.610769,'34');
		Lieu l34172 = lieuRepository.save(new Lieu("34172","MONTPELLIER",3.876716,43.610769,"34"));
		Lieu l34198 = lieuRepository.save(new Lieu("34198","PEROLS",3.954211,43.563782,"34"));
		Lieu l2A004 = lieuRepository.save(new Lieu("2A004","Ajaccio",8.7386,41.9192,"2A"));
		
		
//		insert into monument values ('spfb070hzm8g','HOTEL DE GRIFFY','PRIVE','HOTEL_PARTICULIER',3.87848611,43.611075,'34172');                                                            
//		(String codeM, String nomM, String proprietaire, String typeMonument, double longitude,
//		double latitude, Lieu localisation)
		Monument spfb05nwqmvu = monumentRepository.save(new Monument("spfb05nwqmvu","HOTEL DE GANGES","PRIVE","HOTEL_PARTICULIER",3.87639,43.611334,"34172"));
		
		
//		 (String nom, String prenom, String nationalite, String epoque)
//		Celebrite amir = celebriteRepository.save(new Celebrite("SHIRALI POUR","Amir","Iranienne","1981"));
//		Celebrite amir2 = celebriteRepository.save(new Celebrite("SHIRALI POUR","Amir","Iranienne","1981"));
		
//		departementRepository.updateDepartement(numDep, nomDep);
		
//		String query = "ALTER TABLE tablename MODIFY `col 1` INT(5)";
//		Statement stmt = co.createStatement();
//		int rslt = stmt.executeUpdate(query);


		System.out.println("\n\n\n"+herault+ "\n\n\n");
		
		
		
		
		
		
		
		
		// Test
//		appService.getDepartement("isere");
	}

}
