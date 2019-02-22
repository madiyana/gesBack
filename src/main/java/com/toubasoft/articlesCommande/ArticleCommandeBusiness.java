package com.toubasoft.articlesCommande;

import java.util.List;

public interface ArticleCommandeBusiness {

	/**
	 * Create a new article
	 *
	 * @param article
	 *            s {@link ArticlesCommande}
	 * @throws Exception
	 * @
	 */
	public ArticlesCommande createArticle(ArticlesCommande articlesCommande);

	/**
	 * Update Fournissueur
	 * 
	 * @param article
	 * @
	 */
	public ArticlesCommande updateArticles(ArticlesCommande articlesCommande);

	/**
	 * Delete a article by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteArticleCommande(Long id);

	/**
	 * Retrieve all Articles
	 *
	 * @return List of Articles entities
	 * @throws Exception
	 * @
	 */
	public List<ArticlesCommandeDTO> retrieveArticlesCommande();

	/**
	 * Retrieve {@link ArticlesCommande} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link ArticlesCommande} entities
	 * @throws Exception
	 * @
	 */
	public ArticlesCommande retrieveArticleCommandeById(Long id);

	/**
	 * Retrieve {@link ArticlesCommande} details for a given name.
	 *
	 * @param anme
	 *            Technical Identifier
	 * @return {@link ArticlesCommande} entities
	 * @throws Exception
	 * @
	 */
	public List<ArticlesCommande> retrieveArticleCommandeByName(String article, String fournisseur);

}
