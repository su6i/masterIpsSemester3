package um2.m2ips.projet.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Monument implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String codeM;
	private String nomM;
	private String propritaire;
	private String typeMonument;
	private float latitude;
	private float longitude;
	 @ManyToOne
	 @JoinColumn(name="FK_CodeInsee")
	private Lieu localite;
	
	public Monument() {
		super();
	}

	public Monument(String codeM, String nomM, String propritaire, String typeMonument, float latitude, float longitude,
			Lieu localite) {
		super();
		this.codeM = codeM;
		this.nomM = nomM;
		this.propritaire = propritaire;
		this.typeMonument = typeMonument;
		this.latitude = latitude;
		this.longitude = longitude;
		this.localite = localite;
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
	public String getPropritaire() {
		return propritaire;
	}
	public void setPropritaire(String propritaire) {
		this.propritaire = propritaire;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getlongitude() {
		return longitude;
	}
	public void setlongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getTypeMonument() {
		return typeMonument;
	}

	public void setTypeMonument(String typeMonument) {
		this.typeMonument = typeMonument;
	}
	
	
}
