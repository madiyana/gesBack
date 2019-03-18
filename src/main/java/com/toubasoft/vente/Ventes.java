package com.toubasoft.vente;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.toubasoft.employes.Employes;
import com.toubasoft.ligneArticle.LigneArticle;

@Entity
@Table(name = "Ventes")
public class Ventes {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VENTE")
	private Long id;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "VENTE_ARTICLE", joinColumns = {
			@JoinColumn(name = "ID_VENTE", referencedColumnName = "ID_VENTE") }, inverseJoinColumns = {
					@JoinColumn(name = "ID_LIGNE", referencedColumnName = "ID_LIGNE") })
	private List<LigneArticle> ligneArticle;

	@OneToOne
	private Employes employe;

	@Column(name = "DATE_VENTE")
	private Date dateVente;

	@Column(name = "ENCAISSEMENT")
	private int encaissement;

	@Column(name = "RENDU")
	private int rendu;

	@Column(name = "MONTANT_TOTAL")
	private Long montantTotal;

	@Column(name = "TYPE_PAIEMENT")
	private String typePaiement;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LigneArticle> getLigneArticle() {
		return ligneArticle;
	}

	public void setLigneArticle(List<LigneArticle> ligneArticle) {
		this.ligneArticle = ligneArticle;
	}

	public int getEncaissement() {
		return encaissement;
	}

	public void setEncaissement(int encaissement) {
		this.encaissement = encaissement;
	}

	public int getRendu() {
		return rendu;
	}

	public void setRendu(int rendu) {
		this.rendu = rendu;
	}

	public Long getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(Long montantTotal) {
		this.montantTotal = montantTotal;
	}

	public Employes getEmploye() { 
		return employe;
	}

	public void setEmploye(Employes employe) {
		this.employe = employe;
	}

	public Date getDateVente() {
		return dateVente;
	}

	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}

	public String getTypePaiement() {
		return typePaiement;
	}

	public void setTypePaiement(String typePaiement) {
		this.typePaiement = typePaiement;
	}

	/** CONVERT ENTITY TO DTO **/

	public VenteDTO convertEntityToDTO() {
		VenteDTO venteDTO = new VenteDTO();
		venteDTO.setId(getId());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		venteDTO.setDateVente(format.format(getDateVente()));
		venteDTO.setEmploye(getEmploye().getPrenom() + " " + getEmploye().getNom());
		venteDTO.setMontantTotal(getMontantTotal());

		return venteDTO;
	}

}
