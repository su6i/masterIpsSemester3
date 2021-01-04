package um2.m2ips.projet.dao;

import java.util.List;

import um2.m2ips.projet.entities.AssocieA;
import um2.m2ips.projet.entities.Celebrite;
import um2.m2ips.projet.entities.Departement;
import um2.m2ips.projet.entities.Lieu;
import um2.m2ips.projet.entities.Monument;

public interface IProjectDao {
	// ADD
	public Lieu addLieu(Lieu l);
	public Departement addDepartement(Departement d);
	public Monument addMonument(Monument m);
	public Celebrite addCelebrite(Celebrite c);
	public AssocieA addAssociation(AssocieA a);
	
	public Departement getDepartement(String numDep);

	
	public void addMonumentToLieu(Long codeM, String codeInsee);
	
	public float getDistanceBetweenMonuments(String nomMonA,String nomMonB);
	public List<Monument> getListMonumentsByDep(String nomDep);
	public List<Monument> getListMonumentsByLieu(String nomCom);

}
