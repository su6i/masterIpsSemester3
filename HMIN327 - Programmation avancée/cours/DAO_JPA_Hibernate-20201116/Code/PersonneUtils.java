import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;

public class PersonneUtils {
  protected EntityManager em;

  public PersonneUtils(EntityManager em) {
    this.em = em;
  }

  public Personne createPersonne(String numSS, String nom, Date DateN, String genre, Ville localisation) {
	  Personne p = new Personne(numSS,nom,DateN,genre,localisation);
    em.persist(p);
    return p;
  }

  public void removePersonne(String numSS) {
	  Personne p = findPersonne(numSS);
    if (p != null) {
      em.remove(p);
    }
  }

  public Personne findPersonne(String numSS) {
    return em.find(Personne.class, numSS);
  }

  public Collection<Personne> findAll_SameFirstLetter(String name) {
    Query query = em.createQuery("SELECT p FROM Personne p where nom like :name");
    query.setParameter("name", name);
    return (Collection<Personne>) query.getResultList();
  }
}
