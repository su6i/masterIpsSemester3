package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "monument")
public class Monument implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name = "codeM", length = 5)
	private String monument_code;
	
	@Column(name = "nomM", length = 25)
	private String monument_name;
	
	@Column(name = "proprietaire", length = 10)
	private String owner;
	
	@Column(name = "monument_type", length = 16)
	private String typeMonument;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "latitude")
	private double latitude;
	
	@ManyToOne
	@JoinColumn(name="fk_codeInsee")
	private Lieu   localisation;
	
	@ManyToMany
	private Set<Celebrite> associeACelebrite;
	
	
}
