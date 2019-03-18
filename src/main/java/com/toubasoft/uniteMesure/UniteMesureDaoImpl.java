package com.toubasoft.uniteMesure;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class UniteMesureDaoImpl implements UniteMesureDAO {

	@PersistenceContext 
	private EntityManager entityManager;

	public void create(UniteMesure rayon) {
		entityManager.persist(rayon);
	}

	public void update(UniteMesure rayons) {
		entityManager.merge(rayons);
	}

	public UniteMesure findById(Long id) {
		return entityManager.find(UniteMesure.class, id);
	}

	public void delete(Long id) {
		entityManager.remove(this.findById(id));
	}

	@Override
	public List<UniteMesure> findAll() {
		TypedQuery<UniteMesure> query = entityManager.createQuery("select uniteMesure from UniteMesure uniteMesure where uniteMesure.actif = true", UniteMesure.class);
		return query.getResultList();
	}

	public List<UniteMesure> findByName(String name) {
		TypedQuery<UniteMesure> typedQuery = entityManager
				.createQuery("select r from UniteMesure r where upper(r.nom) = :name", UniteMesure.class)
				.setParameter("name", name.toUpperCase());

		return typedQuery.getResultList();
	}

}
