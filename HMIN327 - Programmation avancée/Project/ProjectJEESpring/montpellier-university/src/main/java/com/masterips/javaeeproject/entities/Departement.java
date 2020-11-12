package com.masterips.javaeeproject.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departement implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	private String numDep;
	private String dep;
	private String chefLieu;
	private String nomDep;
	private String numRegion;
	
	@OneToMany(mappedBy="dep",fetch=FetchType.LAZY)
	private Set<Lieu> lieuSet;
	
	
}
