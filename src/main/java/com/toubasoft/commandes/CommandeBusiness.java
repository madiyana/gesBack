package com.toubasoft.commandes;

import java.util.List;

import com.toubasoft.dashboard.DBCommandeDTO;

public interface CommandeBusiness {

	/**
	 * Create a new commande
	 *
	 * @param Commande
	 *            s {@link commande}
	 * @throws Exception
	 * @
	 */
	public Commandes createCommande(Commandes commande);

	/**
	 * Update Fournissueur
	 * 
	 * @param Commande
	 * @
	 */
	public Commandes updateCommandes(Commandes commande);

	/**
	 * Delete a Commande by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteCommande(Long idCommande);

	/**
	 * Retrieve all Commandes
	 *
	 * @return List of Commandes entities
	 * @throws Exception
	 * @
	 */
	public List<Commandes> retrieveCommandes();

	/**
	 * Retrieve {@link Commandes} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Commandes} entities
	 * @throws Exception
	 * @
	 */
	public Commandes retrieveCommandeById(Long id);

	public List<Commandes> retrieveCommandesStatus(String status);

	public DBCommandeDTO retrieveStatusCommandes();
}
