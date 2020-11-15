package com.masterips.javaeeproject.entities;

import java.io.Serializable;
import java.util.HashSet;
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
	@Column(name = "departement")
	private String numDep;
	
	@Column(name = "departement", length = 4)
	private String dep;
	
	@Column(name = "chef_lieu", length = 46)
	private String chefLieu;
	
	@Column(name = "department_name", length = 30)
	private String nomDep;
	
	@Column(name = "region_number", length = 4)
	private String numRegion;
	
	@OneToMany(mappedBy="fk_numDep",fetch=FetchType.LAZY)
	private Set<Lieu> lieuSet;
	
	
}
