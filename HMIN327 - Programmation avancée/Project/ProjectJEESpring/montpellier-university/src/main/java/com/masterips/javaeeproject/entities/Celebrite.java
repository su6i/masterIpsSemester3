package com.masterips.javaeeproject.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "celebrite")
public class Celebrite implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int numCelebrite;
	
	@Column(length = 16)
	private String nom;
	
	@Column(length = 16)
	private String prenom;
	
	@Column(length = 10)
	private String nationalite;
	
	@Column(length = 6)
	private String epoque;
	
	

	public Celebrite() {
		super();
	}



	public Celebrite(String nom, String prenom, String nationalite, String epoque) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
		this.epoque = epoque;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	
	
	@Override
    public String toString() {
        return "Nom Celebrite: " + this.nom + "Prénom Celebrite: " + this.prenom + ", Nationalite: " + this.nationalite +
        	   "Epoque: " + this.epoque ;
    }
	
	
}
