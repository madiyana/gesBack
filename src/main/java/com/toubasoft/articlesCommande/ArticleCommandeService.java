package com.toubasoft.articlesCommande;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/articlesCommande")
public class ArticleCommandeService {
	
	@Inject
	private ArticleCommandeBusiness articleCommandeBusiness;
	
	/**
	 * Create a new articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param articles
	 * @return
	 * @
	 * @throws Exception
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createArticles(@Valid ArticlesCommande articlesCommande) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			if (articlesCommande.getId() == null) {
				articleCommandeBusiness.createArticle(articlesCommande);
			}else{
				articleCommandeBusiness.updateArticles(articlesCommande);
			}
			return Response.ok(articlesCommande, MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de creation de l'article :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}
	
	
	/**
	 * Update an articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param articles
	 * @return
	 * @
	 * @throws Exception
	 */
	@PUT
	@Path("/updateArticles")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateArticles(@Valid ArticlesCommande articles) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			articleCommandeBusiness.updateArticles(articles);
			builder = Response.ok(articles, MediaType.APPLICATION_JSON);
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de mise � jour de l'article :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}
	
	/** Delete an articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param articles
	 * @return
	 * @
	 * @throws Exception
	 */
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateArticles(@QueryParam("id") Long id) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			articleCommandeBusiness.deleteArticleCommande(id);
			builder = Response.ok().status(Response.Status.OK);
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de suppression de l'article :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}
	
	/**
	 * Retrieve all articles
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveListArticles() {

		List<ArticlesCommandeDTO> listArticles = articleCommandeBusiness.retrieveArticlesCommande();
		if (listArticles == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listArticles).build();
	}
	
	/**
	 * Retrieve entity {@link ArticlesCommande} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveArticlesById(@QueryParam("id") Long id) {
		ArticlesCommande articles = articleCommandeBusiness.retrieveArticleCommandeById(id);
		if (articles == null) {
			return Response.ok().entity("L'article " + id + " est introuvable").build();
		}
		return Response.ok().entity(articles).build();
	}
	
	/**
	 * Retrieve entity {@link ArticlesCommande} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveArticlesByName")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveArticlesByName(@QueryParam("article") String article, @QueryParam("fournisseur") String fournisseur) {
		List<ArticlesCommande> listArticles = articleCommandeBusiness.retrieveArticleCommandeByName(article, fournisseur);
		if (listArticles == null) {
			return null;
		}
		return Response.ok().entity(listArticles).build();
	}
	
	

}
