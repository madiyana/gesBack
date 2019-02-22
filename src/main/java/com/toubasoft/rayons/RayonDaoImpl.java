package com.toubasoft.rayons;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class RayonDaoImpl implements RayonDAO {

	@PersistenceContext 
	private EntityManager entityManager;

	public void create(Rayon rayon) {
		entityManager.persist(rayon);
	}

	public void update(Rayon rayons) {
		entityManager.merge(rayons);
	}

	public Rayon findById(Long id) {
		return entityManager.find(Rayon.class, id);
	}

	public void delete(Long id) {
		entityManager.remove(this.findById(id));
	}

	public List<Rayon> findAll() {
		TypedQuery<Rayon> query = entityManager.createQuery("select rayon from Rayon rayon", Rayon.class);
		return query.getResultList();
	}

	public List<Rayon> findByName(String name) {
		TypedQuery<Rayon> typedQuery = entityManager
				.createQuery("select r from Rayon r where upper(r.nom) = :name", Rayon.class)
				.setParameter("name", name.toUpperCase());

		return typedQuery.getResultList();
	}

}
