package com.toubasoft.userToken;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserTokenDaoImpl implements UserTokenDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(UsersToken article) {
		entityManager.persist(article);
	}

	@Override
	public void delete(String identifiant) {
		entityManager.remove(findUserToken(identifiant));
	}

	@Override
	public UsersToken findUserToken(String identifiant) {
		TypedQuery<UsersToken> typedQuery = entityManager
				.createQuery("select user from UsersToken user where user.identifiant = :identifiant", UsersToken.class)
				.setParameter("identifiant", identifiant);
		List<UsersToken> listUsersToken = typedQuery.getResultList();
		if (listUsersToken.isEmpty()) {
			return null;
		}
		return listUsersToken.get(0);
	}

}
