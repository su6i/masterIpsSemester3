package um2.m2ips.projet.models;

import um2.m2ips.projet.entities.Departement;

public class DepartementForm {
	private String numDep;
	private Departement departement;
	
	public String getNumDep() {
		return numDep;
	}
	public void setNumDep(String numDep) {
		this.numDep = numDep;
	}
	public Departement getDepartement() {
		return departement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
}
