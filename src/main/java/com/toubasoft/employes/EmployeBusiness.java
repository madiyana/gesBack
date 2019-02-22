package com.toubasoft.employes;

import java.util.List;
import java.util.Map;

public interface EmployeBusiness {

	/**
	 * Create a new employe
	 *
	 * @param employe
	 *            {@link Employes}
	 * @throws Exception
	 * @
	 */
	public Employes createEmploye(Employes employe);

	/**
	 * Update Fournissueur
	 * 
	 * @param employe
	 * @
	 */
	public Employes updateEmployes(Employes employe);

	/**
	 * Delete a employe by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteEmploye(Long idEmploye);

	/**
	 * Retrieve all Employes
	 *
	 * @return List of Employes entities
	 * @throws Exception
	 * @
	 */
	public List<Employes> retrieveEmployes();

	/**
	 * Retrieve {@link Employes} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Employes} entities
	 * @throws Exception
	 * @
	 */
	public Employes retrieveEmployeById(Long id);

	/**
	 * Retrieve {@link Employes} details for a given name.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Employes} entities
	 * @throws Exception
	 * @
	 */
	public List<Employes> retrieveEmployeByName(String name);

	/**
	 * Authenticate
	 *
	 * @param Technical
	 *            Identifier
	 * @return {@link Employes} entities
	 * @throws Exception
	 * @
	 */
	public Map<String, Employes> authenticate(String username, String password);

	/**
	 * Logout
	 *
	 * @param Technical
	 *            Identifier
	 * @return {@link Employes} entities
	 * @throws Exception
	 * @
	 */
	public void logout(Employes employes);

	/**
	 * Cange pasword firs connexion
	 *
	 * @param Technical
	 *            Identifier
	 * @return {@link Employes} entities
	 * @throws Exception
	 * @
	 */
	public Employes updatePassword(Employes employes);
}
