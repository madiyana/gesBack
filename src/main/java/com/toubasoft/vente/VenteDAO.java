package com.toubasoft.vente;

import java.util.List;

import com.toubasoft.ligneArticle.LigneArticle;

public interface VenteDAO {

	/**
	 * Create an article
	 * 
	 * @param articles
	 * @return
	 */
	public void create(Ventes ventes);

	public void addArticleVente(LigneArticle ligneArticle);

	public List<Ventes> findAll();

	public List<Object[]> findDetailsVente(Long id);

	public List<Object[]> findVenteCritere(String dateDebut, String dateFin);

	public void deleteLigneArticle(Long idVente, Long idLigne);

	public CaVenteDTO caVente();

	public List<Object[]> popularArticle();

	public Integer caVenteEmploye(Long id);

	public TypeVenteDTO typeVente();

	
}
