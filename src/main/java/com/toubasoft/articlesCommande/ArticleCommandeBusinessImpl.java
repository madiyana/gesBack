package com.toubasoft.articlesCommande;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ArticleCommandeBusinessImpl implements ArticleCommandeBusiness {

	@Inject
	private ArticleCommandeDAO articleCommandeDAO;

	@Override
	public ArticlesCommande createArticle(ArticlesCommande articlesCommande) {
		articleCommandeDAO.create(articlesCommande);
		return articlesCommande;
	}

	@Override
	public ArticlesCommande updateArticles(ArticlesCommande articlesCommande) {
		articleCommandeDAO.update(articlesCommande);
		return articlesCommande;
	}

	@Override
	public void deleteArticleCommande(Long id) {
		articleCommandeDAO.delete(id);
	}

	@Override
	public List<ArticlesCommandeDTO> retrieveArticlesCommande() {
		List<ArticlesCommande> listArticles = articleCommandeDAO.findAll();
		List<ArticlesCommandeDTO> listArticlesDTO = new ArrayList<>();
		for (ArticlesCommande articles : listArticles) {
			ArticlesCommandeDTO articlesDTO = articles.convertEntityToDTO();
			listArticlesDTO.add(articlesDTO);
		}

		return listArticlesDTO;
	}

	@Override
	public ArticlesCommande retrieveArticleCommandeById(Long id) {
		return articleCommandeDAO.findById(id);
	}

	@Override
	public List<ArticlesCommande> retrieveArticleCommandeByName(String article, String fournisseur) {
		// TODO Auto-generated method stub
		return null;
	}

}
