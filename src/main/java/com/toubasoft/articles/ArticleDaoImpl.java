package com.toubasoft.articles;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ArticleDaoImpl implements ArticleDAO {

	@PersistenceContext 
	private EntityManager entityManager;

	public void create(Articles article) {
		entityManager.persist(article);
	}

	public void update(Articles articles) {
		entityManager.merge(articles);
	}

	public Articles findById(Long id) {
		return entityManager.find(Articles.class, id);
	}

	public void delete(Long id) {
		entityManager.remove(this.findById(id));
	}

	public List<Articles> findAll() {
		TypedQuery<Articles> query = entityManager.createQuery("select article from Articles article", Articles.class);
		return query.getResultList();
	}

	public List<Articles> findByName(String name) {
		TypedQuery<Articles> typedQuery = entityManager
				.createQuery("select art from Articles art where upper(art.nom) = :name",
						Articles.class)
				.setParameter("name", name.toUpperCase());

		return typedQuery.getResultList();
	}
	
	
	public List<Articles> findByNameDelaySearch(String name) {
		TypedQuery<Articles> typedQuery = entityManager
				.createQuery("select art from Articles art where upper(art.nom) LIKE :name",
						Articles.class)
				.setParameter("name", "%"+name.toUpperCase()+"%");

		return typedQuery.getResultList();
	}
	
	@Override
	public Articles findByReference(String reference) {
		TypedQuery<Articles> typedQuery = entityManager
				.createQuery("select art from Articles art where art.reference = :reference",
						Articles.class)
				.setParameter("reference", reference);

		return typedQuery.getResultList().get(0);
	}

}
