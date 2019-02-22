package com.toubasoft.fournisseurs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class FournisseurDaoImpl implements FournisseursDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Fournisseurs fournisseurs) {
		entityManager.persist(fournisseurs);
		entityManager.flush();
	}

	@Override
	public void update(Fournisseurs fournisseurs) {
		entityManager.merge(fournisseurs);
		entityManager.flush();
	}

	@Override
	public Fournisseurs findById(Long id) {
		return entityManager.find(Fournisseurs.class, id);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(findById(id));
		entityManager.flush();
	}

	@Override
	public List<Fournisseurs> findAll() {
		TypedQuery<Fournisseurs> query = entityManager.createQuery("select c from Fournisseurs c", Fournisseurs.class);
		return query.getResultList();
	}

	@Override
	public List<Fournisseurs> findByName(String name) {
		TypedQuery<Fournisseurs> typedQuery = entityManager
				.createQuery("select c from Fournisseurs c where upper(c.nom) = :nomFourn", Fournisseurs.class)
				.setParameter("nomFourn", name.toUpperCase());

		return typedQuery.getResultList();
	}

}
