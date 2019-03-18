package com.toubasoft.articlesCommande;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ArticleCommandeDaoImpl implements ArticleCommandeDAO {

	@PersistenceContext 
	private EntityManager entityManager;

	public void create(ArticlesCommande article) {
		entityManager.persist(article);
	}

	public void update(ArticlesCommande articles) {
		entityManager.merge(articles);
	}

	public ArticlesCommande findById(Long id) {
		return entityManager.find(ArticlesCommande.class, id);
	}

	public void delete(Long id) {
		entityManager.remove(this.findById(id));
	}

	public List<ArticlesCommande> findAll() {
		TypedQuery<ArticlesCommande> query = entityManager.createQuery("select article from ArticlesCommande article", ArticlesCommande.class);
		return query.getResultList();
	}

	@Override
	public List<ArticlesCommande> findByNameArticleOrFournisseur(String article, String fournisseur) {
		return null;
	}
	 
}
