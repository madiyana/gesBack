package com.toubasoft.articlesretour;

import java.util.List;

public interface ArticleRetourBusiness {

	/**
	 * Create a new article
	 *
	 * @param article
	 *            s {@link ArticlesRetour}
	 * @throws Exception
	 * @
	 */
	public ArticlesRetour create(ArticlesRetour articlesRetour);

	/**
	 * Update Fournissueur
	 * 
	 * @param article
	 * @
	 */
	public ArticlesRetour update(ArticlesRetour articlesRetour);

	/**
	 * Delete a article by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void delete(Long id);

	/**
	 * Retrieve all Articles
	 *
	 * @return List of Articles entities
	 * @throws Exception
	 * @
	 */
	public List<ArticlesRetourDTO> retrieveArticles();

	/**
	 * Retrieve {@link ArticlesRetour} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link ArticlesRetour} entities
	 * @throws Exception
	 * @
	 */
	public ArticlesRetour retrieveArticleById(Long id);

	/**
	 * Retrieve {@link ArticlesRetour} details for a given name.
	 *
	 * @param anme
	 *            Technical Identifier
	 * @return {@link ArticlesRetour} entities
	 * @throws Exception
	 * @ 1 test
	 */
	public List<ArticlesRetourDTO> retrieveArticleByName(String name);

	public ArticlesRetour retrieveArticlesByReference(String reference);

	public List<ArticlesRetourDTO> retrieveArticleByNameDelay(String name);

}
