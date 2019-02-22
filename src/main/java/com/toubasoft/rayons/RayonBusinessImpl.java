package com.toubasoft.rayons;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RayonBusinessImpl implements RayonBusiness {

	@Inject
	private RayonDAO rayonDAO;

	@Override
	public Rayon createRayon(Rayon rayon) {
		rayonDAO.create(rayon);
		return rayon;
	}

	@Override
	public Rayon updateRayon(Rayon rayon) {
		rayonDAO.update(rayon);
		return rayon;
	}

	@Override
	public void deleteRayon(Long idRayon) {
		rayonDAO.delete(idRayon);
	}

	@Override
	public List<RayonDTO> retrieveRayons() {
		List<Rayon> listRayon = rayonDAO.findAll();
		List<RayonDTO> listRayonDTO = new ArrayList<>();
		for (Rayon rayons : listRayon) {
			RayonDTO rayonsDTO = rayons.convertEntityToDTO();
			listRayonDTO.add(rayonsDTO);
		}

		return listRayonDTO;
	}

	@Override
	public Rayon retrieveRayonById(Long id) {
		return rayonDAO.findById(id);
	}

	@Override
	public List<Rayon> retrieveRayonByName(String name) {
		return rayonDAO.findByName(name);
	}
}
