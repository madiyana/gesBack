package com.toubasoft.references;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ReferenceBusinessImpl implements ReferenceBusiness {

	@Inject
	private ReferenceDAO referenceeDAO;

	@Override
	public List<UniteMesure> retrieveUniteMesure()  {
		return referenceeDAO.findAllUniteMesur();
	}

	@Override
	public double retrieveTVA()  {
		return referenceeDAO.findTVA();
	}
}
