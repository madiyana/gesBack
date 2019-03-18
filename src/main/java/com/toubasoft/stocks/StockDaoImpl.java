package com.toubasoft.stocks;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class StockDaoImpl implements StocksDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Stocks stocks) {
		entityManager.persist(stocks);
		entityManager.flush();
	}

	@Override
	public void update(Stocks stocks) {
		entityManager.merge(stocks);
		entityManager.flush();
	}

	@Override
	public Stocks findById(Long id) {
		return entityManager.find(Stocks.class, id);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(findById(id));
		entityManager.flush();
	}

	@Override
	public List<Stocks> findAll() {
		TypedQuery<Stocks> query = entityManager.createQuery("select c from Stocks c", Stocks.class);
		return query.getResultList();
	}

	@Override
	public List<RuptureStockDTO> findRuptureStock() {

		String sql = "SELECT distinct  art.nom_article, art.reference,  cat.nom_categorie, st.quantite,  st.date_entree "
				+ "FROM stocks st " + "LEFT JOIN articles art ON st.articles_id_article = art.id_article "
				+ "LEFT JOIN categories cat ON cat.id_categorie = art.categorie_id_categorie "
				+ "WHERE st.quantite < art.seuil_critique";

		Query query = entityManager.createNativeQuery(sql);

		List<RuptureStockDTO> ruptureStockDTOs = new ArrayList<>();
		List<Object[]> lists = query.getResultList();
		for (Object[] objects : lists) {
			RuptureStockDTO ruptureStockDTO = new RuptureStockDTO();
			ruptureStockDTO.setNomArticle((String) objects[0]);
			ruptureStockDTO.setReference((String) objects[1]);
			ruptureStockDTO.setNomCategorie((String) objects[2]);
			ruptureStockDTO.setQuantite((Integer) objects[3]);
			ruptureStockDTO.setDateEntree((String) objects[4]);

			ruptureStockDTOs.add(ruptureStockDTO);
		}

		return ruptureStockDTOs;
	}
	
	@Override
	public Stocks findByArticle(String reference) {
		TypedQuery<Stocks> query = entityManager.createQuery("select c from Stocks c where c.articles.reference = :reference order by c.dateEntree", Stocks.class);
		query.setParameter("reference", reference);
		return query.getResultList().get(0);
	}
}
