package com.masterips.javaeeproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.masterips.javaeeproject.dao.CelebriteRepository;
import com.masterips.javaeeproject.dao.DepartementRepository;
import com.masterips.javaeeproject.dao.LieuRepository;
import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.entities.Departement;
import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;

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
	
	
	public static void main(String[] args) {
		SpringApplication.run(MontpellierUniversityApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {

		/* // First added department
		Departement herault = new Departement("34","HERAULT","34172");
		departementRepository.save(herault);
		
		Departement isere = new Departement("38","ISERE","38185"); 
		departementRepository.save(isere);
		
		Lieu l1 = new Lieu("34172","MONTPELLIER",3.876716,43.610769,"34");
		lieuRepository.save(l1);
		
		Lieu l2 = new Lieu("34198","PEROLS",3.954211,43.563782,"34");
		lieuRepository.save(l2);
		
		// ('spfb05nwqmvu','HOTEL DE GANGES','PRIVE','HOTEL_PARTICULIER',3.87639,43.611334,'34172');
		Monument m1 = new Monument("spfb05nwqmvu","HOTEL DE GANGES","PRIVE","HOTEL_PARTICULIER",3.87639,43.611334,"34172");
	*/	
		
	}

}
