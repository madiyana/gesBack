package com.toubasoft.stocks;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.toubasoft.articlesCommande.ArticlesCommande;
import com.toubasoft.commandes.Commandes;
import com.toubasoft.common.DateUtils;

@Stateless
public class StockBusinessImpl implements StocksBusiness {
	@Inject
	private StocksDAO stocksDAO;

	@Override
	public Stocks createStocks(Stocks stocks) {
		if (null != stocks.getNbArticlesDefectueux()) {
			stocks.setQuantiteReelle(stocks.getQuantite() - stocks.getNbArticlesDefectueux());
		}
		stocksDAO.create(stocks);
		return stocks;
	}

	@Override
	public Stocks updateStocks(Stocks stocks) {
		if (null != stocks.getNbArticlesDefectueux()) {
			stocks.setQuantiteReelle(stocks.getQuantite() - stocks.getNbArticlesDefectueux());
		}
		stocksDAO.update(stocks);
		return stocks;
	}

	@Override
	public void deleteStocks(Long idStocks) {
		stocksDAO.delete(idStocks);
	}

	@Override
	public List<Stocks> retrieveStocks() {
		return stocksDAO.findAll();
	}

	@Override
	public Stocks retrieveStocksById(Long id) {
		return stocksDAO.findById(id);
	}

	@Override
	public List<RuptureStockDTO> ruptureArticleStocks() {
		return stocksDAO.findRuptureStock();
	}

	@Override
	public void createStockCommande(Commandes commandes) {
		for (ArticlesCommande articlesCommande : commandes.getArticlesCommande()) {
			Stocks stocks = new Stocks();
			stocks.setCommandes(commandes);
			stocks.setArticles(articlesCommande.getArticles());
			stocks.setFournisseurs(articlesCommande.getFournisseurs());
			stocks.setDateEntree(DateUtils.dateOfDay());
			stocks.setQuantite(articlesCommande.getNbreArticleCommande());
			stocks.setNbArticlesDefectueux(0);
			stocksDAO.create(stocks);
		}
	}
}
