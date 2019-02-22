package com.toubasoft.categories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CategorieDaoImpl implements CategorieDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Categories categories) {
		entityManager.persist(categories);
		entityManager.flush();
	}

	@Override
	public void update(Categories categories) {
		entityManager.merge(categories);
		entityManager.flush();
	}

	@Override
	public Categories findById(Long id) {
		return entityManager.find(Categories.class, id);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(findById(id));
	}

	@Override
	public List<Categories> findAll() {
		TypedQuery<Categories> query = entityManager.createQuery("select c from Categories c ORDER BY c.nom",
				Categories.class);
		return query.getResultList();
	}

	@Override
	public List<Categories> findByName(String name) {
		TypedQuery<Categories> typedQuery = entityManager
				.createQuery("select c from Categories c where upper(c.nom) = :nomCategorie", Categories.class)
				.setParameter("nomCategorie", name.toUpperCase());

		return typedQuery.getResultList();
	}

	@Override
	public List<Categories> findByNameDelay(String nom) {
		TypedQuery<Categories> typedQuery = entityManager
				.createQuery("select c from Categories c where upper(c.nom) LIKE :nomCategorie", Categories.class)
				.setParameter("nomCategorie", "%" + nom.toUpperCase() + "%");

		return typedQuery.getResultList();
	}

}
