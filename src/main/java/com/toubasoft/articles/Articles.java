package com.toubasoft.articles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.toubasoft.categories.Categories;
import com.toubasoft.rayons.Rayon;
import com.toubasoft.uniteMesure.UniteMesure;

@Entity
@Table(name = "Articles")
public class Articles {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ARTICLE")
	private Long id;

	@Column(name = "NOM_ARTICLE")
	private String nom;

	@Column(name = "REFERENCE")
	private String reference;

	@Column(name = "SEUIL_CRITIQUE")
	private Integer seuilCritique;

	@Column(name = "TVA")
	private boolean tva;

	@Column(name = "PRIX_ACHAT")
	private Long prixAchat;

	@Column(name = "PRIX_VENTE_HT")
	private Long prixVenteHT;

	@Column(name = "PRIX_VENTE_TTC")
	private Long prixVenteTTC;

	@Column(name = "MARGE")
	private Long marge;

	@ManyToOne
	private UniteMesure uniteMesure;

	@ManyToOne
	private Categories categorie;

	@ManyToOne
	private Rayon rayon;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Categories getCategorie() {
		return categorie;
	}

	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}

	public Rayon getRayon() {
		return rayon;
	}

	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}

	public Integer getSeuilCritique() {
		return seuilCritique;
	}

	public void setSeuilCritique(Integer seuilCritique) {
		this.seuilCritique = seuilCritique;
	}

	public boolean isTva() {
		return tva;
	}

	public void setTva(boolean tva) {
		this.tva = tva;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
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

	/** CONVERT ENTITY TO DTO **/

	public ArticlesDTO convertEntityToDTO() {
		ArticlesDTO articlesDTO = new ArticlesDTO();
		articlesDTO.setId(getId());
		articlesDTO.setMarge(getMarge());
		articlesDTO.setReference(reference);
		articlesDTO.setNom(getNom());
		articlesDTO.setPrixAchat(getPrixAchat());
		articlesDTO.setPrixVenteHT(getPrixVenteHT());
		articlesDTO.setPrixVenteTTC(getPrixVenteTTC());
		articlesDTO.setSeuilCritique(getSeuilCritique());
		articlesDTO.setTva(isTva());
		articlesDTO.setCategorie(getCategorie());
		articlesDTO.setUniteMesure(getUniteMesure());
		articlesDTO.setRayon(rayon);

		return articlesDTO;
	}

	/******************************************
	 * METHOS CHECK VALIDATION
	 ***************************************/
	public String checkValidationEntity() {
		return null;
	}

}
