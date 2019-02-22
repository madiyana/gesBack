package com.toubasoft.fournisseurs;

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

@Path("/fournisseurs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FournisseurService {

	@Inject
	private FournisseurBusiness fournisseurBusiness = new FournisseurBusinessImpl();

	/**
	 * Create a new fournisseurs from the values provided (fournisseur) Return a
	 * JAX-RS Response with 200 if it is OK, or with a Map of fields and related
	 * error
	 *
	 * @param fournisseurs
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@POST
	@Path("/createFournisseurs")
	public Response createFournisseurs(Fournisseurs fournisseurs) {
		Response.ResponseBuilder builder = null;
		Map<String, String> response;
		boolean exist = false;
		try {
			List<Fournisseurs> listFournisseurs = fournisseurBusiness
					.retrieveFournisseursByName(fournisseurs.getNom().trim());
			if (fournisseurs.getId() == null) {
				if (listFournisseurs.isEmpty()) {
					fournisseurBusiness.createFournisseur(fournisseurs);
				} else {
					exist = true;
				}
			} else {
				Fournisseurs fournisseurOld = fournisseurBusiness.retrieveFournisseurById(fournisseurs.getId());
				if (!fournisseurs.getNom().equals(fournisseurOld.getNom()) && !listFournisseurs.isEmpty()) {
					exist = true;
				} else {
					fournisseurBusiness.updateFournisseurs(fournisseurs);
				}
			}

			if (exist) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Le fournisseur " + fournisseurs.getNom() + " existe déjà.").build();
			}

			return Response.ok(fournisseurs, MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			response = new HashMap<String, String>();
			response.put("Erreur technique lors de de creation du fournisseur :", e.getMessage());
		}
		builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		return builder.build();
	}

	/**
	 * Update an fournisseurs from the values provided (fournisseur) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param fournisseurs
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@PUT
	@Path("/updateFournisseurs")
	public Response updateFournisseurs(@Valid Fournisseurs fournisseurs) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			fournisseurBusiness.updateFournisseurs(fournisseurs);
			builder = Response.ok(fournisseurs, MediaType.APPLICATION_JSON);
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de mise à jour de l'fournisseur :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}

	/**
	 * Delete an fournisseurs from the values provided (fournisseur) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param fournisseurs
	 * @return
	 * @ @throws
	 *       Exception
	 */
	@DELETE
	@Path("/deleteFournisseurs")
	public Response deleteFournisseurs(@QueryParam("idFournisseur") Long idFournisseur) {
		Response.ResponseBuilder builder = null;
		try {
			fournisseurBusiness.deleteFournisseur(idFournisseur);
			builder = Response.ok(true, MediaType.APPLICATION_JSON);
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).entity("Erreur de suppression du fournisseur.").build();
		}
		return builder.build();
	}

	/**
	 * Retrieve all fournisseurs
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveListFournisseurs")
	public Response retrieveListFournisseurs() {

		List<Fournisseurs> listFournisseurs = fournisseurBusiness.retrieveFournisseurs();
		if (listFournisseurs == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listFournisseurs).build();
	}

	/**
	 * Retrieve entity {@link Fournisseurs} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveFournisseursById")
	public Response retrieveFournisseursById(@QueryParam("idFournisseur") Long id) {
		Fournisseurs fournisseurs = fournisseurBusiness.retrieveFournisseurById(id);
		if (fournisseurs == null) {
			return Response.ok().entity("Le fournisseur " + id + " est introuvable").build();
		}
		return Response.ok().entity(fournisseurs).build();
	}

	/**
	 * Retrieve entity {@link Fournisseurs} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveFournisseursByName")
	public Response retrieveFournisseursByName(@QueryParam("nom") String nom) {
		List<Fournisseurs> listFournisseurs = fournisseurBusiness.retrieveFournisseursByName(nom);
		if (listFournisseurs == null) {
			return Response.ok().entity("Le fournisseur " + nom + " est introuvable").build();
		}
		return Response.ok().entity(listFournisseurs).build();
	}
}
