package com.toubasoft.employes;

import java.util.List;

public interface EmployeDAO {

	/**
	 * Create an employe
	 * 
	 * @param employes
	 * @return
	 */
	public void create(Employes employes);

	/**
	 * Upadte an employe
	 * 
	 * @param employe
	 * @return
	 */
	public void update(Employes employes);

	/**
	 * Fin an employe by given id
	 * 
	 * @param id
	 * @return
	 */
	public Employes findById(Long id);

	/**
	 * Delete an employe by given id
	 *
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * Retrieve all employes *
	 * 
	 * @return List of employes
	 */
	public List<Employes> findAll();

	/**
	 * Retrieve employes details for a given name.
	 *
	 * @param id
	 * @return List of employes
	 */
	public List<Employes> findByName(String name);
	
	/**
	 * Retrieve employes details for a given username / password.
	 *
	 * @param username, password
	 * @return Employes
	 */
	public Employes authenticate(String username, String password);
	
	
	/**
	 * Check if username generate not exist
	 * @param username
	 * @return
	 */
	public Employes findUsername(String username);
	
	
}
