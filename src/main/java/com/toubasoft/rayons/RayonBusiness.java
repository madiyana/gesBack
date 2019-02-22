package com.toubasoft.rayons;

import java.util.List;

public interface RayonBusiness {

	/**
	 * Create a new rayon
	 *
	 * @param rayon
	 *            s {@link Rayon}
	 * @throws Exception
	 * @
	 */
	public Rayon createRayon(Rayon rayon);

	/**
	 * Update Fournissueur
	 * 
	 * @param rayon
	 * @
	 */
	public Rayon updateRayon(Rayon rayon);

	/**
	 * Delete a rayon by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteRayon(Long idrayon);

	/**
	 * Retrieve all rayons
	 *
	 * @return List of rayons entities
	 * @throws Exception
	 * @
	 */
	public List<RayonDTO> retrieveRayons();

	/**
	 * Retrieve {@link Rayon} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Rayon} entities
	 * @throws Exception
	 * @
	 */
	public Rayon retrieveRayonById(Long id);

	/**
	 * Retrieve {@link Rayon} details for a given name.
	 *
	 * @param anme
	 *            Technical Identifier
	 * @return {@link Rayon} entities
	 * @throws Exception
	 * @
	 */
	public List<Rayon> retrieveRayonByName(String name);

}
