package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "departement")
public class Departement implements Serializable {
	 /**
	 * insert into departement values ('12','AVEYRON','12202');  
	 */
	private static final long serialVersionUID = 1L;
	

	@Id	
	@Column(name = "numDep", length = 4)
	private String numDep;
				
	@Column(name = "nomDep", length = 30)
	private String nomDep;
		
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="chefLieu", insertable=false ,updatable=false)
	//@OneToMany(mappedBy="dep",fetch=FetchType.LAZY)
//	@Column(name = "chefLieuDepCodeInsee", length = 5)
	private Lieu lieu;

	@Column(name = "chefLieu", length = 5)
	private String chefLieu;



	
	public Departement() {
		super();
	}
	
	public Departement(String numDep) {
		super();
		this.setNumDep(numDep);
	}

	
	// insert into departement values ('34','HERAULT','34172');                                     
	// Hibernate: insert into departement (chef_lieu, nom_dep, reg, num_dep) values (?, ?, ?, ?)


	public Departement(String numDep, String nomDep, String chefLieu) {
		super();
		this.setNumDep(numDep);
		this.setNomDep(nomDep);
		
		
		lieu = new Lieu(chefLieu);
		this.chefLieu = chefLieu;
//		this.chefLieu = this.lieu.getCodeInsee();
		

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
	
	

	public String getChefLieu() {
		return chefLieu;
	}

	public void setChefLieu(String chefLieu) {
		this.chefLieu = chefLieu;
	}

	public Departement getDepartement() {
		return this;
	}
	
	public void setDepartement(Departement departement) {
		departement.setDepartement(this);
	}


	
	@Override
    public String toString() {
        return "Numéro Departement: " + this.numDep + ", Nom Departement: " + this.nomDep +
        		", Chef Lieu: " + this.chefLieu  ;
        					 
    }
	
	
}
