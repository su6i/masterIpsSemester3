package com.masterips.javaeeproject.entities;

import java.util.Set;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Celebrite implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int numCelebrite;
	private String nom;
	private String prenom;
	private String nationalite;
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
