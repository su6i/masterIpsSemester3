package pack1;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;



@Entity
public class Acteur1 {

    @Id
    private String idActeur;
    private String nom;
   
    
    @ManyToMany(mappedBy = "acteurs")
    private List<Film1> films = new ArrayList<>();
            

    
    public Acteur1() { }
    
    public Acteur1( String nom ) {
        this.nom=nom;
    }

    
    public String getIdActeur() {
        return idActeur;
    }
    
    
    public String getNom() {
        return nom;
    }
    

    public List<Film1> getFilms() {
        return films;
    }
    
    public String toString() {
        return this.idActeur + ": " + this.nom;
    }
    
}
