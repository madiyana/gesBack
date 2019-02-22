package com.toubasoft.articles;

import java.util.List;

public interface ArticleDAO {

	/**
	 * Create an article
	 * 
	 * @param articles
	 * @return
	 */
	public void create(Articles articles);

	/**
	 * Upadte an article
	 * 
	 * @param article
	 * @return
	 */
	public void update(Articles articles);

	/**
	 * Fin an article by given id
	 * 
	 * @param id
	 * @return
	 */
	public Articles findById(Long id);

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
	public List<Articles> findAll();

	/**
	 * Retrieve articles details for a given name.
	 *
	 * @param id
	 * @return List of articles
	 */
	public List<Articles> findByName(String name);

	public Articles findByReference(String reference);
	
	public List<Articles> findByNameDelaySearch(String name);
}
