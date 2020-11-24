package com.masterips.javaeeproject.entities;

import java.io.Serializable;

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
		
	//@OneToOne
	//@JoinColumn(name="chefLieu")
	//@OneToMany(mappedBy="dep",fetch=FetchType.LAZY)
	@Column(name = "chefLieuDepCodeInsee", length = 5)
	private String codeInsee;

	
	public Departement() {
		super();
	}

	// insert into departement values ('34','HERAULT','34172');                                     
	// Hibernate: insert into departement (chef_lieu, nom_dep, reg, num_dep) values (?, ?, ?, ?)


	public Departement(String numDep, String nomDep, String codeInsee) {
		super();
		this.numDep = numDep;
		this.nomDep = nomDep;
		this.codeInsee = codeInsee;
		
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

	
	
}
