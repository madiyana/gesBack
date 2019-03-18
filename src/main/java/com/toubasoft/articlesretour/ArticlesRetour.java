package com.toubasoft.articlesretour;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.toubasoft.articles.Articles;
import com.toubasoft.commandes.Commandes;
import com.toubasoft.fournisseurs.Fournisseurs;

@Entity
@Table(name = "ArticlesRetour")
public class ArticlesRetour {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ARTICLE_RETOUR")
	private Long id;

	@ManyToOne
	private Articles articles;

	@ManyToOne
	private Fournisseurs fournisseurs;

	@ManyToOne
	private Commandes commandes;

	@Column(name = "QTE_RETOUR")
	private Integer quantite;

	@Column(name = "COMMENT")
	private String commentaire;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Commandes getCommandes() {
		return commandes;
	}

	public void setCommandes(Commandes commandes) {
		this.commandes = commandes;
	}

	
	public ArticlesRetourDTO convertEntityToDTO() {
		ArticlesRetourDTO articlesDTO = new ArticlesRetourDTO();
		articlesDTO.setId(getId());
		articlesDTO.setReference(articles.getReference());
		articlesDTO.setNomArticle(articles.getNom());
		articlesDTO.setQuantite(quantite);
		articlesDTO.setCommande(commandes.getId());
		articlesDTO.setFournisseur(fournisseurs.getNom());
		articlesDTO.setCommentaire(commentaire);
		articlesDTO.setCategorie(articles.getCategorie().getNom());

		return articlesDTO;
	}
}
