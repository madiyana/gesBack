package com.toubasoft.commandes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.toubasoft.articlesCommande.ArticlesCommande;
import com.toubasoft.employes.Employes;

@Entity
@Table(name = "Commandes")
public class Commandes {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMMANDE")
	private Long id;

	@Column(name = "DATE_COMMANDE")
	private String dateCommande;

	@Column(name = "ETAT_COMMANDE")
	private String etatCommande;

	@Column(name = "OBSERVATION")
	private String observation;
	
	@OneToOne
	private Employes employes;

	@OneToMany(cascade= {CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinTable(name = "COMMANDE_ARTICLE", 
	joinColumns = {@JoinColumn(name = "ID_COMMANDE", referencedColumnName = "ID_COMMANDE")}, 
		inverseJoinColumns = {@JoinColumn(name = "ART_COMMAND", referencedColumnName = "ID_ARTCOM") })
	private List<ArticlesCommande> articlesCommande;

	/*
	 * BasePersonEtat : ID_TABLE , ID_BASE_NATIONALE, ID_ETAT
	 * Person : List<BasePersonEtat>
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getEtatCommande() {
		return etatCommande;
	}

	public void setEtatCommande(String etatCommande) {
		this.etatCommande = etatCommande;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<ArticlesCommande> getArticlesCommande() {
		return articlesCommande;
	}

	public void setArticlesCommande(List<ArticlesCommande> articlesCommande) {
		this.articlesCommande = articlesCommande;
	}

	public Employes getEmployes() {
		return employes;
	}

	public void setEmployes(Employes employes) {
		this.employes = employes;
	}

	/** CONVERT ENTITY TO DTO **/

	public CommandesDTO convertEntityToDTO() {
		CommandesDTO commandesDTO = new CommandesDTO();
		commandesDTO.setId(id);
		commandesDTO.setDateCommande(dateCommande);
		commandesDTO.setEtatCommande(etatCommande);
		commandesDTO.setObservation(observation);
		commandesDTO.setNomPrenom(employes.getPrenom()+" "+employes.getNom().toUpperCase());
		return commandesDTO;
	}

	/******************************************
	 * METHOS CHECK VALIDATION
	 ***************************************/
	public String checkValidationEntity() {
		return null;
	}

}
