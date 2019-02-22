package com.toubasoft.userToken;

public interface UserTokenDAO {

	/**
	 * Create an article
	 * 
	 * @param articles
	 * @return
	 */
	public void create(UsersToken usersToken);

	/**
	 * Delete an article by given id
	 *
	 * @param id
	 */
	public void delete(String identifiant);

	/**
	 * Retrieve articles details for a given name.
	 *
	 * @param id
	 * @return List of articles
	 */
	public UsersToken findUserToken(String identifiant);
}
