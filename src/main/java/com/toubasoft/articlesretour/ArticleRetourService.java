package com.toubasoft.articlesretour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
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

@Path("/articlesretour")
public class ArticleRetourService {

	@Inject
	private ArticleRetourBusiness articleBusiness;

	/**
	 * Create a new articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param articles
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createArticles(ArticlesRetour articles) {
		Response.ResponseBuilder builder = null;
		Map<String, String> response;
		try {
			if (articles.getId() == null) {
				articleBusiness.create(articles);
			} else {
				articleBusiness.update(articles);
			}
			return Response.ok(articles, MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			response = new HashMap<String, String>();
			response.put("Erreur techniqye lors de de creation de la categorie :", e.getMessage());
		}
		builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		return builder.build();
	}

	/**
	 * Update an articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param articles
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateArticles(ArticlesRetour articles) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			articleBusiness.update(articles);
			builder = Response.ok(articles, MediaType.APPLICATION_JSON);
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de mise � jour de l'article :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}

	/**
	 * Delete an articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param articles
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteArticles(@QueryParam("id") Long idArticle) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			articleBusiness.delete(idArticle);
			builder = Response.ok(true, MediaType.APPLICATION_JSON);
		} catch (ConstraintViolationException e) {

			return Response.status(Response.Status.FORBIDDEN)
					.entity("Erreur de ConstraintViolationException de la categorie.").build();
		} catch (Exception e) {

			return Response.status(Response.Status.FORBIDDEN).entity("Erreur de suppression d'article.").build();
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
	@Path("/retrieveList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveListArticles() {
		List<ArticlesRetourDTO> listArticles = articleBusiness.retrieveArticles();
		if (listArticles == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listArticles).build();
	}

	/**
	 * Retrieve entity {@link ArticlesRetour} from the given values.
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
		ArticlesRetour articles = articleBusiness.retrieveArticleById(id);
		if (articles == null) {
			return Response.ok().entity("L'article " + id + " est introuvable").build();
		}
		return Response.ok().entity(articles).build();
	}

	/**
	 * Retrieve entity {@link ArticlesRetour} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveArticlesByName")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveArticlesByName(@QueryParam("nom") String nom, @QueryParam("delay") String delay) {
		List<ArticlesRetourDTO> listArticles = new ArrayList<>();
		if (null != delay && !"".equals(delay)) {
			listArticles = articleBusiness.retrieveArticleByNameDelay(nom);
		} else {
			listArticles = articleBusiness.retrieveArticleByName(nom);
		}
		if (listArticles == null) {
			return Response.ok().entity("L'article " + nom + " est introuvable").build();
		}
		return Response.ok().entity(listArticles).build();
	}

	@GET
	@Path("/retrieveArticlesByReference")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveArticlesByCodeProduit(@QueryParam("reference") String reference) {
		ArticlesRetour articles = articleBusiness.retrieveArticlesByReference(reference);
		if (articles == null) {
			return Response.ok().entity("L'article " + articles + " est introuvable").build();
		}
		return Response.ok().entity(articles).build();
	}

}
