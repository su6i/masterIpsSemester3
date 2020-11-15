
import java.util.Date;
import javax.persistence.*;
 
@Entity
public class Personne {
    // Required by JPA
    private Personne() {}
 
    public Personne(String numSS, String nom, Date DateN, String genre, Ville localisation) {
    	this.numSS = numSS;
        this.nom = nom;
        this.DateN = DateN;
        this.genre = genre;
        this.localisation = localisation;
    }
 
    @Column
    @Id
    public String getNumSS() {
        return numSS;
    }
    
    public void setNumSS(String numSS) {
        this.numSS = numSS;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Ville.class)
    @JoinColumn(name="LOCALISATION")
    public Ville getLocalisation() {
        return localisation;
    }
 
    public void setLocalisation(Ville localisation) {
        this.localisation = localisation;
     }  
    
    
    public Date getDateN() {
        return (Date)DateN.clone();
    }
 
    public void setDateN(Date DateN) {
        this.DateN = DateN;
    }
    
    public String toString() {
        return String.format("%s [%tF]", nom, DateN, localisation);
    }
 
   
    private String      numSS;
    private String      nom;
    private Date        DateN;
    private String 		genre;
    private Ville localisation;
}
