package com.toubasoft.categories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
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

import com.toubasoft.employes.Employes;

@Path("/categories")
public class CategorieService {

	@Inject
	private CategorieBusiness categorieBusiness;

	/**
	 * Create a new categories from the values provided (categorie) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param categories
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@POST
	@Path("/createCategories")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCategories(Categories categories) {
		Response.ResponseBuilder builder = null;
		Map<String, String> response;
		boolean exist = false;
		try {
			List<Categories> listCategories = categorieBusiness.retrieveCategoriesByName(categories.getNom().trim());
			if (categories.getId() == null) {
				if (listCategories.isEmpty()) {
					categorieBusiness.createCategories(categories);
				} else {
					exist = true;
				}
			} else {
				Categories categoriesOld = categorieBusiness.retrieveCategoriesById(categories.getId());
				if (!categories.getNom().equals(categoriesOld.getNom()) && !listCategories.isEmpty()) {
					exist = true;
				} else {
					categorieBusiness.updateCategories(categories);
				}
			}

			if (exist) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("La catégorie " + categories.getNom() + " existe déjà.").build();
			}

			return Response.ok(categories, MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			response = new HashMap<String, String>();
			response.put("Erreur techniqye lors de de creation de la categorie :", e.getMessage());
		}
		builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		return builder.build();
	}

	/**
	 * Update an categories from the values provided (categorie) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param categories
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@PUT
	@Path("/updateCategories")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategories(@Valid Categories categories) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			categorieBusiness.updateCategories(categories);
			builder = Response.ok(categories, MediaType.APPLICATION_JSON);
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de mise � jour de l'categorie :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}

	/**
	 * Delete an categories from the values provided (categorie) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param categories
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@DELETE
	@Path("/deleteCategories")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCategories(@QueryParam("idCategorie") Long idCategorie) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			categorieBusiness.deleteCategories(idCategorie);
			builder = Response.ok(true, MediaType.APPLICATION_JSON);
		} catch (ConstraintViolationException e) {

			return Response.status(Response.Status.FORBIDDEN)
					.entity("Erreur de ConstraintViolationException de la categorie.").build();
		} catch (Exception e) {

			return Response.status(Response.Status.FORBIDDEN).entity("Erreur de suppression de la categorie.").build();
		}
		return builder.build();
	}

	/**
	 * Retrieve all categories
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveListCategories")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveListCategories() {
		List<Categories> listCategories = categorieBusiness.retrieveCategories();

		if (listCategories == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listCategories).build();
	}

	/**
	 * Retrieve entity {@link Categories} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveCategoriesById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveCategoriesById(@QueryParam("idCategorie") Long id) {
		Categories categories = categorieBusiness.retrieveCategoriesById(id);
		if (categories == null) {
			return Response.ok().entity("La cat�gorie " + id + " est introuvable").build();
		}
		return Response.ok().entity(categories).build();
	}

	/**
	 * Retrieve List {@link Employes} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveCategoriesByName")
	public Response retrieveCategoriesByName(@QueryParam("nom") String nom, @QueryParam("delay") String delay) {
		List<Categories> listCategories = new ArrayList<Categories>(); // categorieBusiness.retrieveCategoriesByName(nom);
		if (null != delay && !"".equals(delay)) {
			listCategories = categorieBusiness.retrieveCategoriesByNameDelay(nom);
		} else {
			listCategories = categorieBusiness.retrieveCategoriesByName(nom);
		}
		if (listCategories == null) {
			return Response.ok().entity("Categories " + nom + " est introuvable").build();
		}
		return Response.ok().entity(listCategories).build();
	}
}
