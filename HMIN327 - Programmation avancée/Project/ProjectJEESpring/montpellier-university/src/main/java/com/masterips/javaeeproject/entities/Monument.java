package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Entity
@Table(name = "monument")
public class Monument implements Serializable {
	 /**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="code_m", length = 12)
	private String codeM;
	
	@Column(name="nom_m", length = 47)
	private String nomM;
	
	@Column(length = 10)
	private String proprietaire;
	
	@Column(name="typeMonument", length = 17)
	private String typeMonument;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "latitude")
	private double latitude;
		
    @ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="codeLieu", insertable=false ,updatable=false)
	private Lieu lieu = new Lieu();
	
    
	
	
//	@ManyToMany
//	@ManyToMany(mappedBy = "monuments", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable( 
//	    joinColumns = @JoinColumn( name = "codeM" ),
//	    inverseJoinColumns = @JoinColumn( name = "numCelebrite" ))
	
	
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(joinColumns=@JoinColumn(name="code_m"), inverseJoinColumns=@JoinColumn(name="num_celebrite"))  

	private Set<Celebrite> celebrities;
		
	

    @Column(length = 255)
	private String url;
    
    @Column(length = 255)
	private String parent_url;


	
	public Monument() {
		super();
	}
	
	public Monument(String codeM) {
		this();
		this.setCodeM(codeM);
	}

	

	public Monument(String codeM, String nomM, String proprietaire, String typeMonument, double longitude,
			double latitude, Lieu lieu) {
		this(codeM);
		this.setNomM(nomM);
		this.setProprietaire(proprietaire);
		this.setTypeMonument(typeMonument);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
		
		this.setLieu(lieu);
	}
	
	

	public String getCodeM() {
		return codeM;
	}


	public void setCodeM(String codeM) {
		this.codeM = codeM;
        this.url = "http://localhost:8080/json/monuments/"+this.codeM;
        this.parent_url = "http://localhost:8080/json/monuments/page/1";

	}


	public String getNomM() {
		return nomM;
	}


	public void setNomM(String nomM) {
		this.nomM = nomM;
	}


	public String getProprietaire() {
		return proprietaire;
	}


	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}


	public String getTypeMonument() {
		return typeMonument;
	}


	public void setTypeMonument(String typeMonument) {
		this.typeMonument = typeMonument;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
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
        return "Code Monument: " + this.codeM + ", Nom Monument: " + this.nomM + ", Proprietaire: " + this.proprietaire +
        	   ", Type Monument: " + this.typeMonument + ", Longitude: " + this.longitude + ", Latitude: " + this.latitude	+
        	   ", Localisation: " + this.lieu.getCodeInsee();
    }

	public Set<Celebrite> getCelebrities() {
		return celebrities;
	}

	public void setCelebrities(Set<Celebrite> celebrities) {
		this.celebrities = celebrities;
	}

	
	
	
	

}
	
	