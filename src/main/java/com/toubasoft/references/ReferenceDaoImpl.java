package com.toubasoft.references;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class ReferenceDaoImpl implements ReferenceDAO {

	@PersistenceContext 
	private EntityManager entityManager;

	@Override
	public List<UniteMesure> findAllUniteMesur() {
		TypedQuery<UniteMesure> query = entityManager.createQuery("select uniteMesure from UniteMesure uniteMesure", UniteMesure.class);
		return query.getResultList();
	}

	@Override
	public double findTVA() {
		TypedQuery<ValeurTVA> query = entityManager.createQuery("select tva from ValeurTVA tva", ValeurTVA.class);
		return query.getResultList().get(0).getValeurTva();
	}


}
