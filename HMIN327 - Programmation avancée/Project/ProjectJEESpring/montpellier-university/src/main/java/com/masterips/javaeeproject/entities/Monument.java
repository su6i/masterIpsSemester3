package com.masterips.javaeeproject.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

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
		
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="codeLieu", insertable=false ,updatable=false)
	private Lieu lieu;
	
	
	@ManyToMany
	@JoinTable( 
	    joinColumns = @JoinColumn( name = "code_m" ),
	    inverseJoinColumns = @JoinColumn( name = "num_celebrite" ) 
    )
	private Set<Celebrite> associea_celebrite;


	
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

	@Override
    public String toString() {
        return "Code Monument: " + this.codeM + ", Nom Monument: " + this.nomM + ", Proprietaire: " + this.proprietaire +
        	   ", Type Monument: " + this.typeMonument + ", Longitude: " + this.longitude + ", Latitude: " + this.latitude	+
        	   ", Localisation: " + this.lieu.getCodeInsee();
    }

	
	
	
	

	public void forEach(Consumer<? super Celebrite> action) {
		associea_celebrite.forEach(action);
	}

	public int size() {
		return associea_celebrite.size();
	}

	public boolean isEmpty() {
		return associea_celebrite.isEmpty();
	}

	public boolean contains(Object o) {
		return associea_celebrite.contains(o);
	}

	public Iterator<Celebrite> iterator() {
		return associea_celebrite.iterator();
	}

	public Object[] toArray() {
		return associea_celebrite.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return associea_celebrite.toArray(a);
	}

	public boolean add(Celebrite e) {
		return associea_celebrite.add(e);
	}

	public boolean remove(Object o) {
		return associea_celebrite.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return associea_celebrite.containsAll(c);
	}

	public boolean addAll(Collection<? extends Celebrite> c) {
		return associea_celebrite.addAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return associea_celebrite.retainAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return associea_celebrite.removeAll(c);
	}

	public void clear() {
		associea_celebrite.clear();
	}

	public boolean equals(Object o) {
		return associea_celebrite.equals(o);
	}

	public int hashCode() {
		return associea_celebrite.hashCode();
	}

	public Spliterator<Celebrite> spliterator() {
		return associea_celebrite.spliterator();
	}

	public <T> T[] toArray(IntFunction<T[]> generator) {
		return associea_celebrite.toArray(generator);
	}

	public boolean removeIf(Predicate<? super Celebrite> filter) {
		return associea_celebrite.removeIf(filter);
	}

	public Stream<Celebrite> stream() {
		return associea_celebrite.stream();
	}

	public Stream<Celebrite> parallelStream() {
		return associea_celebrite.parallelStream();
	}


	
}
