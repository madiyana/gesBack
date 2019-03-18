package com.toubasoft.stocks;

import java.util.List;

public interface StocksDAO {

	/**
	 * Create an stock
	 * 
	 * @param stocks
	 * @return
	 */
	public void create(Stocks stocks);
	

	/**
	 * Upadte an stock
	 * 
	 * @param stock
	 * @return
	 */
	public void update(Stocks stocks);

	/**
	 * Fin an stock by given id
	 * 
	 * @param id
	 * @return
	 */
	public Stocks findById(Long id);

	/**
	 * Delete an stock by given id
	 *
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * Retrieve all stocks *
	 * 
	 * @return List of stocks
	 */
	public List<Stocks> findAll();


	public List<RuptureStockDTO> findRuptureStock();


	public Stocks findByArticle(String reference);
}
