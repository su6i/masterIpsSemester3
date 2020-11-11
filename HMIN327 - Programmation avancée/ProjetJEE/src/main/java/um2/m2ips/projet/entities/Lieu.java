package um2.m2ips.projet.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Lieu implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String codeInsee;
	private String nomCom;
	private float latitude;
	private float longitude;
	
	@OneToMany(mappedBy="localite",fetch=FetchType.LAZY)
	private Collection<Monument> monuments;
	
	 @ManyToOne
	 @JoinColumn(name="FK_NumDep")
	private Departement dep;

	public Lieu() {
	}

	public Lieu(String codeInsee, String nomCom, float latitude, float longitude, Departement dep) {

		this.codeInsee = codeInsee;
		this.nomCom = nomCom;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dep = dep;
	}



	public String getCodeInsee() {
		return codeInsee;
	}

	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	public String getNomCom() {
		return nomCom;
	}

	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Collection<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(Collection<Monument> monuments) {
		this.monuments = monuments;
	}
	
}
