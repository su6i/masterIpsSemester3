	import javax.persistence.*;


import java.util.*;
	 
	public class AjoutePersonne {
	    public static void main(String[] args) {
	        // Démarre JPA et ouvre une session
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BdPersist");
	        EntityManager em = emf.createEntityManager();
	        System.out.println("Entity manager prêt");
	 
	        // Commence une transaction
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        System.out.println("Début de la transaction");

	       
	            // Création d'une personne
	        	Ville v = new Ville ("Arcachon","France");
	        	Date dateN = new GregorianCalendar(1924, Calendar.APRIL, 3).getTime();
	            Personne pier = new Personne("9876", "Jules" ,dateN, "M",v);
	            System.out.println(pier.toString() + " créé");
	 
	            // Rend celui-ci persistent dans la base de données
	          //  em.persist(v);
	            em.persist(pier);
	        
	        /*
	         * Fin du code spécifique
	         */
	 
	        // Applique les modifications à la base de données
	        tx.commit();
	        System.out.println("Transaction confirmée");
	 
	        // Ferme la session et termine JPA
	        em.close();
	        emf.close();
	    }
	}