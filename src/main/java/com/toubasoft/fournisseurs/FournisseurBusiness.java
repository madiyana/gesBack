package com.toubasoft.fournisseurs;

import java.util.List;

public interface FournisseurBusiness {

	/**
	 * Create a new fournisseur
	 *
	 * @param fournisseurs
	 *            {@link Fournisseurs}
	 * @throws Exception
	 * @
	 */
	public Fournisseurs createFournisseur(Fournisseurs fournisseurs);

	/**
	 * Update Fournissueur
	 * 
	 * @param fournisseurs
	 * @
	 */
	public Fournisseurs updateFournisseurs(Fournisseurs fournisseurs);

	/**
	 * Delete a fournisseurs by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteFournisseur(Long idFournisseur);

	/**
	 * Retrieve all Fournisseurs
	 *
	 * @return List of Fournisseurs entities
	 * @throws Exception
	 * @
	 */
	public List<Fournisseurs> retrieveFournisseurs();

	/**
	 * Retrieve all Fournisseurs with given name
	 *
	 * @return List of Fournisseurs entities
	 * @throws Exception
	 * @
	 */
	public List<Fournisseurs> retrieveFournisseursByName(String name);

	/**
	 * Retrieve {@link Fournisseurs} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Fournisseurs} entities
	 * @throws Exception
	 * @
	 */
	public Fournisseurs retrieveFournisseurById(Long id);

}
