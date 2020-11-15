package com.masterips.javaeeproject.entities;

import java.util.Set;
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
	@Column(name = "celebrity_number")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int numCelebrite;
	
	@Column(name = "family", length = 16)
	private String nom;
	
	@Column(name = "name", length = 16)
	private String prenom;
	
	@Column(name = "nationality", length = 10)
	private String nationalite;
	
	@Column(name = "era", length = 6)
	private String epoque;
	
	
	
	
	public int getNumCelebrite() {
		return numCelebrite;
	}
	public void setNumCelebrite(int numCelebrite) {
		this.numCelebrite = numCelebrite;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
