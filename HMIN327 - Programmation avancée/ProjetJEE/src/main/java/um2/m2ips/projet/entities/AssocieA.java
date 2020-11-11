package um2.m2ips.projet.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AssocieA  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Id
	 @ManyToOne //possibilte de plusieurs associations de monuments vers une celebrite
	 @JoinColumn(name="FK_NumCeleb")
	private Celebrite celebrite;
	 @Id
	 @ManyToOne //possibilte de plusieurs associations de celebrites vers un monument
	 @JoinColumn(name="FK_CodeM")
	private Monument monument;

	public AssocieA() {
	}

	public AssocieA(Celebrite celebrite, Monument monument) {
		super();
		this.celebrite = celebrite;
		this.monument = monument;
	}
	 
	
}
