package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
        
//	@NotBlank(message = "Chef Lieu is mandatory")
    @JsonManagedReference
	@OneToOne(mappedBy = "departement", cascade = {CascadeType.ALL})
	@JoinColumn(name="chefLieu", insertable=false ,updatable=false)
	private Lieu lieu = new Lieu();


    @Column
	private String url;
    
    @Column(length = 255)
	private String parent_url;


	
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
        this.url = "http://localhost:8080/json/departements/"+this.numDep;
        this.parent_url = "http://localhost:8080/json/departements/page/1";

	}


	public String getNomDep() {
		return nomDep;
	}


	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}


	@JsonGetter
	public Lieu getLieu() {
		return lieu;
	}

	@JsonSetter
	public void setLieu(Lieu lieu) {
//		String chefLieu = this.getLieu().getCodeInsee();
//		if(chefLieu != null) {
//			setLieu(lieu);
//		} else {
//			setLieu(new Lieu(chefLieu));
//		}
		this.lieu = lieu;
	}

	
	
	public String getUrl() {
		return url;
	}

	

	public String getParent_url() {
		return parent_url;
	}


	@Override
    public String toString() {
        return "N°: " + this.numDep + ", Nom: " + this.nomDep +
        		", Ch.L.: " + this.lieu;
        					 
    }
	
	
}
