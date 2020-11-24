package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "monument")
public class Monument implements Serializable {
	 /**
	 * insert into monument values ('spfb05nwqmvu','HOTEL DE GANGES','PRIVE','HOTEL_PARTICULIER',3.87639,43.611334,'34172');  
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="code_M", length = 12)
	private String codeM;
	
	@Column(name="nom_M", length = 47)
	private String nomM;
	
	@Column(length = 10)
	private String proprietaire;
	
	@Column(name="typeMonument", length = 17)
	private String typeMonument;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "latitude")
	private double latitude;
		
	@ManyToOne
	@JoinColumn(name="codeLieu")
	private Lieu localisation;
	
	@ManyToMany
	@JoinTable( 
    joinColumns = @JoinColumn( name = "codeM" ),
    inverseJoinColumns = @JoinColumn( name = "numCelebrite" ) )
	private Set<Celebrite> associea_celebrite;

	
	public Monument() {
		super();
	}

	

	// insert into monument values ('spfb0725nhcx','HOTEL DE BEAULAC','PRIVE','HOTEL_PARTICULIER',3.87843333,43.6121444,'34172');                                                            

	public Monument(String codeM, String nomM, String proprietaire, String typeMonument, double longitude,
			double latitude, Lieu localisation) {
		super();
		this.codeM = codeM;
		this.nomM = nomM;
		this.proprietaire = proprietaire;
		this.typeMonument = typeMonument;
		this.longitude = longitude;
		this.latitude = latitude;
		this.localisation = localisation;
	}
	
	

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



	
}
