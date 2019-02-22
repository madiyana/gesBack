package com.toubasoft.stocks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.toubasoft.articles.Articles;
import com.toubasoft.commandes.Commandes;
import com.toubasoft.fournisseurs.Fournisseurs;

@Entity
@Table(name = "stocks")
public class Stocks {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_STOCK")
	private Long id;

	@Column(name = "DATE_ENTREE")
	private String dateEntree;

	@Column(name = "QUANTITE")
	private Integer quantite;

	@ManyToOne
	private Articles articles;

	@ManyToOne
	private Fournisseurs fournisseurs;

	@OneToOne
	private Commandes commandes;

	@Column(name = "NB_ART_DEFECT")
	private Integer nbArticlesDefectueux;

	@Column(name = "QTE_REELLE")
	private Integer quantiteReelle;

	@Column(name = "OBSERVATION")
	private String observation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(String dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Integer getNbArticlesDefectueux() {
		return nbArticlesDefectueux;
	}

	public void setNbArticlesDefectueux(Integer nbArticlesDefectueux) {
		this.nbArticlesDefectueux = nbArticlesDefectueux;
	}

	public Integer getQuantiteReelle() {
		return quantiteReelle;
	}

	public void setQuantiteReelle(Integer quantiteReelle) {
		this.quantiteReelle = quantiteReelle;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public Fournisseurs getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(Fournisseurs fournisseurs) {
		this.fournisseurs = fournisseurs;
	}

	public Commandes getCommandes() {
		return commandes;
	}

	public void setCommandes(Commandes commandes) {
		this.commandes = commandes;
	}

}
