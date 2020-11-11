package um2.m2ips.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import um2.m2ips.projet.entities.AssocieA;
import um2.m2ips.projet.entities.Celebrite;
import um2.m2ips.projet.entities.Departement;
import um2.m2ips.projet.entities.Lieu;
import um2.m2ips.projet.entities.Monument;

public class ProjectDaoImpl implements IProjectDao{
	@PersistenceContext
	private EntityManager em; // Implementation JPA

	@Override
	public Lieu addLieu(Lieu l) {
		em.persist(l);		
		return l;
	}

	@Override
	public Departement addDepartement(Departement d) {
		em.persist(d);
		return d;
	}

	@Override
	public Monument addMonument(Monument m) {
		em.persist(m);
		return m;
	}

	@Override
	public Celebrite addCelebrite(Celebrite c) {
		em.persist(c);
		return c;
	}



	@Override
	public void addMonumentToLieu(Long codeM, String codeInsee) {
		Monument m = em.find(Monument.class, codeM);
		Lieu l = em.find(Lieu.class, codeInsee);
		l.getMonuments().add(m);
	}

	@Override
	public float getDistanceBetweenMonuments(String nomMonA, String nomMonB) {
		float distance = 0;
		Monument m1 = em.find(Monument.class, nomMonA);
		Monument m2 = em.find(Monument.class, nomMonA);
		//TODO: calcules

		return distance;
	}

	@Override
	public List<Monument> getListMonumentsByDep(String nomDep) {
		Query req = em.createQuery("select m from Lieu m where m.lieu.dep =:x");
		req.setParameter("x", nomDep);
		
		return req.getResultList();
	}

	@Override
	public List<Monument> getListMonumentsByLieu(String nomCom) {
		// il y a em.findAll...
		Query req = em.createQuery("select m from Lieu m where m.lieu.nomCom =:x");
		req.setParameter("x", nomCom);

		return req.getResultList();
	}

	@Override
	public Departement getDepartement(String numDep) {
		Departement d = em.find(Departement.class, numDep);
		return d;
	}

	@Override
	public AssocieA addAssociation(AssocieA a) {
//		Monument m=em.find(Monument.class, codeM);
//		Celebrite c=em.find(Celebrite.class, numCel);
//		AssocieA a=
		em.persist(a);
		return a;
	}

}
