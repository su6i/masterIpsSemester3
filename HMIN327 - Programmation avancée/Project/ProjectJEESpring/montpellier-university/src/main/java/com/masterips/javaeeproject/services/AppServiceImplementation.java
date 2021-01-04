package com.masterips.javaeeproject.services;

import java.util.Arrays;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;


import com.masterips.javaeeproject.dao.LieuRepository;
import com.masterips.javaeeproject.dao.MonumentRepository;
import com.masterips.javaeeproject.dao.CelebriteRepository;
import com.masterips.javaeeproject.dao.DepartementRepository;

import com.masterips.javaeeproject.entities.Lieu;
import com.masterips.javaeeproject.entities.Monument;
import com.masterips.javaeeproject.entities.Celebrite;
import com.masterips.javaeeproject.entities.Departement;


@Service
public class AppServiceImplementation implements AppService {
	
	
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private MonumentRepository monumentRepository;

	@Autowired
	private CelebriteRepository celebriteRepository;
	
	@Autowired
	private DepartementRepository departementRepository;
	
	
//	Department
	
	@Override
	public Departement getDepartement(String numDep) {
		
		 if(numDep==null || (!departementRepository.existsById(numDep))) 
			 throw new RuntimeException("Can't find entered department");
		 return departementRepository.findById(numDep).get();
	}
	
	@Override
	public Page<Departement> getAllDepartements(int pageNumber, int items, Sort sort) {
		
		Pageable pageable = PageRequest.of(pageNumber - 1, items, sort);
		return departementRepository.findAll(pageable);
	}
	
	@Override
	public Departement addDepartement(Departement departement) {
		return departementRepository.save(departement);
	}
    
    @Override
	public List<Departement> findAll(){
		return departementRepository.findAll();
	}
    
	
	@Override
	public List<Departement> getByNameDepartementContaining(String nom) {
		// TODO Auto-generated method stub
		return departementRepository.getByNameDepartementContaining(nom);
	}

	@Override
	public boolean deleteDepartementById(String numDep) {
		// TODO Auto-generated method stub
		return departementRepository.deleteDepartementById(numDep);
	}

	
	
	




//	Lieu 



	 
	@Override
	public Lieu getLieu(String codeIsee) {
		 if(codeIsee==null || (!lieuRepository.existsById(codeIsee))) 
			 throw new RuntimeException("Can't find entered Lieu");
		 return lieuRepository.findById(codeIsee).get();

	}
	
	@Override
	public Page<Lieu> getAllLieux(int pageNumber, int items, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber - 1, items, sort);
		return lieuRepository.findAll(pageable);
	}
	
//	public Page<Lieu> getAllLieux(int page, int size) {
//		return lieuRepository.getAllLieuxPage(new PageRequest(page, size));
//	}
//
//	@Override
//	public Page<Lieu> getAllLieuxPage(PageRequest of) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
	@Override
	public Lieu addLieu(Lieu lieu) {
		return lieuRepository.save(lieu);
	}
	
	
	@Override
	public List<Lieu> getByNameLieuContaining(String nom) {
		// TODO Auto-generated method stub
		return lieuRepository.getByNameLieuContaining(nom);
	}

	@Override
	public boolean deleteLieuById(String codeIsee) {
		// TODO Auto-generated method stub
		return lieuRepository.deleteLieuById(codeIsee);
	}

	
	
	
	
	
	
	
	
    
    

//	Monument
	
    @Override
	public Monument getMonument(String codeM) {
		 if(codeM==null || (!monumentRepository.existsById(codeM))) 
			 throw new RuntimeException("Can't find entered Monument");
		 return monumentRepository.findById(codeM).get();
	}

//	public List<Monument> getAllMonuments() {
//		
//		return monumentRepository.findAll();
//	}
	

//	@Override
//	public Page<Monument> getAllMonuments(int pageNumber) {
//		Pageable pageable = PageRequest.of(pageNumber - 1, 17);
//		return monumentRepository.findAll(pageable);
//	}

    
	@Override
	public Page<Monument> getAllMonuments(int pageNumber, int items, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber - 1, items, sort);
		return monumentRepository.findAll(pageable);
	}



	@Override
	public void addMonument(Monument monument) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addMonumentToLieu(String codeM, String codeInsee) {
		

//		Monument m = em.find(Monument.class, codeM);
//		Lieu l = em.find(Lieu.class, codeInsee);
//		l.getMonuments().add(m);

		
		
	}

	@Override
	public float getDistanceBetweenMonuments(String nomMonA, String nomMonB) {

            // /**
            //  * Calculate distance between two points in latitude and longitude taking
            //  * into account height difference. If you are not interested in height
            //  * difference pass 0.0. Uses Haversine method as its base.
            //  * 
            //  * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
            //  * el2 End altitude in meters
            //  * @returns Distance in Meters
            //  */
            // public static double distance(double lat1, double lat2, double lon1,
            //         double lon2, double el1, double el2) {

            //     final int R = 6371; // Radius of the earth

            //     double latDistance = Math.toRadians(lat2 - lat1);
            //     double lonDistance = Math.toRadians(lon2 - lon1);
            //     double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            //             + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            //             * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            //     double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            //     double distance = R * c * 1000; // convert to meters

            //     double height = el1 - el2;

            //     distance = Math.pow(distance, 2) + Math.pow(height, 2);

            //     return Math.sqrt(distance);
            // }





        return 0;
	}

	
	@Override
	public List<Monument> getByNameMonumentContaining(String nom) {
		// TODO Auto-generated method stub
		return monumentRepository.getByNameMonumentContaining(nom);
	}

	
	@Override
	public boolean deleteMonumentById(String codeM) {
		// TODO Auto-generated method stub
		return monumentRepository.deleteMonumentById(codeM);
	}

	
	
	@Override
	public List<Monument> getListMonumentsByDep(String nomDep) {
		// TODO Auto-generated method stub
		return monumentRepository.getListMonumentsByDep(nomDep);
	}
	
	@Override
	public List<Monument> getListMonumentsByLieu(String nomCom) {
		return monumentRepository.getListMonumentsByLieu(nomCom);
	}

    
    
    
//	Celebrite


    @Override
	public List<Celebrite> getCelebriteByName(String prenom) {
		return celebriteRepository.getCelebriteByName(prenom);
	}

    @Override
	public List<Celebrite> getCelebriteByFamily(String nom) {
		return celebriteRepository.getCelebriteByFamily(nom);
	}

    @Override
	public Page<Celebrite> getAllCelebrities(int pageNumber, int items, Sort sort) {
		Pageable pageable = PageRequest.of(pageNumber - 1, items, sort);
		return celebriteRepository.findAll(pageable);
	}

	
	@Override
	public Celebrite addCelebrite(Celebrite celebrite) {
		return celebriteRepository.save(celebrite);
	}

    @Override
	public Celebrite getCelebriteById(String numCelebrite) {
		 if(numCelebrite==null || (!celebriteRepository.existsById(numCelebrite))) 
			 throw new RuntimeException("Can't find entered Celebrite");
		 
		 return celebriteRepository.findById(numCelebrite).get();
	}

    @Override
    public List<Celebrite> getByNameCelebriteContaining(String nom) {
    	return celebriteRepository.getByNameContaining(nom);
    }
    
	

	@Override
	public int deleteCelebriteById(String numCelebrite) {
		return celebriteRepository.deleteCelebriteById(numCelebrite);
	}








	
	


	



}
