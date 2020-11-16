package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "departement")
public class Departement implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name = "numDep", length = 4)
	private String departement_number;
		
	@Column(name = "chefLieu", length = 46)
	private String chef_lieu;
	
	@Column(name = "nomDep", length = 30)
	private String department_name;
	
	@Column(name = "reg", length = 4)
	private String region_number;
	
	@OneToMany(mappedBy="dep",fetch=FetchType.LAZY)
	private Set<Lieu> lieuSet;

	public String getDepartement_number() {
		return departement_number;
	}

	public void setDepartement_number(String departement_number) {
		this.departement_number = departement_number;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getRegion_number() {
		return region_number;
	}

	public void setRegion_number(String region_number) {
		this.region_number = region_number;
	}

	public String getChef_lieu() {
		return chef_lieu;
	}

	public void setChef_lieu(String chef_lieu) {
		this.chef_lieu = chef_lieu;
	}
	
	
	
	
}
