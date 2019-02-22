package com.toubasoft.vente;

public class VenteDTO {

	private Long id;
	private String dateVente;
	private String employe;
	private Long montantTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getDateVente() {
		return dateVente;
	}

	public void setDateVente(String dateVente) {
		this.dateVente = dateVente;
	}

	public String getEmploye() {
		return employe;
	}

	public void setEmploye(String employe) {
		this.employe = employe;
	}

	public Long getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(Long montantTotal) {
		this.montantTotal = montantTotal;
	}

}
