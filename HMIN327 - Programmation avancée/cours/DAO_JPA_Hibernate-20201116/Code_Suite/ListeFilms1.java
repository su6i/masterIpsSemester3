package pack1;

import javax.persistence.*;
import java.util.*;
 
public class ListeFilms1 {
    public static void main(String[] args) {
        // Démarre JPA et ouvre une session
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BdPersist");
        EntityManager em = emf.createEntityManager();
        System.out.println("Entity manager prêt");
 
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        System.out.println("Début de la transaction");
     
        Query query = em.createQuery("SELECT f FROM Film1 f ");
        System.out.println("Les films");
        @SuppressWarnings("unchecked")
        List<Film1> films = (List<Film1>)query.getResultList();
        for(Film1 fl : films) {
         System.out.println(fl.getTitre());
         List<Acteur1> c2 = fl.getActeurs();
	        for (Acteur1 p : c2)
	        { System.out.println("      "+p.toString());}
        }
           tx.commit();
        System.out.println("Transaction confirmée");
 
       
        em.close();
        emf.close();
    }
}
