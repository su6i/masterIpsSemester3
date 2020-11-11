package TestsDaoMetier;

import org.hibernate.engine.profile.Association;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import um2.m2ips.projet.entities.AssocieA;
import um2.m2ips.projet.entities.Celebrite;
import um2.m2ips.projet.entities.Departement;
import um2.m2ips.projet.entities.Lieu;
import um2.m2ips.projet.entities.Monument;
import um2.m2ips.projet.metier.IProjectMetier;
import um2.m2ips.projet.metier.ProjectMetierImpl;

public class test {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IProjectMetier metier = (IProjectMetier) context.getBean("metier");
		
		metier.getListMonumentsByDep("Herault");
		
		Departement d1 = new Departement("34", "Montpellier", "Herault", "Occitanie");
		Lieu l1 = new Lieu("34000", "Montp", 1.0f, 1.0f, d1);
		
		Monument m1 = new Monument("codeM1", "Place de la Comédie", "Mairie de Montpellier", "Place historique", 1.0f, 1.0f, l1);
		Monument m2 = new Monument("codeM2", "Place de la Comédie", "Mairie de Montpellier", "Place historique", 1.0f, 1.0f, l1);
		Monument m3 = new Monument("codeM3", "Place de la Comédie", "Mairie de Montpellier", "Place historique", 1.0f, 1.0f, l1);
		Celebrite c1 = new Celebrite("Ben", "Said", "Terre", "Now");
		Celebrite c2 = new Celebrite("Ali", "Momo", "Terre", "Now");
		Celebrite c3 = new Celebrite("Sak", "Sissou", "Terre", "Now");
		
		AssocieA a1 = new AssocieA(c1, m1);
		AssocieA a2 = new AssocieA(c1, m2);
		AssocieA a3 = new AssocieA(c1, m3);
		AssocieA a4 = new AssocieA(c2, m3);
		AssocieA a5 = new AssocieA(c3, m3);
		
		metier.addDepartement(d1);
		metier.addLieu(l1);
		
		metier.addMonument(m1);
		metier.addMonument(m2);
		metier.addMonument(m3);
		metier.addCelebrite(c1);
		metier.addCelebrite(c2);
		metier.addCelebrite(c3);
		metier.addAssociation(a1);
		metier.addAssociation(a2);
		metier.addAssociation(a3);
		metier.addAssociation(a4);
		metier.addAssociation(a5);
		
		/*
		 drop table AssocieA; drop table Celebrite; drop table Monument; drop table Lieu; drop table Departement;

		 select * from Departement; select * from Lieu; select * from Monument; select * from Celebrite; select * from AssocieA;
		*/
	}
}
