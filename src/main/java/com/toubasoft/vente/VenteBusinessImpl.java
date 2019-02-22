package com.toubasoft.vente;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.toubasoft.ligneArticle.LigneArticle;
import com.toubasoft.ligneArticle.LigneArticleDTO;

@Stateless
public class VenteBusinessImpl implements VenteBusiness {

	@Inject
	private VenteDAO venteDAO;

	@Override
	public Ventes create(Ventes vente) {
		vente.setDateVente(new Date());
		venteDAO.create(vente);
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
}
