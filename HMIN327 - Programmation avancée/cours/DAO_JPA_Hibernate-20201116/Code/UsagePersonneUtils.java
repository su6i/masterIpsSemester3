import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Collection;

public class UsagePersonneUtils {
	 public static void main(String[] args) {
	        
	        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BdPersist");
	        EntityManager em = emf.createEntityManager();
	       
	        PersonneUtils pu = new PersonneUtils(em);
	        // Commence une transaction
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        System.out.println("Début Transaction");
	        Collection<Personne> c1 = pu.findAll_SameFirstLetter("E%");
	        
	        for (Personne p : c1)
	        { System.out.println(p.toString());}
	        
	        // Applique les modifications à la base de données
	        tx.commit();
	        System.out.println("Fin Transaction");
	 
	        // Ferme la session et termine JPA
	        em.close();
	        emf.close();
}
}