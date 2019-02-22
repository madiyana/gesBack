package com.toubasoft.employes;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EmployeDaoImpl implements EmployeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Employes employes) {
		entityManager.persist(employes);
		entityManager.flush();
	}

	@Override
	public void update(Employes employes) {
		entityManager.merge(employes);
		entityManager.flush();
	}

	@Override
	public Employes findById(Long id) {
		return entityManager.find(Employes.class, id);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(findById(id));
		entityManager.flush();
	}

	@Override
	public List<Employes> findAll() {
		TypedQuery<Employes> query = entityManager.createQuery("select c from Employes c ORDER BY c.nom",
				Employes.class);
		return query.getResultList();
	}

	@Override
	public List<Employes> findByName(String name) {
		TypedQuery<Employes> typedQuery = entityManager
				.createQuery("select c from Employes c where upper(c.nom) LIKE :nomEmploye", Employes.class)
				.setParameter("nomEmploye", "'%" + name.toUpperCase() + "%'");

		return typedQuery.getResultList();
	}

	@Override
	public Employes authenticate(String identifiant, String motDePasse) {
		TypedQuery<Employes> typedQuery = entityManager.createQuery(
				"select c from Employes c where c.identifiant = :identifiant and c.motDePasse = :motDePasse",
				Employes.class).setParameter("identifiant", identifiant).setParameter("motDePasse", motDePasse);
		// typedQuery.setMaxResults(1);

		List<Employes> listEmployes = typedQuery.getResultList();
		if (listEmployes == null || listEmployes.isEmpty()) {
			return null;
		}
		return listEmployes.get(0);
	}

	@Override
	public Employes findUsername(String identifiant) {
		TypedQuery<Employes> typedQuery = entityManager
				.createQuery("select c from Employes c where lower(c.identifiant) = :identifiant", Employes.class)
				.setParameter("identifiant", identifiant.toLowerCase());

		typedQuery.setMaxResults(1);

		List<Employes> listEmployes = typedQuery.getResultList();
		if (listEmployes == null || listEmployes.isEmpty()) {
			return null;
		}
		return listEmployes.get(0);
	}

}
