package com.toubasoft.userToken;

import com.toubasoft.categories.Categories;
import com.toubasoft.references.UniteMesure;

public class ArticlesDTO {

	private Long id;
	private String nom;
	private Integer seuilCritique;
	private boolean tva;
	private Long prixAchat;
	private Long prixVenteHT;
	private Long prixVenteTTC;
	private String reference;
	private Long marge;
	private UniteMesure uniteMesure;
	private Categories categorie;

	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isTva() {
		return tva;
	}

	public void setTva(boolean tva) {
		this.tva = tva;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getSeuilCritique() {
		return seuilCritique;
	}

	public void setSeuilCritique(Integer seuilCritique) {
		this.seuilCritique = seuilCritique;
	}

	public Long getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(Long prixAchat) {
		this.prixAchat = prixAchat;
	}

	public Long getPrixVenteHT() {
		return prixVenteHT;
	}

	public void setPrixVenteHT(Long prixVenteHT) {
		this.prixVenteHT = prixVenteHT;
	}

	public Long getPrixVenteTTC() {
		return prixVenteTTC;
	}

	public void setPrixVenteTTC(Long prixVenteTTC) {
		this.prixVenteTTC = prixVenteTTC;
	}

	public Long getMarge() {
		return marge;
	}

	public void setMarge(Long marge) {
		this.marge = marge;
	}

	public UniteMesure getUniteMesure() {
		return uniteMesure;
	}

	public void setUniteMesure(UniteMesure uniteMesure) {
		this.uniteMesure = uniteMesure;
	}

	public Categories getCategorie() {
		return categorie;
	}

	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}

}
