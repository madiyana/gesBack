package com.toubasoft.vente;

import java.util.List;

import com.toubasoft.dashboard.DBArticleDTO;
import com.toubasoft.ligneArticle.LigneArticle;
import com.toubasoft.ligneArticle.LigneArticleDTO;

public interface VenteBusiness {

	/**
	 * Create a new article
	 *
	 * @param article s
	 *            {@link Ventes}
	 * @throws Exception
	 * @
	 */
	public Ventes create(Ventes ventes) ;
	
	public LigneArticle addArticle(LigneArticle ligneArticle);

	public List<VenteDTO> retrieveVentes();

	public List<LigneArticleDTO> retrieveDetailVentes(Long idVente);

	public List<VenteDTO> retrieveVentesByCritere(String dateDebut, String dateFin);

	public void deleteLigneArticle(Long idVente, Long idLigne);

	public CaVenteDTO retrieveCaVentes();

	public List<DBArticleDTO> popularArticle();

	public Integer caVenteEmploye(Long id);

	public TypeVenteDTO retrieveTypeVentes();

}
