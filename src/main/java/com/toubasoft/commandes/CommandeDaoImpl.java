package com.toubasoft.commandes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CommandeDaoImpl implements CommandeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Commandes commande) {
		entityManager.persist(commande);
	}

	@Override
	public void update(Commandes commandes) {
		entityManager.merge(commandes);
	}

	@Override
	public Commandes findById(Long id) {
		return entityManager.find(Commandes.class, id);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(this.findById(id));
	}

	@Override
	public List<Commandes> findAll() {
		TypedQuery<Commandes> query = entityManager.createQuery("select commande from Commandes commande",
				Commandes.class);
		return query.getResultList();
	}

	@Override
	public List<Commandes> findByState(String etat) {
		TypedQuery<Commandes> query = entityManager
				.createQuery("select commande from Commandes commande where commande.etatCommande = :etatCommande",
						Commandes.class)
				.setParameter("etatCommande", etat);
		return query.getResultList();
	}

}
