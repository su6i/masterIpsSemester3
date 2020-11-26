package com.masterips.javaeeproject.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lieu")
public class Lieu implements Serializable {
	 /**
	 * insert into  lieu values ('34172','MONTPELLIER',3.876716,43.610769,'34');
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(length = 5)
	private String codeInsee;
	
	@ManyToOne
	@JoinColumn
	private Departement dep;
	
	@Column(length = 30)
	private String nomCom;
		
	private double longitude;
	
	private double latitude;
	
	

	public Lieu() {
		super();
	}


	// 		Lieu l1 = new Lieu("34172","MONTPELLIER",3.876716,43.610769,"34");


	public Lieu(String codeInsee, String nomCom, double longitude, double latitude, Departement dep) {
		super();
		this.codeInsee = codeInsee;
		this.nomCom = nomCom;
		this.longitude = longitude;
		this.latitude = latitude;
		this.dep = dep;
	}




	public String getCodeInsee() {
		return codeInsee;
	}




	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}




	public Departement getDep() {
		return dep;
	}




	public void setDep(Departement dep) {
		this.dep = dep;
	}




	public String getNomCom() {
		return nomCom;
	}




	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
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
	

	@Override
    public String toString() {
        return "Code Insee=" + this.codeInsee + "Nom Commune: " + this.nomCom + ", Departement: " 
        					 + dep +  ", Longitude: " + this.longitude + ", Latitude: " + this.latitude ;
    }
	
	
	
	
	
	}
