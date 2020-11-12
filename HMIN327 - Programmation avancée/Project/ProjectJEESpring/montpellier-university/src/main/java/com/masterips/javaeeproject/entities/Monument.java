package com.masterips.javaeeproject.entities;

import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import java.util.Set;

@Entity
public class Monument implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	private String codeM;
	private String nomM;
	private String proprietaire;
	private String typeMonument;
	private double longitude;
	private double latitude;
	
	@ManyToOne
	@JoinColumn(name="fk_CodeInsee")
	private Lieu   localisation;
	
	@ManyToMany
	private Set<Celebrite> associeACelebrite;
	public String getCodeM() {
		return codeM;
	}
	public void setCodeM(String codeM) {
		this.codeM = codeM;
	}
	public String getNomM() {
		return nomM;
	}
	public void setNomM(String nomM) {
		this.nomM = nomM;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public String getTypeMonument() {
		return typeMonument;
	}
	public void setTypeMonument(String typeMonument) {
		this.typeMonument = typeMonument;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Lieu getLocalisation() {
		return localisation;
	}
	public void setLocalisation(Lieu localisation) {
		this.localisation = localisation;
	}
	public Set<Celebrite> getAssocieACelebrite() {
		return associeACelebrite;
	}
	public void setAssocieACelebrite(Set<Celebrite> associeACelebrite) {
		this.associeACelebrite = associeACelebrite;
	}
	

}
