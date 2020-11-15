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
import javax.persistence.Table;

@Entity @Table(name="Film1")
public class Film1 {

    @Id
    private int idFilm;
    private String titre;
    
    @ManyToMany
    @JoinTable( name = "Joue1",
                joinColumns = @JoinColumn( name = "idFilm" ),
                inverseJoinColumns = @JoinColumn( name = "idActeur" ) )
    private List<Acteur1> acteurs = new ArrayList<>();
            
    
    public int getIdFilm() {
        return idFilm;
    }
    
    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public List<Acteur1> getActeurs() {
        return acteurs;
    }
    
    @Override
    public String toString() {
        return "[" + this.titre + "]";
    }
    
}
