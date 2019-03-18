package com.toubasoft.uniteMesure;

import java.util.List;

public interface UniteMesureDAO {

	/**
	 * Create an article
	 * 
	 * @param rayons
	 * @return
	 */
	public void create(UniteMesure rayons);

	/**
	 * Upadte an article
	 * 
	 * @param article
	 * @return
	 */
	public void update(UniteMesure rayons);

	/**
	 * Fin an article by given id
	 * 
	 * @param id
	 * @return
	 */
	public UniteMesure findById(Long id);

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
	public List<UniteMesure> findAll();

	/**
	 * Retrieve rayons details for a given name.
	 *
	 * @param id
	 * @return List of rayons
	 */
	public List<UniteMesure> findByName(String name);
}
