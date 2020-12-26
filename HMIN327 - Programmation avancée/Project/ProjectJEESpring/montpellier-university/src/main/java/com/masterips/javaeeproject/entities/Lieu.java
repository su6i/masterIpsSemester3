package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "lieu")
public class Lieu implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(length = 5)
	private String codeInsee;
	
//	@JsonManagedReference
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="dep", insertable=false ,updatable=false)
	private Departement departement;
	
	@Column(length = 30)
	private String nomCom;
		
	private double longitude;
	
	private double latitude;	
	

	public Lieu() {
		super();
	}
	
	public Lieu(String codeInsee) {
		this();
		this.setCodeInsee(codeInsee);
	}
	



	public Lieu(String codeInsee, String nomCom, double longitude, double latitude, Departement departement) {
		this(codeInsee);
		this.setNomCom(nomCom);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
		
		this.setDepartement(departement);
	}




	public String getCodeInsee() {
		return this.codeInsee;
	}




	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}



	public String getNomCom() {
		return nomCom;
	}




	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
	}




	public double getLongitude() {
		return this.longitude;
	}




	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}




	public double getLatitude() {
		return this.latitude;
	}




	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
		

	@JsonIgnore
	public Departement getDepartement() {
		return departement;
	}
	
	@JsonSetter
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@Override
    public String toString() {
        return "Code Insee=" + this.codeInsee + ", Nom Commune: " + this.nomCom + ", Departement: " 
        					 + this.departement.getNumDep() +  ", Longitude: " + this.longitude + ", Latitude: " + this.latitude ;
    }

	
	
	
	
	
	}
