package com.toubasoft.uniteMesure;

import java.util.List;

public interface UniteMesureBusiness {

	/**
	 * Create a new rayon
	 *
	 * @param rayon
	 *            s {@link UniteMesure}
	 * @throws Exception
	 * @
	 */
	public UniteMesure createUniteMesure(UniteMesure rayon);

	/**
	 * Update Fournissueur
	 * 
	 * @param rayon
	 * @
	 */
	public UniteMesure updateUniteMesure(UniteMesure rayon);

	/**
	 * Delete a rayon by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteUniteMesure(Long idrayon);

	/**
	 * Retrieve all rayons
	 *
	 * @return List of rayons entities
	 * @throws Exception
	 * @
	 */
	public List<UniteMesureDTO> retrieveUniteMesures();

	/**
	 * Retrieve {@link UniteMesure} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link UniteMesure} entities
	 * @throws Exception
	 * @
	 */
	public UniteMesure retrieveUniteMesureById(Long id);

	/**
	 * Retrieve {@link UniteMesure} details for a given name.
	 *
	 * @param anme
	 *            Technical Identifier
	 * @return {@link UniteMesure} entities
	 * @throws Exception
	 * @
	 */
	public List<UniteMesure> retrieveUniteMesureByName(String name);

}
