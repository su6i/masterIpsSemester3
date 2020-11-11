package um2.m2ips.projet.metier;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import um2.m2ips.projet.dao.IProjectDao;
import um2.m2ips.projet.entities.AssocieA;
import um2.m2ips.projet.entities.Celebrite;
import um2.m2ips.projet.entities.Departement;
import um2.m2ips.projet.entities.Lieu;
import um2.m2ips.projet.entities.Monument;

@Transactional
public class ProjectMetierImpl implements IProjectMetier{
	
	private IProjectDao dao;
	
	public void setDao(IProjectDao dao) {
		this.dao = dao;
	}

	@Override
	public Lieu addLieu(Lieu l) {
		return dao.addLieu(l);
	}

	@Override
	public Departement addDepartement(Departement d) {
		return dao.addDepartement(d);
	}

	@Override
	public Monument addMonument(Monument m) {
		return dao.addMonument(m);
	}

	@Override
	public Celebrite addCelebrite(Celebrite c) {
		// TODO Auto-generated method stub
		return dao.addCelebrite(c);
	}

	@Override
	public void addMonumentToLieu(Long codeM, String codeInsee) {
		dao.addMonumentToLieu(codeM, codeInsee);
	}

	@Override
	public float getDistanceBetweenMonuments(String nomMonA, String nomMonB) {

		return dao.getDistanceBetweenMonuments(nomMonA, nomMonB);
	}

	@Override
	public List<Monument> getListMonumentsByDep(String nomDep) {
		
		return dao.getListMonumentsByDep(nomDep);
	}

	@Override
	public List<Monument> getListMonumentsByLieu(String nomCom) {
		return dao.getListMonumentsByLieu(nomCom);
	}

	@Override
	public Departement getDepartement(String numDep) {
		return dao.getDepartement(numDep);
	}

	@Override
	public AssocieA addAssociation(AssocieA a) {
		return dao.addAssociation(a);
	}

}
