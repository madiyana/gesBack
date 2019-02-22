package com.toubasoft.fournisseurs;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class FournisseurBusinessImpl implements FournisseurBusiness {

	@Inject
	private FournisseursDAO fournisseursDAO;

	@Override
	public Fournisseurs createFournisseur(Fournisseurs fournisseurs) {
		fournisseursDAO.create(fournisseurs);
		return fournisseurs;
	}

	@Override
	public Fournisseurs updateFournisseurs(Fournisseurs fournisseurs) {
		fournisseursDAO.update(fournisseurs);
		return fournisseurs;
	}

	@Override
	public void deleteFournisseur(Long idFournisseur) {
		fournisseursDAO.delete(idFournisseur);
	}

	@Override
	public List<Fournisseurs> retrieveFournisseurs() {
		return fournisseursDAO.findAll();
	}

	@Override
	public Fournisseurs retrieveFournisseurById(Long id) {
		return fournisseursDAO.findById(id);
	}

	@Override
	public List<Fournisseurs> retrieveFournisseursByName(String name) {
		return fournisseursDAO.findByName(name);
	}

}
