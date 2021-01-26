package com.masterips.javaeeproject.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "lieu")
public class Lieu implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(length = 5)
	private String codeInsee;
    
    @JsonBackReference	
    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
	@JoinColumn(name="dep", insertable=false ,updatable=false)
	private Departement departement = new Departement();
	
	@Column(length = 30)
	private String nomCom;
		
	private double longitude;
	
	private double latitude;	
    
    @Column(length = 255)
	private String url;
    
    @Column(length = 255)
	private String parent_url;
    
    
    @OneToMany(
            mappedBy = "lieu", 
            cascade = CascadeType.ALL)
    private Set<Monument> monuments = new HashSet<>();



	public Lieu() {
		super();
	}
	
	public Lieu(String codeInsee) {
		this();
		this.setCodeInsee(codeInsee);
	}
	



	public Lieu(String codeInsee, String nomCom, double longitude, double latitude, Departement departement) {
		this(codeInsee);
		this.setNomCom(nomCom);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
		
		this.setDepartement(departement);
	}




	public String getCodeInsee() {
		return this.codeInsee;
	}




	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
        this.url = "http://localhost:8080/json/lieux/"+this.codeInsee;
        this.parent_url = "http://localhost:8080/json/lieux/page/1";

	}



	public String getNomCom() {
		return nomCom;
	}




	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
	}




	public double getLongitude() {
		return this.longitude;
	}




	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}




	public double getLatitude() {
		return this.latitude;
	}




	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
		

	@JsonGetter
	public Departement getDepartement() {
		return departement;
	}
	
	@JsonSetter
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	
	

	public String getUrl() {
		return url;
	}

	
	

	public String getParent_url() {
		return parent_url;
	}


	@Override
    public String toString() {
        return "Code Insee=" + this.codeInsee + ", Nom Commune: " + this.nomCom + ", Departement: " 
        					 + this.departement.getNumDep() +  ", Longitude: " + this.longitude + ", Latitude: " + this.latitude ;
    }

	public Set<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(Set<Monument> monuments) {
		this.monuments = monuments;
	}

	/**
	 * @return
	 * @see com.masterips.javaeeproject.entities.Departement#getNumDep()
	 */
	public String getNumDep() {
		return departement.getNumDep();
	}

	/**
	 * @param numDep
	 * @see com.masterips.javaeeproject.entities.Departement#setNumDep(java.lang.String)
	 */
	public void setNumDep(String numDep) {
		departement.setNumDep(numDep);
	}

	/**
	 * @return
	 * @see com.masterips.javaeeproject.entities.Departement#getNomDep()
	 */
	public String getNomDep() {
		return departement.getNomDep();
	}

	/**
	 * @param nomDep
	 * @see com.masterips.javaeeproject.entities.Departement#setNomDep(java.lang.String)
	 */
	public void setNomDep(String nomDep) {
		departement.setNomDep(nomDep);
	}

	/**
	 * @return
	 * @see com.masterips.javaeeproject.entities.Departement#getLieu()
	 */
	public Lieu getLieu() {
		return departement.getLieu();
	}

	/**
	 * @param lieu
	 * @see com.masterips.javaeeproject.entities.Departement#setLieu(com.masterips.javaeeproject.entities.Lieu)
	 */
	public void setLieu(Lieu lieu) {
		departement.setLieu(lieu);
	}

	/**
	 * @param action
	 * @see java.lang.Iterable#forEach(java.util.function.Consumer)
	 */
	public void forEach(Consumer<? super Monument> action) {
		monuments.forEach(action);
	}

	/**
	 * @return
	 * @see java.util.Set#size()
	 */
	public int size() {
		return monuments.size();
	}

	/**
	 * @return
	 * @see java.util.Set#isEmpty()
	 */
	public boolean isEmpty() {
		return monuments.isEmpty();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Set#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return monuments.contains(o);
	}

	/**
	 * @return
	 * @see java.util.Set#toArray()
	 */
	public Object[] toArray() {
		return monuments.toArray();
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.Set#add(java.lang.Object)
	 */
	public boolean add(Monument e) {
		return monuments.add(e);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Set#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return monuments.remove(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Set#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return monuments.containsAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Set#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Monument> c) {
		return monuments.addAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.Set#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		return monuments.removeAll(c);
	}

	/**
	 * 
	 * @see java.util.Set#clear()
	 */
	public void clear() {
		monuments.clear();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Set#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return monuments.equals(o);
	}

	/**
	 * @return
	 * @see java.util.Set#hashCode()
	 */
	public int hashCode() {
		return monuments.hashCode();
	}

	/**
	 * @return
	 * @see java.util.Collection#stream()
	 */
	public Stream<Monument> stream() {
		return monuments.stream();
	}

	
	
	
	
	
	}
