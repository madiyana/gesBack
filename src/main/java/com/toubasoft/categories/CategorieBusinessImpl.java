package com.toubasoft.categories;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CategorieBusinessImpl implements CategorieBusiness {

	@Inject
	private CategorieDAO categorieDAO;

	@Override
	public Categories createCategories(Categories categories) {
		categorieDAO.create(categories);
		return categories;
	}

	@Override
	public Categories updateCategories(Categories categories) {
		categorieDAO.update(categories);
		return categories;
	}

	@Override
	public void deleteCategories(Long idCategories) {
		categorieDAO.delete(idCategories);
	}

	@Override
	public List<Categories> retrieveCategories() {
		return categorieDAO.findAll();
	}

	@Override
	public Categories retrieveCategoriesById(Long id) {
		return categorieDAO.findById(id);
	}

	@Override
	public List<Categories> retrieveCategoriesByName(String name) {
		return categorieDAO.findByName(name);
	}

	@Override
	public List<Categories> retrieveCategoriesByNameDelay(String nom) {
		return categorieDAO.findByNameDelay(nom);
	}
}
