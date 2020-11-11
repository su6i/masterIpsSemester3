package um2.m2ips.projet.entities;

import java.io.Serializable;
import java.util.Collection;

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
	private String chefLieu;
	private String nomDep;
	private String region;
	
	 @OneToMany(mappedBy="dep",fetch=FetchType.LAZY)
	private Collection<Lieu> lieux;
	
	public Departement() {
		super();
	}
	
	public Departement(String numDep, String chefLieu, String nomDep, String region) {
		super();
		this.numDep = numDep;
		this.chefLieu = chefLieu;
		this.nomDep = nomDep;
		this.region = region;
	}
	
//	public Collection<Lieu> getLieux() {
//		return lieux;
//	}
//
//	public void setLieux(Collection<Lieu> lieux) {
//		this.lieux = lieux;
//	}

	public String getNumDep() {
		return numDep;
	}
	public void setNumDep(String numDep) {
		this.numDep = numDep;
	}
	public String getChefLieu() {
		return chefLieu;
	}
	public void setChefLieu(String chefLieu) {
		this.chefLieu = chefLieu;
	}
	public String getNomDep() {
		return nomDep;
	}
	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	
	
}
