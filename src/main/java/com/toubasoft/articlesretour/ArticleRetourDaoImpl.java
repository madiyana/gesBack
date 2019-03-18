package com.toubasoft.articlesretour;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class ArticleRetourDaoImpl implements ArticleRetourDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(ArticlesRetour article) {
		entityManager.persist(article);
	}

	public void update(ArticlesRetour articles) {
		entityManager.merge(articles);
	}

	public ArticlesRetour findById(Long id) {
		return entityManager.find(ArticlesRetour.class, id);
	}

	public void delete(Long id) {
		entityManager.remove(this.findById(id));
	}

	public List<ArticlesRetour> findAll() {
		TypedQuery<ArticlesRetour> typedQuery = entityManager
				.createQuery("select art from ArticlesRetour art", ArticlesRetour.class);

		return typedQuery.getResultList();
	}

	public List<ArticlesRetour> findByName(String name) {
		TypedQuery<ArticlesRetour> typedQuery = entityManager
				.createQuery("select art from Articles art where upper(art.nom) = :name", ArticlesRetour.class)
				.setParameter("name", name.toUpperCase());

		return typedQuery.getResultList();
	}

	public List<ArticlesRetour> findByNameDelaySearch(String name) {
		TypedQuery<ArticlesRetour> typedQuery = entityManager
				.createQuery("select art from Articles art where upper(art.nom) LIKE :name", ArticlesRetour.class)
				.setParameter("name", "%" + name.toUpperCase() + "%");

		return typedQuery.getResultList();
	}

	@Override
	public ArticlesRetour findByReference(String reference) {
		TypedQuery<ArticlesRetour> typedQuery = entityManager
				.createQuery("select art from Articles art where art.reference = :reference", ArticlesRetour.class)
				.setParameter("reference", reference);

		return typedQuery.getResultList().get(0);
	}

}
