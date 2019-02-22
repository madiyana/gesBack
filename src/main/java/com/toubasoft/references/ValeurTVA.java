package com.toubasoft.references;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ValeurTVA")
public class ValeurTVA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TVA")
	private Long id;

	@Column(name = "VALEUR_TVA")
	private double valeurTva;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValeurTva() {
		return valeurTva;
	}

	public void setValeurTva(double valeurTva) {
		this.valeurTva = valeurTva;
	}

}
