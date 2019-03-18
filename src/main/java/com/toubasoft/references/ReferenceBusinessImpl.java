package com.toubasoft.references;


import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ReferenceBusinessImpl implements ReferenceBusiness {

	@Inject
	private ReferenceDAO referenceeDAO;

	@Override
	public double retrieveTVA()  {
		return referenceeDAO.findTVA();
	}
}
