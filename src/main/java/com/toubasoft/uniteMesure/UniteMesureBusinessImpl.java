package com.toubasoft.uniteMesure;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UniteMesureBusinessImpl implements UniteMesureBusiness {

	@Inject
	private UniteMesureDAO rayonDAO;

	@Override
	public UniteMesure createUniteMesure(UniteMesure rayon) {
		rayonDAO.create(rayon);
		return rayon;
	}

	@Override
	public UniteMesure updateUniteMesure(UniteMesure rayon) {
		rayonDAO.update(rayon);
		return rayon;
	}

	@Override
	public void deleteUniteMesure(Long idUniteMesure) {
		rayonDAO.delete(idUniteMesure);
	}

	@Override
	public List<UniteMesureDTO> retrieveUniteMesures() {
		List<UniteMesure> listUniteMesure = rayonDAO.findAll();
		List<UniteMesureDTO> listUniteMesureDTO = new ArrayList<>();
		for (UniteMesure unitesMesure : listUniteMesure) {
			UniteMesureDTO unitesMesureDTO = unitesMesure.convertEntityToDTO();
			listUniteMesureDTO.add(unitesMesureDTO);
		}

		return listUniteMesureDTO;
	}

	@Override
	public UniteMesure retrieveUniteMesureById(Long id) {
		return rayonDAO.findById(id);
	}

	@Override
	public List<UniteMesure> retrieveUniteMesureByName(String name) {
		return rayonDAO.findByName(name);
	}
}
