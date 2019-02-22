package com.toubasoft.articlesCommande;

import java.util.List;

public interface ArticleCommandeDAO {

	/**
	 * Create an article
	 * 
	 * @param articles
	 * @return
	 */
	public void create(ArticlesCommande articlesCommande);

	/**
	 * Upadte an article
	 * 
	 * @param article
	 * @return
	 */
	public void update(ArticlesCommande articlesCommande);

	/**
	 * Fin an article by given id
	 * 
	 * @param id
	 * @return
	 */
	public ArticlesCommande findById(Long id);

	/**
	 * Delete an article by given id
	 *
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * Retrieve all articles *
	 * 
	 * @return List of articles
	 */
	public List<ArticlesCommande> findAll();

	/**
	 * Retrieve articles details for a given name.
	 *
	 * @param id
	 * @return List of articles
	 */
	public List<ArticlesCommande> findByNameArticleOrFournisseur(String article, String fournisseur);
}
