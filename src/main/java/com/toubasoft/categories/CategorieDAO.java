package com.toubasoft.categories;

import java.util.List;

public interface CategorieDAO {

	/**
	 * Create an categorie
	 * 
	 * @param categories
	 * @return
	 */
	public void create(Categories categories);

	/**
	 * Upadte a categorie
	 * 
	 * @param categories
	 * @return
	 */
	public void update(Categories categories);

	/**
	 * Fin an categorie by given id
	 * 
	 * @param id
	 * @return
	 */
	public Categories findById(Long id);

	/**
	 * Delete an categorie by given id
	 *
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * Retrieve all categories *
	 * 
	 * @return List of categories
	 */
	public List<Categories> findAll();

	/**
	 * Retrieve categories details for a given name.
	 *
	 * @param id
	 * @return List of categories
	 */
	public List<Categories> findByName(String name);

	public List<Categories> findByNameDelay(String nom);
}
