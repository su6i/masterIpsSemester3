package com.masterips.javaeeproject.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "celebrity")
public class Celebrite implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "numCelebrite")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int celebrity_number;
	
	@Column(name = "nom", length = 16)
	private String family;
	
	@Column(name = "prenom", length = 16)
	private String name;
	
	@Column(name = "nationalite", length = 10)
	private String nationality;
	
	@Column(name = "epoque", length = 6)
	private String era;

	public int getCelebrity_number() {
		return celebrity_number;
	}

	public void setCelebrity_number(int celebrity_number) {
		this.celebrity_number = celebrity_number;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEra() {
		return era;
	}

	public void setEra(String era) {
		this.era = era;
	}
	
	
	
}
