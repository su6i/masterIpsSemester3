package com.masterips.javaeeproject.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.SQLInsert;


@Entity
@Table(name="celebrite",uniqueConstraints=@UniqueConstraint(columnNames={"nom","prenom","nationalite","epoque"}))

@SQLInsert(sql = "INSERT IGNORE INTO celebrite(epoque, nationalite,nom, prenom) " +
"VALUES (?, ?, ?, ?)" )
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
	
	@Column(length = 4)
	private String epoque;
	
	

	public Celebrite() {
		super();
	}



	public Celebrite(String nom, String prenom, String nationalite, String epoque) {
		super();
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNationalite(nationalite);
		this.setEpoque(epoque);
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

	
	public Celebrite getCelebrite() {
		return this;
	}
	
	
	@Override
    public String toString() {
        return "Nom Celebrite: " + this.nom + ", Prénom Celebrite: " + this.prenom + ", Nationalite: " + this.nationalite +
        	   ", Epoque: " + this.epoque ;
    }
	
	
}
