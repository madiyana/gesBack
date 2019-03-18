package com.toubasoft.employes;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.toubasoft.common.NumberUtils;
import com.toubasoft.userToken.UserTokenBusiness;
import com.toubasoft.userToken.UsersToken;

@Stateless
public class EmployeBusinessImpl implements EmployeBusiness {

	@Inject
	private EmployeDAO employeDAO;

	@Inject
	private UserTokenBusiness userTokenBusiness;

	@Override
	public Employes createEmploye(Employes employe) {
		String identifiant = employe.getNom().toLowerCase() + employe.getPrenom().toLowerCase().substring(0, 2);
		String identifiantGen = generateIdentUser(identifiant);
		employe.setIdentifiant(identifiantGen);
		employe.setMotDePasse(identifiantGen);
		employe.setPremConnexion(true);
		employeDAO.create(employe);
		return employe;
	}

	@Override
	public Employes updateEmployes(Employes employe) {
		employeDAO.update(employe);
		return employe;
	}

	@Override
	public void deleteEmploye(Long idEmploye) {
		employeDAO.delete(idEmploye);
	}

	@Override
	public List<Employes> retrieveEmployes() {
		return employeDAO.findAll();
	}

	@Override
	public Employes retrieveEmployeById(Long id) {
		return employeDAO.findById(id);
	}

	@Override
	public Map<String, Employes> authenticate(String username, String password) {
		Map<String, Employes> employeToken = new HashMap<String, Employes>();
		Employes employesConnect = employeDAO.authenticate(username, password);
		if (employesConnect == null) {
			employeToken.put("ERROR_ID_PWD", employesConnect);
		} else {
			long millis = System.currentTimeMillis();
			String token = issueToken(username + password + millis);
			employeToken.put(token, employesConnect);
			if (!employesConnect.isActif()) {
				employeToken.put("NOT_ACTIF", employesConnect);
			} else if(!employesConnect.isPremConnexion()){
				// mise a jour table user_token
				UsersToken usersToken = new UsersToken();
				usersToken.setIdentifiant(employesConnect.getIdentifiant());
				usersToken.setToken(token);
				userTokenBusiness.createUserToken(usersToken);
			}
		}
		return employeToken;
	}

	@Override
	public Employes updatePassword(Employes employes) {
		employes.setPremConnexion(false);
		employeDAO.update(employes);
		return employes;
	}

	/**
	 * Generation des identifiants de connexion
	 * 
	 * @param identifiant
	 * @return
	 */
	private String generateIdentUser(String identifiant) {
		Employes employeCourant = employeDAO.findUsername(identifiant);
		int newVar = 1;
		if (employeCourant == null)
			return identifiant;
		else {
			// Verfier si le dernier chiffre est un nombre ==> Si oui ++ sinon 1
			String last = identifiant.substring(identifiant.length() - 1);
			if (NumberUtils.isInteger(last)) {
				newVar = Integer.parseInt(last) + 1;
			} else {
				// pas encore de numero opour cet identifiant
				return generateIdentUser(identifiant + "1");
			}
			return generateIdentUser(identifiant + newVar);
		}
	}

	@Override
	public List<Employes> retrieveEmployeByName(String name) {
		return employeDAO.findByName(name);
	}

	private String issueToken(String username) {
		String token = "";
		try {
			Algorithm algorithm = Algorithm.HMAC256(username);
			token = JWT.create().withIssuer("auth0").sign(algorithm);
		} catch (UnsupportedEncodingException exception) {
			// UTF-8 encoding not supported
		} catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
		}
		return token;
	}

	@Override
	public void logout(Employes employes) {
		userTokenBusiness.deleteUserToken(employes.getIdentifiant());
	}

}
