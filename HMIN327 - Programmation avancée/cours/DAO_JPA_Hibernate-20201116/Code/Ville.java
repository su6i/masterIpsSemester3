
import java.util.Date;
import javax.persistence.*;
 
@Entity
public class Ville {
    // Required by JPA
    private Ville() {}
 
    public Ville(String nom, String pays) {
        this.nom = nom;
        this.pays = pays;
    }
 
    public String getNom() {
        return nom;
    }
 
   
    public String getPays() {
        return nom;
    }
   
    public String toString() {
        return String.format("%s %s", nom, pays);
    }
 
    @Id
    private String      nom;
    @Column(name="pays", length=15)
    private String      pays;
 
}
