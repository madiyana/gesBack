package com.toubasoft.commandes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CommandeBusinessImpl implements CommandeBusiness {

	@Inject
	private CommandeDAO commandeDAO;

	@Override
	public Commandes createCommande(Commandes commande) {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		commande.setDateCommande(format.format(new Date()));
		commandeDAO.create(commande);
		return commande;
	}

	@Override
	public Commandes updateCommandes(Commandes commande) {
		commandeDAO.update(commande);
		return commande;
	}

	@Override
	public void deleteCommande(Long idCommande) {
		commandeDAO.delete(idCommande);
	}

	@Override
	public List<Commandes> retrieveCommandes() {
		return commandeDAO.findAll();
	}

	@Override
	public Commandes retrieveCommandeById(Long id) {
		return commandeDAO.findById(id);
	}

	@Override
	public List<Commandes> retrieveCommandesStatus(String status) {
		return commandeDAO.findByState(status);
	}

	/************** PRIVATE METHOD *****/

}
