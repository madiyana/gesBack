package com.toubasoft.articlesretour;

import java.util.List;

public interface ArticleRetourDAO {

	/**
	 * Create an article
	 * 
	 * @param articles
	 * @return
	 */
	public void create(ArticlesRetour articlesRetour);

	/**
	 * Upadte an article
	 * 
	 * @param article
	 * @return
	 */
	public void update(ArticlesRetour articlesRetour);

	/**
	 * Fin an article by given id
	 * 
	 * @param id
	 * @return
	 */
	public ArticlesRetour findById(Long id);

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
	public List<ArticlesRetour> findAll();

	/**
	 * Retrieve articles details for a given name.
	 *
	 * @param id
	 * @return List of articles
	 */
	public List<ArticlesRetour> findByName(String name);

	public ArticlesRetour findByReference(String reference);
	
	public List<ArticlesRetour> findByNameDelaySearch(String name);
}
