package com.toubasoft.articles;

import java.util.List;

public interface ArticleBusiness {

	/**
	 * Create a new article
	 *
	 * @param article
	 *            s {@link Articles}
	 * @throws Exception
	 * @
	 */
	public Articles createArticle(Articles article);

	/**
	 * Update Fournissueur
	 * 
	 * @param article
	 * @
	 */
	public Articles updateArticles(Articles article);

	/**
	 * Delete a article by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteArticle(Long idArticle);

	/**
	 * Retrieve all Articles
	 *
	 * @return List of Articles entities
	 * @throws Exception
	 * @
	 */
	public List<ArticlesDTO> retrieveArticles();

	/**
	 * Retrieve {@link Articles} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Articles} entities
	 * @throws Exception
	 * @
	 */
	public Articles retrieveArticleById(Long id);

	/**
	 * Retrieve {@link Articles} details for a given name.
	 *
	 * @param anme
	 *            Technical Identifier
	 * @return {@link Articles} entities
	 * @throws Exception
	 * @ 1 test
	 */
	public List<ArticlesDTO> retrieveArticleByName(String name);

	public Articles retrieveArticlesByReference(String reference);

	public List<ArticlesDTO> retrieveArticleByNameDelay(String name);

}
