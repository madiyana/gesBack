package com.toubasoft.articlesCommande;


public class ArticlesCommandeDTO {

	private Long id;
	private String nomArticle;
	private Integer nbArticleCommande;
	private String fournisseur;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public Integer getNbArticleCommande() {
		return nbArticleCommande;
	}
	public void setNbArticleCommande(Integer nbArticleCommande) {
		this.nbArticleCommande = nbArticleCommande;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	
}
