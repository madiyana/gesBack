package com.toubasoft.dashboard;

public class DBCommandeDTO {

	private Integer nbRecu;
	private Integer nbEnvoye;
	private Integer nbEnCours;
	private Integer nbAnnule;

	public Integer getNbRecu() {
		return nbRecu;
	}

	public void setNbRecu(Integer nbRecu) {
		this.nbRecu = nbRecu;
	}

	public Integer getNbEnvoye() {
		return nbEnvoye;
	}

	public void setNbEnvoye(Integer nbEnvoye) {
		this.nbEnvoye = nbEnvoye;
	}

	public Integer getNbEnCours() {
		return nbEnCours;
	}

	public void setNbEnCours(Integer nbEnCours) {
		this.nbEnCours = nbEnCours;
	}

	public Integer getNbAnnule() {
		return nbAnnule;
	}

	public void setNbAnnule(Integer nbAnnule) {
		this.nbAnnule = nbAnnule;
	}

}
