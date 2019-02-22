package com.toubasoft.articlesCommande;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.toubasoft.articles.Articles;
import com.toubasoft.fournisseurs.Fournisseurs;

@Entity
@Table(name = "ArticlesCommande")
public class ArticlesCommande {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ARTCOM")
	private Long id;

	@Column(name = "NB_COMMANDE")
	private Integer nbreArticleCommande;

	@OneToOne
	private Articles articles;

	@OneToOne
	private Fournisseurs fournisseurs;

	public Integer getNbreArticleCommande() {
		return nbreArticleCommande;
	}

	public void setNbreArticleCommande(Integer nbreArticleCommande) {
		this.nbreArticleCommande = nbreArticleCommande;
	}

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

	/** CONVERT ENTITY TO DTO **/

	public ArticlesCommandeDTO convertEntityToDTO() {
		ArticlesCommandeDTO articlesCommandeDTO = new ArticlesCommandeDTO();
		articlesCommandeDTO.setId(getId());
		if (null != getFournisseurs().getNom()) {
			articlesCommandeDTO.setFournisseur(getFournisseurs().getNom());
		}
		articlesCommandeDTO.setNomArticle(getArticles().getNom());
		articlesCommandeDTO.setNbArticleCommande(getNbreArticleCommande());

		return articlesCommandeDTO;
	}

	/******************************************
	 * METHOS CHECK VALIDATION
	 ***************************************/
	public String checkValidationEntity() {
		return null;
	}

}
