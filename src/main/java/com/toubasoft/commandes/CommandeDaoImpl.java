package com.toubasoft.commandes;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.toubasoft.dashboard.DBCommandeDTO;

@Stateless
public class CommandeDaoImpl implements CommandeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(Commandes commande) {
		entityManager.persist(commande);
	}

	@Override
	public void update(Commandes commandes) {
		entityManager.merge(commandes);
	}

	@Override
	public Commandes findById(Long id) {
		return entityManager.find(Commandes.class, id);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(this.findById(id));
	}

	@Override
	public List<Commandes> findAll() {
		TypedQuery<Commandes> query = entityManager.createQuery("select commande from Commandes commande",
				Commandes.class);
		return query.getResultList();
	}

	@Override
	public List<Commandes> findByState(String etat) {
		TypedQuery<Commandes> query = entityManager
				.createQuery("select commande from Commandes commande where commande.etatCommande = :etatCommande",
						Commandes.class)
				.setParameter("etatCommande", etat);
		return query.getResultList();
	}
	
	@Override
	public DBCommandeDTO findStatus() {
		DBCommandeDTO dbCommandeDTO = new DBCommandeDTO();
		Query query = null;
		
		String sqlRecu = "select count(*) from commandes com where com.etat_commande = :etatCommande";
		query = entityManager.createNativeQuery(sqlRecu);
		query.setParameter("etatCommande", EtatCommande.RECEPT.getLabel());
		dbCommandeDTO.setNbRecu(((BigInteger) query.getSingleResult()).intValue());
		
		String sqlEnvoye = "select count(*) from commandes com where com.etat_commande = :etatCommande";
		query = entityManager.createNativeQuery(sqlEnvoye);
		query.setParameter("etatCommande", EtatCommande.ENVOYE.getLabel());
		dbCommandeDTO.setNbEnvoye(((BigInteger) query.getSingleResult()).intValue());
		
		String sqlAnnule = "select count(*) from commandes com where com.etat_commande = :etatCommande";
		query = entityManager.createNativeQuery(sqlAnnule);
		query.setParameter("etatCommande", EtatCommande.ANNULE.getLabel());
		dbCommandeDTO.setNbAnnule(((BigInteger) query.getSingleResult()).intValue());
		
		String sqlEnCours = "select count(*) from commandes com where com.etat_commande = :etatCommande";
		query = entityManager.createNativeQuery(sqlEnCours);
		query.setParameter("etatCommande", EtatCommande.EN_COURS.getLabel());
		dbCommandeDTO.setNbEnCours(((BigInteger) query.getSingleResult()).intValue());
	
		return dbCommandeDTO;
	}

}
