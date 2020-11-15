
	import javax.persistence.*;
import java.util.*;
	public class RetrouveVille {
	    public static void main(String[] args) {
	        // Démarre JPA et ouvre une session
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BdPersist");
	        EntityManager em = emf.createEntityManager();
	        // Commence une transaction
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        	Ville v = em.find(Ville.class, "Arcachon");
	        	if (v!=null)
	            System.out.println(v.toString() + " trouvée"); 
	        	else System.out.println(" non référencée dans la base");
	        tx.commit();
	  
	 
	        // Ferme la session et termine JPA
	        em.close();
	        emf.close();
	    }
	}
