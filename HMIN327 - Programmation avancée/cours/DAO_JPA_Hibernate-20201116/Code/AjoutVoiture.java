
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 
/**
 * UserDAOTest.java
 * Copyright by CodeJava.net
 */
public class AjoutVoiture {
 
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("BdPersist");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
         
        Car c1 = new Car();
        c1.setMake("Citroen");
        c1.setModel("2 CV");
        
        entityManager.persist(c1);
         
        entityManager.getTransaction().commit();
         
        entityManager.close();
        factory.close();
    }
}