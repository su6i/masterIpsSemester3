package com.masterips.javaeeproject.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.SQLInsert;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
//@Table(name="celebrite",uniqueConstraints=@UniqueConstraint(columnNames={"nom","prenom","nationalite","epoque"}))

@SQLInsert(sql = "INSERT IGNORE INTO Celebrite(num_celebrite, epoque, image, nationalite, nom, prenom, url, parent_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" )
public class Celebrite implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String numCelebrite;
	
	@Column(length = 16)
	private String nom;
	
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
		this.setNumCelebrite("undefined", "undefined");
		
	}
	
	public Celebrite(@JsonProperty("prenom") String prenom, @JsonProperty("nom")String nom) {
		this();
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setNumCelebrite(prenom, nom);
	}



	public Celebrite(String nom, String prenom, @JsonProperty("nationalite") String nationalite, @JsonProperty("epoque") String epoque) {
		this(prenom, nom);
		this.setNationalite(nationalite);
		this.setEpoque(epoque);
		

	}



	public String getNumCelebrite() {
		return this.numCelebrite;
	}

	public void setNumCelebrite(String prenom, String nom) {
		String key = prenom + ' ' + nom;
		this.numCelebrite = Long.toString(Math.abs(key.hashCode()));
		this.url = "http://localhost:8080/json/celebrities/"+numCelebrite;
		this.parent_url = "http://localhost:8080/json/celebrities/page/1";
		this.image = "/image/" + numCelebrite + ".jpg";
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


	@Override
    public String toString() {
        return "ID: " + this.numCelebrite +", Nom Celebrite: " + this.nom + ", Prénom Celebrite: " + this.prenom + ", Nationalite: " + this.nationalite +
        	   ", Epoque: " + this.epoque ;
    }
	
	
}
