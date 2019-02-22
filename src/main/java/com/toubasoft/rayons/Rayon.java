package com.toubasoft.rayons;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Rayons")
public class Rayon {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RAYON")
	private Long id;

	@Column(name = "NOM_RAYON")
	private String nom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/** CONVERT ENTITY TO DTO **/

	public RayonDTO convertEntityToDTO() {
		RayonDTO rayonDTO = new RayonDTO();
		rayonDTO.setId(id);
		rayonDTO.setNom(nom);

		return rayonDTO;
	}

	/******************************************
	 * METHOS CHECK VALIDATION
	 ***************************************/
	public String checkValidationEntity() {
		return null;
	}

}
