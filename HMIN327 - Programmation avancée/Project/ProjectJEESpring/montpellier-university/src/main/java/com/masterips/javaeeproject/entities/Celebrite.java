package com.masterips.javaeeproject.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLInsert;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
//@Table(name="celebrite",uniqueConstraints=@UniqueConstraint(columnNames={"nom","prenom","nationalite","epoque"}))

//@SQLInsert(sql = "INSERT INTO Celebrite(num_celebrite, epoque, image, nationalite, nom, prenom, url, parent_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" )
public class Celebrite implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numCelebrite;
	
	@NotBlank(message = "Family is mandatory")
	@Column(length = 16)
	private String nom;
	
	@NotBlank(message = "Name is mandatory")
	@Column(length = 16)
	private String prenom;
	
	@Column(length = 30)
	private String nationalite;
	
	@Column(length = 4)
	private String epoque;

    @Column(length = 255)
	private String url;
    
    @Column(length = 255)
	private String parent_url;
    
    
    @Column(length = 255)
	private String image;
	
	

	

	public Celebrite() {
		super();		
	}
	
	public Celebrite(long numCelebrite) {
		this();
		this.setNumCelebrite(numCelebrite);
	}



	public Celebrite(long numCelebrite, @JsonProperty("nom") String nom, @JsonProperty("prenom") String prenom, @JsonProperty("nationalite") String nationalite, @JsonProperty("epoque") String epoque) {
		this(numCelebrite);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNationalite(nationalite);
		this.setEpoque(epoque);
		this.setUrl(url);
		this.setParent_url(parent_url);
		this.setImage(image);
		

	}



	public long getNumCelebrite() {
		return this.numCelebrite;
	}

	public void setNumCelebrite(long numCelebrite) {
		this.numCelebrite = numCelebrite;
	}

	public String getNom() {
		return nom;
	}



	public String setNom(String nom) {
		this.nom = nom;
		return nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public String setPrenom(String prenom) {
		this.prenom = prenom;
		return prenom;
	}



	public String getNationalite() {
		return nationalite;
	}



	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}



	public String getEpoque() {
		return epoque;
	}



	public void setEpoque(String epoque) {
		this.epoque = epoque;
	}

	

	public String getUrl() {

		return url;
	}

	
	public String getParent_url() {
		return parent_url;
	}


	public String getImage() {
		return image;
	}
	
	

	public void setUrl(String url) {
		if(url != "" || url != null) this.url = url;
		this.url = "http://localhost:8080/json/celebrities/"+numCelebrite;
	}

	public void setParent_url(String parent_url) {
		if(parent_url != "" || parent_url != null) this.parent_url = parent_url;
		this.parent_url = "http://localhost:8080/json/celebrities/page/1";
	}

	public void setImage(String image) {
		if(image != "" || image != null) this.image = image;
		else this.image = "/image/" + numCelebrite + ".jpg";
	}

	@Override
    public String toString() {
        return "ID: " + this.numCelebrite +", Nom Celebrite: " + this.nom + ", Prénom Celebrite: " + this.prenom + ", Nationalite: " + this.nationalite +
        	   ", Epoque: " + this.epoque ;
    }
	
	
}
