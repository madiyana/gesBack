package com.toubasoft.rayons;

import java.util.List;

public interface RayonDAO {

	/**
	 * Create an article
	 * 
	 * @param rayons
	 * @return
	 */
	public void create(Rayon rayons);

	/**
	 * Upadte an article
	 * 
	 * @param article
	 * @return
	 */
	public void update(Rayon rayons);

	/**
	 * Fin an article by given id
	 * 
	 * @param id
	 * @return
	 */
	public Rayon findById(Long id);

	/**
	 * Delete an article by given id
	 *
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * Retrieve all rayons *
	 * 
	 * @return List of rayons
	 */
	public List<Rayon> findAll();

	/**
	 * Retrieve rayons details for a given name.
	 *
	 * @param id
	 * @return List of rayons
	 */
	public List<Rayon> findByName(String name);
}
