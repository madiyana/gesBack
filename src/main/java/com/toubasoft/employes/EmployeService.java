package com.toubasoft.employes;

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

import com.toubasoft.references.UserDTO;
import com.toubasoft.userToken.UserTokenBusiness;

@Path("/employes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeService {

	@Inject
	private EmployeBusiness employeBusiness;
	
	@Inject
	private UserTokenBusiness userTokenBusiness;

	// @Context
	// private ResourceInfo resourceInfo;

	/**
	 * Create a new employes from the values provided (employe) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param employes
	 * @return
	 * @
	 * @throws Exception
	 */
	@POST
	@Path("/createEmployes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEmployes(@Valid Employes employes) {
		Response.ResponseBuilder builder = null;
		try {

			// Check validation before persist
			if (employes.getId() == null) {
				employeBusiness.createEmploye(employes);
			} else {
				employeBusiness.updateEmployes(employes);
			}
			return Response.ok(employes, MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de creation de l'employe :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}

	/**
	 * Update an employes from the values provided (employe) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param employes
	 * @return
	 * @
	 * @throws Exception
	 */
	@PUT
	@Path("/updateEmployes")
	public Response updateEmployes(@Valid Employes employes) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			employeBusiness.updateEmployes(employes);
			builder = Response.ok(employes, MediaType.APPLICATION_JSON);
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de mise � jour de l'employe :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}

	/**
	 * Delete an employes from the values provided (employe) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param employes
	 * @return
	 * @
	 * @throws Exception
	 */
	@DELETE
	@Path("/deleteEmployes")
	public Response updateEmployes(@QueryParam("idEmploye") Long idEmploye) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			employeBusiness.deleteEmploye(idEmploye);
			builder = Response.ok().status(Response.Status.OK);
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de suppression de la employe :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}

	/**
	 * Retrieve all employes
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveListEmployes")
	public Response retrieveListEmployes() {
		List<Employes> listEmployes = employeBusiness.retrieveEmployes();
		if (listEmployes == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listEmployes).build();
	}

	/**
	 * Retrieve entity {@link Employes} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveEmployesById")
	public Response retrieveEmployesById(@QueryParam("idEmploye") Long id) {
		Employes employes = employeBusiness.retrieveEmployeById(id);
		if (employes == null) {
			return Response.ok().entity("La cat�gorie " + id + " est introuvable").build();
		}
		return Response.ok().entity(employes).build();
	}

	/**
	 * Retrieve List {@link Employes} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveEmployesByName")
	public Response retrieveEmployesByName(@QueryParam("nom") String nom) {
		List<Employes> listEmployes = employeBusiness.retrieveEmployeByName(nom);
		if (listEmployes == null) {
			return Response.ok().entity("Employes " + nom + " est introuvable").build();
		}
		return Response.ok().entity(listEmployes).build();
	}

	/**
	 * Update password after first connexion from the values provided (employe)
	 * Return a JAX-RS Response with 200 if it is OK, or with a Map of fields
	 * and related error
	 *
	 * @param employes
	 * @return
	 * @
	 * @throws Exception
	 */
	@PUT
	@Path("/updatePassword")
	public Response updatePassword(Employes employes) {
		Response.ResponseBuilder builder = null;
		try {
			// Check validation before persist
			Employes employesConnect = employeBusiness.updatePassword(employes);
			return Response.ok(employesConnect, MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("Erreur de mise a jour du mot de passe :", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		}
		return builder.build();
	}

	/**
	 * Authenticate employes from the values provided (employe) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param employes
	 * @return
	 * @
	 * @throws Exception
	 */
	@POST
	@Path("/authenticate")
	public Response authenticate(UserDTO userDTO) {
		try {
			// Vérifier si cet utilisateur est dejq connecté
			if(userTokenBusiness.retrieveUserToken(userDTO.getIdentifiant()) != null) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Erreur : Vous êtes déjà connecté avec ce compte.").build();
			} else {
				Map<String, Employes> mapEmployeToken = employeBusiness.authenticate(userDTO.getIdentifiant(), userDTO.getMotDePasse());
				if (mapEmployeToken.containsKey("ERROR_ID_PWD")) {
					return Response.status(Response.Status.FORBIDDEN)
							.entity("Erreur : Identifiant ou mot de passe incorrect.").build();
				} else if (mapEmployeToken.containsKey("NOT_ACTIF")) {
					return Response.status(Response.Status.FORBIDDEN)
							.entity("Erreur : Votre compte n'est pas activé. Veuillez contater l'administrateur.").build();
				}
				return Response.ok(mapEmployeToken, MediaType.APPLICATION_JSON).build();
			}
		} catch (PersistenceException e) {
			return Response.status(Response.Status.FORBIDDEN).entity("Erreur technique").build();
		}
	}
	
	@POST
	@Path("/logout")
	public void logout(Employes employes) {
		userTokenBusiness.deleteUserToken(employes.getIdentifiant());
	}
}
