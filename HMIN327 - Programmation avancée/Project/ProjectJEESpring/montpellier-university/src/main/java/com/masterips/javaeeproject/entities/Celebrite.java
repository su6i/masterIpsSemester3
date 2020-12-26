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
//@Table(name="celebrite",uniqueConstraints=@UniqueConstraint(columnNames={"nom","prenom","nationalite","epoque"}))

@SQLInsert(sql = "INSERT IGNORE INTO Celebrite(epoque, nationalite,nom, prenom, id) VALUES (?, ?, ?, ?, ?)" )
public class Celebrite implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
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
		this.setId("undefined", "undefined");
	}
	
	public Celebrite(String nom) {
		this();
		this.setNom(nom);
		this.setId("undefined", nom);
	}



	public Celebrite(String nom, String prenom, String nationalite, String epoque) {
		this(nom);
		this.setPrenom(prenom);
		this.setId(prenom, nom);
		this.setNationalite(nationalite);
		this.setEpoque(epoque);
	}



	public long getId() {
		return id;
	}

	public int setId(String prenom, String nom) {
		String key = prenom + ' ' + nom;
		this.id = Math.abs(key.hashCode());
		return this.id;

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
        return "ID: " + this.id +", Nom Celebrite: " + this.nom + ", Prénom Celebrite: " + this.prenom + ", Nationalite: " + this.nationalite +
        	   ", Epoque: " + this.epoque ;
    }
	
	
}
