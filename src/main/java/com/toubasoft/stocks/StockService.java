package com.toubasoft.stocks;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.toubasoft.commandes.Commandes;

@Path("/stocks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StockService {

	@Inject
	private StocksBusiness stockBusiness = new StockBusinessImpl();

	/**
	 * Create a new stocks from the values provided (stock) Return a JAX-RS Response
	 * with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param stocks
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@POST
	@Path("/createStocks")
	public Stocks createStocks(Stocks stocks) {
		if (stocks.getId() == null) {
			stocks = stockBusiness.createStocks(stocks);
		} else {
			stocks = stockBusiness.updateStocks(stocks);
		}
		return stocks;
	}

	/**
	 * Create a new stocks from the values provided (commande) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param Commande
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@POST
	@Path("/createStockCommande")
	public void createStockCommande(Commandes commandes) {
		stockBusiness.createStockCommande(commandes);
	}

	/**
	 * Delete an stocks from the values provided (stock) Return a JAX-RS Response
	 * with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param stocks
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@DELETE
	@Path("/deleteStocks")
	public void updateStocks(@QueryParam("idStock") Long idStock) {
		stockBusiness.deleteStocks(idStock);
	}

	/**
	 * Retrieve all stocks
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveListStocks")
	public List<Stocks> retrieveListStocks() {
		return stockBusiness.retrieveStocks();
	}

	@GET
	@Path("/ruptureArticleStocks")
	public List<RuptureStockDTO> ruptureArticleStocks() {
		return stockBusiness.ruptureArticleStocks();
	}

	/**
	 * Retrieve entity {@link Stocks} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveStocksById")
	public Stocks retrieveStocksById(@QueryParam("idStock") Long id) {
		return stockBusiness.retrieveStocksById(id);
	}
}
