package com.toubasoft.commandes;

import java.util.List;

import com.toubasoft.dashboard.DBCommandeDTO;

public interface CommandeDAO {

	/**
	 * Create an article
	 * 
	 * @param articles
	 * @return
	 */
	public void create(Commandes articles);

	/**
	 * Upadte an article
	 * 
	 * @param article
	 * @return
	 */
	public void update(Commandes articles);

	/**
	 * Fin an article by given id
	 * 
	 * @param id
	 * @return
	 */
	public Commandes findById(Long id);
	
	/**
	 * Fin an article by given etat
	 * 
	 * @param etat
	 * @return
	 */
	public List<Commandes> findByState(String etat);


	/**
	 * Delete an article by given id
	 *
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * Retrieve all articles *
	 * 
	 * @return List of articles
	 */
	public List<Commandes> findAll();

	public DBCommandeDTO findStatus();
}
