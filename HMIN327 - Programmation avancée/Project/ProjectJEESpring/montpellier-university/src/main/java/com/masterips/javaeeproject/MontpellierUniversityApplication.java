package com.masterips.javaeeproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.masterips.javaeeproject.dao.DepartementRepository;
import com.masterips.javaeeproject.entities.Departement;

@SpringBootApplication
public class MontpellierUniversityApplication implements CommandLineRunner {

	@Autowired
	private DepartementRepository departementRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MontpellierUniversityApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {

		// First added department
		Departement amir = new Departement();
		amir.setDepartement_number("n999");
		amir.setDepartment_name("AmirDep");
		amir.setRegion_number("r999");
		departementRepository.save(amir);
		
		Departement ali = new Departement();
		ali.setDepartement_number("nnnn");
		ali.setDepartment_name("AliDep");
		ali.setRegion_number("r888");
		ali.setChef_lieu("Ali");
		departementRepository.save(ali);
		
		
		
	}

}
