package com.toubasoft.commandes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
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

import com.toubasoft.articles.Articles;
import com.toubasoft.articlesCommande.ArticlesCommande;
import com.toubasoft.fournisseurs.Fournisseurs;

@Path("/commandes")
public class CommandeService {

	@Inject
	private CommandeBusiness commandeBusiness;

	/**
	 * Create a new articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param commandes
	 * @return
	 * @
	 * @throws Exception
	 */
	@POST
	@Path("/createCommandes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Commandes createCommandes(@Valid Commandes commandes) {
		if (commandes.getId() == null) {
			return commandeBusiness.createCommande(commandes);
		} else {
			return commandeBusiness.updateCommandes(commandes);
		}
	}

	
	/**
	 * Update an articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param commandes
	 * @return
	 * @
	 * @throws Exception
	 */
	@PUT
	@Path("/updateCommandes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Commandes updateCommandes(@Valid Commandes commandes) {
		return commandeBusiness.updateCommandes(commandes);
	}

	/**
	 * Delete an articles from the values provided (article) Return a JAX-RS
	 * Response with 200 if it is OK, or with a Map of fields and related error
	 *
	 * @param articles
	 * @return
	 * @
	 * @throws Exception
	 */
	@DELETE
	@Path("/deleteCommandes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateCommandes(@QueryParam("idCommande") Long idCommande) {
		commandeBusiness.deleteCommande(idCommande);
	}

	/**
	 * Retrieve all articles
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveListCommandes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Commandes> retrieveListCommandes() {
		return commandeBusiness.retrieveCommandes();
	}
	
	/**
	 * Retrieve all articles RECEPT
	 *
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveListCommandesStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Commandes> retrieveListCommandes(@QueryParam("status") String status) {
		return commandeBusiness.retrieveCommandesStatus(status);
	}

	/**
	 * Retrieve entity {@link Commandes} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveCommandesById")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Commandes retrieveCommandesById(@QueryParam("idCommande") Long id) {
		return commandeBusiness.retrieveCommandeById(id);
	}


	/**
	 * Retrieve entity {@link ArticleCommande} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveCommandesArticle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Articles> retrieveCommandesArticle(@QueryParam("idCommande") Long id) {
		List<Articles> listArticle = new ArrayList<>();
		Commandes commande = commandeBusiness.retrieveCommandeById(id);
		for (ArticlesCommande articlesCommande : commande.getArticlesCommande()) {
			listArticle.add(articlesCommande.getArticles());
		}
		return listArticle;
	}

	/**
	 * Retrieve entity {@link ArticleCommande} from the given values.
	 *
	 * @param id
	 * @return JAX-RS Response with 200 if it is OK, or with a Map of fields and
	 *         related error
	 */
	@GET
	@Path("/retrieveCommandeFournisseur")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Fournisseurs> retrieveCommandeFournisseur(@QueryParam("idCommande") Long id) {
		List<Fournisseurs> listFournisseur = new ArrayList<>();
		Commandes commande = commandeBusiness.retrieveCommandeById(id);
		for (ArticlesCommande articlesCommande : commande.getArticlesCommande()) {
			if(!listFournisseur.contains(articlesCommande.getFournisseurs())) {
				listFournisseur.add(articlesCommande.getFournisseurs());
			}
		}
		return listFournisseur;
	}
	
	
}
