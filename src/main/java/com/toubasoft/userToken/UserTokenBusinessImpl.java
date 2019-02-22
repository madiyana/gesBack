package com.toubasoft.userToken;


import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserTokenBusinessImpl implements UserTokenBusiness {

	@Inject
	private UserTokenDAO userTokenDAO;
 
	
	@Override
	public void deleteUserToken(String identifiant) {
		userTokenDAO.delete(identifiant);
	}

	@Override
	public UsersToken retrieveUserToken(String identifiant) {
		return userTokenDAO.findUserToken(identifiant);
	}

	@Override
	public void createUserToken(UsersToken usersToken) {
		userTokenDAO.create(usersToken);
	}

}
