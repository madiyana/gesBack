package com.toubasoft.commandes;

public enum EtatCommande {

	RECEPT("RECEPT"),
	ENVOYE("ENVOYE"),
	ANNULE("ANNULE"),
	EN_COURS("EN_COURS");

	private String label;

	private EtatCommande(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
	
}
