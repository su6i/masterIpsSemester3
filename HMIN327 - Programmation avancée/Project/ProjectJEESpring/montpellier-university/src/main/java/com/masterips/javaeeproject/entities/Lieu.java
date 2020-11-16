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
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name = "codeInsee", length = 5)
	private String insee_code;
	
	@ManyToOne
	@JoinColumn(name="fk_numDep")
	private Departement dep;
	
	@Column(name = "nomCom", length = 30)
	private String commun_name;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "latitude")
	private double latitude;
	
	
	
	
	}
