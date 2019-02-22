package com.toubasoft.stocks;

import java.util.List;

import com.toubasoft.commandes.Commandes;

public interface StocksBusiness {

	/**
	 * Create a new stocks
	 *
	 * @param stocks
	 *            {@link Stocks}
	 * @throws Exception
	 * @
	 */
	public Stocks createStocks(Stocks stocks);

	/**
	 * Update Stocks
	 * 
	 * @param stocks
	 * @
	 */
	public Stocks updateStocks(Stocks stocks);

	/**
	 * Delete a stocks by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteStocks(Long idStocks);

	/**
	 * Retrieve all Stocks
	 *
	 * @return List of Stocks entities
	 * @throws Exception
	 * @
	 */
	public List<Stocks> retrieveStocks();

	/**
	 * Retrieve {@link Stocks} details for a given id.
	 *
	 * @param id
	 *            Technical Identifier
	 * @return {@link Stocks} entities
	 * @throws Exception
	 * @
	 */
	public Stocks retrieveStocksById(Long id);

	public void createStockCommande(Commandes commandes);

	public List<RuptureStockDTO> ruptureArticleStocks();

}
