package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "departement")
public class Departement implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id	
	@Column(name = "numDep", length = 4)
	private String numDep;
				
	@Column(name = "nomDep", length = 30)
	private String nomDep;
		
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="chefLieu", insertable=false ,updatable=false)
	private Lieu lieu;


	
	public Departement() {
		super();
	}
	
	public Departement(String numDep) {
		this();
		this.setNumDep(numDep);
	}

	

	public Departement(String numDep, String nomDep, Lieu lieu) {
		this(numDep);
		this.setNomDep(nomDep);
		
		
		this.setLieu(lieu);
	}


	public String getNumDep() {
		return numDep;
	}


	public void setNumDep(String numDep) {
		this.numDep = numDep;
	}


	public String getNomDep() {
		return nomDep;
	}


	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}


	public Lieu getLieu() {
		return lieu;
	}

	@JsonSetter
	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	@Override
    public String toString() {
        return "Numéro Departement: " + this.numDep + ", Nom Departement: " + this.nomDep +
        		", Chef Lieu: " + this.lieu.getCodeInsee()  ;
        					 
    }
	
	
}
