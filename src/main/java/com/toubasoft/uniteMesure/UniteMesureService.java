package com.toubasoft.uniteMesure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/unitemesure")
public class UniteMesureService {
	
	@Inject
	private UniteMesureBusiness rayonBusiness;
	
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
	@Path("/createUnite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUniteMesures(UniteMesure rayons) {
		Response.ResponseBuilder builder = null;
		Map<String, String> response;
		boolean exist = false;
		try {
			List<UniteMesure> listUniteMesures = rayonBusiness.retrieveUniteMesureByName(rayons.getNom().trim());
			if (rayons.getId() == null) {
				if (listUniteMesures.isEmpty()) {
					rayonBusiness.createUniteMesure(rayons);
				} else {
					exist = true;
				}
			} else {
				UniteMesure rayonOld = rayonBusiness.retrieveUniteMesureById(rayons.getId());
				if (!rayons.getNom().equals(rayonOld.getNom()) && !listUniteMesures.isEmpty()) {
					exist = true;
				} else {
					rayonBusiness.updateUniteMesure(rayons);
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
	@Path("/updateUnite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUniteMesures(UniteMesure rayons) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			rayonBusiness.updateUniteMesure(rayons);
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
	@Path("/deleteUnite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUniteMesures(@QueryParam("idUniteMesure") Long idUniteMesure) {
		Response.ResponseBuilder builder = null;
		try {
			rayonBusiness.deleteUniteMesure(idUniteMesure);
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
	@Path("/retrieveListUnite")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveListUniteMesures() {
		List<UniteMesureDTO> listUniteMesures = rayonBusiness.retrieveUniteMesures();
		if (listUniteMesures == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listUniteMesures).build();
	}
	
	/**
	 * Retrieve entity {@link UniteMesure} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveUniteById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUniteMesuresById(@QueryParam("idUniteMesure") Long id) {
		UniteMesure rayons = rayonBusiness.retrieveUniteMesureById(id);
		if (rayons == null) {
			return Response.ok().entity("L'article " + id + " est introuvable").build();
		}
		return Response.ok().entity(rayons).build();
	}
	
	/**
	 * Retrieve entity {@link UniteMesure} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveUniteMesuresByName")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUniteMesuresByName(@QueryParam("name") String name) {
		List<UniteMesure> listUniteMesures = rayonBusiness.retrieveUniteMesureByName(name);
		if (listUniteMesures == null) {
			return Response.ok().entity("L'article " + name + " est introuvable").build();
		}
		return Response.ok().entity(listUniteMesures).build();
	}
	
	

}
