package com.toubasoft.categories;

import java.util.List;

public interface CategorieBusiness {

	/**
	 * Create a new categories
	 *
	 * @param categories
	 *            {@link Categories}
	 * @throws Exception
	 * @
	 */
	public Categories createCategories(Categories categories);

	/**
	 * Update Categories
	 * 
	 * @param categories
	 * @
	 */
	public Categories updateCategories(Categories categories);

	/**
	 * Delete a categories by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteCategories(Long idCategories);

	/**
	 * Retrieve all Categories
	 *
	 * @return List of Categories entities
	 * @throws Exception
	 * @
	 */
	public List<Categories> retrieveCategories();

	/**
	 * Retrieve {@link Categories} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Categories} entities
	 * @throws Exception
	 * @
	 */
	public Categories retrieveCategoriesById(Long id);

	/**
	 * Retrieve {@link Categories} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Categories} entities
	 * @throws Exception
	 * @
	 */
	public List<Categories> retrieveCategoriesByName(String name);

	public List<Categories> retrieveCategoriesByNameDelay(String nom);
}
