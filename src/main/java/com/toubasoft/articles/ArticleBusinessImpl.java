package com.toubasoft.articles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.toubasoft.references.ReferenceBusiness;

@Stateless
public class ArticleBusinessImpl implements ArticleBusiness {

	@Inject
	private ArticleDAO articleDAO;

	@Inject
	private ReferenceBusiness referenceBusiness;

	public Articles createArticle(Articles article)  {
		setArticleMargeprixVenteTTC(article);
		if (article.getReference() == null) {
			genererReferenceArticle(article);		
		}
		articleDAO.create(article);
		return article;
	}

	public Articles updateArticles(Articles article)  {
		setArticleMargeprixVenteTTC(article);
		articleDAO.update(article);
		return article;
	}

	public void deleteArticle(Long idArticle)  {
		articleDAO.delete(idArticle);
	}

	public List<ArticlesDTO> retrieveArticles()  {
		return convertEntityDTO(articleDAO.findAll());
	}

	public Articles retrieveArticleById(Long id)  {
		return articleDAO.findById(id);
	}

	public List<ArticlesDTO> retrieveArticleByName(String name)  {
		return convertEntityDTO(articleDAO.findByName(name));
	}
	
	public List<ArticlesDTO> retrieveArticleByNameDelay(String name)  {
		return convertEntityDTO(articleDAO.findByNameDelaySearch(name));
	}
	
	@Override
	public Articles retrieveArticlesByReference(String reference) {
		return articleDAO.findByReference(reference);
	}

	/************** PRIVATE METHOD *****/

	private Articles setArticleMargeprixVenteTTC(Articles article) {
		article.setMarge(article.getPrixVenteHT() - article.getPrixAchat());
		if (article.isTva()) {
			double tva = 1 + (referenceBusiness.retrieveTVA() / 100);
			article.setPrixVenteTTC((long) (tva * article.getPrixVenteHT()));
		} else {
			article.setPrixVenteTTC(article.getPrixVenteHT());
		}
		return article;
	}

	private Articles genererReferenceArticle(Articles article) {
		int length = 12;
		String reference = "";
		String chars = "0123456789";
		String str = new Random().ints(length, 0, chars.length()).mapToObj(i -> "" + chars.charAt(i))
				.collect(Collectors.joining());
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
			reference = 3 + "" + str;
		article.setReference(reference);
		return article;
	}
	
	private List<ArticlesDTO> convertEntityDTO(List<Articles> listArticles) {
		List<ArticlesDTO> listArticlesDTO = new ArrayList<>();
			if(!listArticles.isEmpty()) {
			for (Articles articles : listArticles) {
				ArticlesDTO articlesDTO = articles.convertEntityToDTO();
				listArticlesDTO.add(articlesDTO);
			}
		}
		return listArticlesDTO;
	}

}
