package com.toubasoft.references;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/references")
public class ReferenceService {
	
	@Inject 
	private ReferenceBusiness referenceBusiness;
	

	/**
	 * Retrieve all listUniteMesure
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveListUnitMesure")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveListUnitMesure() {
		List<UniteMesure> listUnitMesure = referenceBusiness.retrieveUniteMesure();

		if (listUnitMesure == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listUnitMesure).build();
	}
	
}
