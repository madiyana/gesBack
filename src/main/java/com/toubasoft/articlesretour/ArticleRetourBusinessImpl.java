package com.toubasoft.articlesretour;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class ArticleRetourBusinessImpl implements ArticleRetourBusiness {

	@Inject
	private ArticleRetourDAO articleDAO;

	public ArticlesRetour create(ArticlesRetour articlesRetour)  {
		articleDAO.create(articlesRetour);
		return articlesRetour;
	}

	public ArticlesRetour update(ArticlesRetour articlesRetour)  {
		articleDAO.update(articlesRetour);
		return articlesRetour;
	}

	public void delete(Long id)  {
		articleDAO.delete(id);
	}

	public List<ArticlesRetourDTO> retrieveArticles()  {
		return convertEntityDTO(articleDAO.findAll());
	}

	public ArticlesRetour retrieveArticleById(Long id)  {
		return articleDAO.findById(id);
	}

	public List<ArticlesRetourDTO> retrieveArticleByName(String name)  {
		return convertEntityDTO(articleDAO.findByName(name));
	}
	
	public List<ArticlesRetourDTO> retrieveArticleByNameDelay(String name)  {
		return convertEntityDTO(articleDAO.findByNameDelaySearch(name));
	}
	
	@Override
	public ArticlesRetour retrieveArticlesByReference(String reference) {
		return articleDAO.findByReference(reference);
	}

	/************** PRIVATE METHOD *****/

	private List<ArticlesRetourDTO> convertEntityDTO(List<ArticlesRetour> listArticles) {
		List<ArticlesRetourDTO> listArticlesDTO = new ArrayList<>();
			if(!listArticles.isEmpty()) {
			for (ArticlesRetour articles : listArticles) {
				ArticlesRetourDTO articlesDTO = articles.convertEntityToDTO();
				listArticlesDTO.add(articlesDTO);
			}
		}
		return listArticlesDTO;
	}

}
