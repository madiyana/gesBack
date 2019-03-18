package com.toubasoft.dashboard;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.toubasoft.commandes.CommandeBusiness;
import com.toubasoft.vente.TypeVenteDTO;
import com.toubasoft.vente.CaVenteDTO;
import com.toubasoft.vente.VenteBusiness;

@Path("/dashboard")
public class DashboardService {

	@Inject
	private CommandeBusiness commandeBusiness;
	@Inject
	private VenteBusiness venteBusiness;


	
	@GET
	@Path("/commandeStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public Response commandeStatus() {
		DBCommandeDTO dbCommandeDTO = commandeBusiness.retrieveStatusCommandes();
		if (dbCommandeDTO == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(dbCommandeDTO).build();
	}
	
	@GET
	@Path("/caVente")
	@Produces(MediaType.APPLICATION_JSON)
	public Response caVente() {
		CaVenteDTO caVente = venteBusiness.retrieveCaVentes();
		if (caVente == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(caVente).build();
	}
	
	@GET
	@Path("/popularArticles")
	@Produces(MediaType.APPLICATION_JSON)
	public Response popularArticles() {
		List<DBArticleDTO> list = venteBusiness.popularArticle();
		if (list == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(list).build();
	}
	
	
	@GET
	@Path("/typeVente")
	@Produces(MediaType.APPLICATION_JSON)
	public Response typeVente() {
		TypeVenteDTO typeVenteDTO = venteBusiness.retrieveTypeVentes();
		if (typeVenteDTO == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(typeVenteDTO).build();
	}
	
	
}
