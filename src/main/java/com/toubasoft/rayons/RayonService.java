package com.toubasoft.rayons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/rayons")
public class RayonService {
	
	@Inject
	private RayonBusiness rayonBusiness;
	
	/**
	 * Create a new rayons from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param rayons
	 * @return
	 * @
	 * @throws Exception
	 */
	@POST
	@Path("/createRayons")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRayons(Rayon rayons) {
		Response.ResponseBuilder builder = null;
		Map<String, String> response;
		boolean exist = false;
		try {
			List<Rayon> listRayons = rayonBusiness.retrieveRayonByName(rayons.getNom().trim());
			if (rayons.getId() == null) {
				if (listRayons.isEmpty()) {
					rayonBusiness.createRayon(rayons);
				} else {
					exist = true;
				}
			} else {
				Rayon rayonOld = rayonBusiness.retrieveRayonById(rayons.getId());
				if (!rayons.getNom().equals(rayonOld.getNom()) && !listRayons.isEmpty()) {
					exist = true;
				} else {
					rayonBusiness.updateRayon(rayons);
				}
			}

			if (exist) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Le rayon " + rayons.getNom() + " existe déjà.").build();
			}

			return Response.ok(rayons, MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			response = new HashMap<String, String>();
			response.put("Erreur technique lors de de creation du rayon :", e.getMessage());
		}
		builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		return builder.build();
	}
	
	
	/**
	 * Update an rayons from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param rayons
	 * @return
	 * @
	 * @throws Exception
	 */
	@PUT
	@Path("/updateRayons")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRayons(Rayon rayons) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			rayonBusiness.updateRayon(rayons);
			builder = Response.ok(rayons, MediaType.APPLICATION_JSON);
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de mise � jour de l'article :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}
	
	/** Delete an rayons from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param rayons
	 * @return
	 * @
	 * @throws Exception
	 */
	@DELETE
	@Path("/deleteRayons")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRayons(@QueryParam("idRayon") Long idRayon) {
		Response.ResponseBuilder builder = null;
		try {
			rayonBusiness.deleteRayon(idRayon);
			builder = Response.ok(true, MediaType.APPLICATION_JSON);
		} catch (Exception e) {
			
			return Response.status(Response.Status.FORBIDDEN).entity("Erreur de suppression du rayon.").build();
		}
		return builder.build();
	}
	
	/**
	 * Retrieve all rayons
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveListRayons")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveListRayons() {
		List<RayonDTO> listRayons = rayonBusiness.retrieveRayons();
		if (listRayons == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listRayons).build();
	}
	
	/**
	 * Retrieve entity {@link Rayon} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveRayonsById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveRayonsById(@QueryParam("idRayon") Long id) {
		Rayon rayons = rayonBusiness.retrieveRayonById(id);
		if (rayons == null) {
			return Response.ok().entity("L'article " + id + " est introuvable").build();
		}
		return Response.ok().entity(rayons).build();
	}
	
	/**
	 * Retrieve entity {@link Rayon} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveRayonsByName")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveRayonsByName(@QueryParam("name") String name) {
		List<Rayon> listRayons = rayonBusiness.retrieveRayonByName(name);
		if (listRayons == null) {
			return Response.ok().entity("L'article " + name + " est introuvable").build();
		}
		return Response.ok().entity(listRayons).build();
	}
	
	

}
