package com.toubasoft.fournisseurs;

import java.util.List;

public interface FournisseursDAO {

	/**
	 * Create an employe
	 * 
	 * @param fournisseur
	 * @return
	 */
	public void create(Fournisseurs fournisseur);

	/**
	 * Upadte an employe
	 * 
	 * @param employe
	 * @return
	 */
	public void update(Fournisseurs fournisseur);

	/**
	 * Fin an employe by given id
	 * 
	 * @param id
	 * @return
	 */
	public Fournisseurs findById(Long id);

	/**
	 * Delete an employe by given id
	 *
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * Retrieve all fournisseur *
	 * 
	 * @return List of fournisseur
	 */
	public List<Fournisseurs> findAll();

	/**
	 * Retrieve fournisseur details for a given name.
	 *
	 * @param id
	 * @return List of fournisseur
	 */
	public List<Fournisseurs> findByName(String name);
}
