package com.toubasoft.vente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.toubasoft.employes.EmployeBusiness;
import com.toubasoft.ligneArticle.LigneArticle;
import com.toubasoft.ligneArticle.LigneArticleDTO;

@Path("/ventes")
public class VenteService {

	@Inject
	private VenteBusiness venteBusiness;
	
	@Inject
	private EmployeBusiness employeBusiness;

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
	public Response create(List<LigneArticle> ligneArticles, @QueryParam("encaissement") int encaissement,  @QueryParam("rendu") int rendu,
			 @QueryParam("montantTotal") Long montantTotal,  @QueryParam("idUser") Long idUser) {
		Response.ResponseBuilder builder = null;
		Map<String, String> response;
		try {
			Ventes vente = new Ventes();
			vente.setLigneArticle(ligneArticles);
			vente.setEncaissement(encaissement);
			vente.setRendu(rendu);
			vente.setMontantTotal(montantTotal);
			vente.setEmploye(employeBusiness.retrieveEmployeById(idUser));
 			venteBusiness.create(vente);
			return Response.ok(vente, MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			response = new HashMap<String, String>();
			response.put("Erreur techniqye lors de de creation de la vente :", e.getMessage());
		}
		builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
		return builder.build();
	}
	
	@GET
	@Path("/retrieveListVentes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveListVentes() {
		List<VenteDTO> listVentes = venteBusiness.retrieveVentes();
		if (listVentes == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(listVentes).build();
	}
	
	@GET
	@Path("/searchVente")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchVente(@QueryParam("dateDebut") String dateDebut, @QueryParam("dateFin") String dateFin) {
		if(null != dateDebut && !"".equals(dateDebut) && null != dateFin && !"".equals(dateFin)) {
			List<VenteDTO> listVentes = venteBusiness.retrieveVentesByCritere(dateDebut, dateFin);
			if (listVentes == null) {
				return Response.ok().status(Status.NO_CONTENT).build();
			}
			return Response.ok().entity(listVentes).build();			
		}
		return Response.status(Status.NOT_ACCEPTABLE).build();			
	}
	
	
	@GET
	@Path("/retrieveDetailsVentes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveListVentes(@QueryParam("idVente") Long idVente) {
		List<LigneArticleDTO> list = venteBusiness.retrieveDetailVentes(idVente);
		if (list == null) {
			return Response.ok().status(Status.NO_CONTENT).build();
		}
		return Response.ok().entity(list).build();
	}

}
