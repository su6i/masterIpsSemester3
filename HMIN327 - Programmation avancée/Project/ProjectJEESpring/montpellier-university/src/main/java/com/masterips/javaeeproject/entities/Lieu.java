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
	 * insert into  lieu values ('34172','MONTPELLIER',3.876716,43.610769,'34');
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

	@Column(name = "dep", length = 4)
	private String dep;
	
	

	public Lieu() {
		super();
	}
	
	public Lieu(String codeInsee) {
		super();
		this.setCodeInsee(codeInsee);
	}
	

	// 		Lieu l1 = new Lieu("34172","MONTPELLIER",3.876716,43.610769,"34");


	public Lieu(String codeInsee, String nomCom, double longitude, double latitude, String dep) {
		super();
		this.setCodeInsee(codeInsee);
		this.setNomCom(nomCom);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
		
		departement = new Departement(dep);
		this.dep = this.departement.getNumDep();
	}




	public String getCodeInsee() {
		return this.codeInsee;
	}




	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}




//	public Departement getDep() {
//		return dep;
//	}
//
//
//
//
//	public void setDep(Departement dep) {
//		this.dep = dep;
//	}




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
	public Lieu getLieu() {
		return this;
	}
	
	@JsonSetter
	public void setLieu(Lieu lieu) {
		lieu.setLieu(this);
	}
	
	public String getDep() {
		return this.dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}
	
	
	//------------------------------
	
//	public Inscription getInscription() {
//
//		return inscription;
//	}
//
//	public void setInscription(Inscription inscription) {
//		
//		this.inscription = inscription;  		
//	}
//
//	public void setEtudiant(Etudiant etudiant) {
//		etudiant.setEtudiant(this);
//	}
	
	
	//------------------------------

	
	
	
	

	@Override
    public String toString() {
        return "Code Insee=" + this.codeInsee + "Nom Commune: " + this.nomCom + ", Departement: " 
        					 + dep +  ", Longitude: " + this.longitude + ", Latitude: " + this.latitude ;
    }

	
	
	
	
	
	}
