package com.toubasoft.userToken;

public interface UserTokenBusiness {

	/**
	 * Create a new article
	 *
	 * @param article
	 *            s {@link UsersToken}
	 * @throws Exception
	 * @
	 */
	public void createUserToken(UsersToken usersToken);

	/**
	 * Delete a article by given id
	 *
	 * @param id
	 * @throws Exception
	 * @
	 */
	public void deleteUserToken(String identifiant);

	/**
	 * Retrieve {@link UsersToken} details for a given token.
	 *
	 * @param anme
	 *            Technical Identifier
	 * @return {@link UsersToken} entities
	 * @throws Exception
	 * @
	 */
	public UsersToken retrieveUserToken(String identifiant);

}
