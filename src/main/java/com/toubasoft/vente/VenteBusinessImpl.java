package com.toubasoft.vente;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.toubasoft.dashboard.DBArticleDTO;
import com.toubasoft.ligneArticle.LigneArticle;
import com.toubasoft.ligneArticle.LigneArticleDTO;
import com.toubasoft.stocks.Stocks;
import com.toubasoft.stocks.StocksDAO;

@Stateless
public class VenteBusinessImpl implements VenteBusiness {

	@Inject
	private VenteDAO venteDAO;

	@Inject
	private StocksDAO stocksDAO;

	@Override
	public Ventes create(Ventes vente) {
		vente.setDateVente(new Date());
		venteDAO.create(vente);

		// Mise a jour du stock en retirant le nombre des articles vendus
		vente.getLigneArticle().forEach(venteCurrent -> {
			Stocks stocks = stocksDAO.findByArticle(venteCurrent.getReference());

			stocks.setQuantiteReelle(stocks.getQuantite() - venteCurrent.getQuantite());
			stocksDAO.update(stocks);
		});
		return vente;
	}

	@Override
	public LigneArticle addArticle(LigneArticle ligneArticle) {
		ligneArticle.setMontantTotal(ligneArticle.getPrix() * ligneArticle.getQuantite());
		venteDAO.addArticleVente(ligneArticle);
		return ligneArticle;
	}

	@Override
	public List<VenteDTO> retrieveVentes() {
		List<Ventes> list = venteDAO.findAll();
		List<VenteDTO> listDTO = new ArrayList<>();
		for (Ventes ventes : list) {
			VenteDTO articlesDTO = ventes.convertEntityToDTO();
			listDTO.add(articlesDTO);
		}

		return listDTO;
	}

	@Override
	public List<LigneArticleDTO> retrieveDetailVentes(Long idVente) {
		List<Object[]> list = venteDAO.findDetailsVente(idVente);
		List<LigneArticleDTO> ligneArticleDTOs = new ArrayList<>();
		for (Object[] objects : list) {
			LigneArticleDTO ligneArticleDTO = new LigneArticleDTO();

			BigInteger bigInteger = (BigInteger) objects[5];
			ligneArticleDTO.setId(bigInteger.longValue());
			ligneArticleDTO.setNom((String) objects[0]);
			ligneArticleDTO.setReference((String) objects[1]);
			ligneArticleDTO.setQuantite((Integer) objects[2]);
			ligneArticleDTO.setPrix((Integer) objects[3]);
			ligneArticleDTO.setMontantTotal((Integer) objects[4]);

			ligneArticleDTOs.add(ligneArticleDTO);

		}
		return ligneArticleDTOs;
	}

	@Override
	public List<VenteDTO> retrieveVentesByCritere(String dateDebut, String dateFin) {
		List<Object[]> list = venteDAO.findVenteCritere(dateDebut, dateFin);
		List<VenteDTO> venteDTOs = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		if (!list.isEmpty()) {
			for (Object[] objects : list) {
				VenteDTO venteDTO = new VenteDTO();
				BigInteger bigInteger = (BigInteger) objects[0];
				venteDTO.setId(bigInteger.longValue());
				String dateParse = format.format((Date) objects[1]);
				venteDTO.setDateVente(dateParse);
				venteDTO.setEmploye((String) objects[2]);
				venteDTO.setMontantTotal(((Integer) objects[3]).longValue());

				venteDTOs.add(venteDTO);

			}
		}
		return venteDTOs;
	}

	@Override
	public void deleteLigneArticle(Long idVente, Long idLigne) {
		venteDAO.deleteLigneArticle(idVente, idLigne);
	}

	@Override
	public CaVenteDTO retrieveCaVentes() {
		return venteDAO.caVente();
	}

	@Override
	public List<DBArticleDTO> popularArticle() {
		List<Object[]> ojects = venteDAO.popularArticle();
		List<DBArticleDTO> dbArticleDTOs = new ArrayList<>();
		if (!ojects.isEmpty()) {
			for (Object[] object : ojects) {
				DBArticleDTO dbArticleDTO = new DBArticleDTO();
				BigInteger bigInteger = (BigInteger) object[0];
				dbArticleDTO.setNbArticle(bigInteger.intValue());
				dbArticleDTO.setNom((String) object[1]);

				dbArticleDTOs.add(dbArticleDTO);

			}
		}
		return dbArticleDTOs;
	}
	
	@Override
	public Integer caVenteEmploye(Long id) {
		return venteDAO.caVenteEmploye(id);
	}
	
	@Override
	public TypeVenteDTO retrieveTypeVentes() {
		// TODO Auto-generated method stub
		return venteDAO.typeVente();
	}
}
