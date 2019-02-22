package com.toubasoft.vente;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.toubasoft.ligneArticle.LigneArticle;

@Stateless
public class VenteDaoImpl implements VenteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(Ventes vente) {
		entityManager.persist(vente);
	}

	@Override
	public void addArticleVente(LigneArticle ligneArticle) {
		entityManager.persist(ligneArticle);
	}

	public List<Ventes> findAll() {
		return entityManager.createQuery("select vente from Ventes vente order by vente.dateVente asc", Ventes.class)
				.setMaxResults(50).getResultList();
	}

	@Override
	public List<Object[]> findDetailsVente(Long id) {
		String sql = "SELECT lart.nom_article, lart.ref_article, lart.quantite, lart.prix, lart.montant_total "
				+ "FROM vente_article vart " + "LEFT JOIN lignearticle lart on lart.id_ligne = vart.id_ligne "
				+ "WHERE vart.id_vente = :id";

		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	@Override
	public List<Object[]> findVenteCritere(String dateDebut, String dateFin) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Query query = null;
		try {
			Date dateDebutPArse = format.parse(dateDebut);
			Date dateFinPArse = format.parse(dateFin);

			String sql = "SELECT distinct ve.id_vente, ve.date_vente, CONCAT(emp.prenom, ' ',  emp.nom) as employe, ve.encaissement-ve.rendu as montant, ve.encaissement ,ve.rendu " + 
					"FROM ventes ve " + 
					"LEFT JOIN vente_article vart on ve.id_vente = vart.id_vente " + 
					"LEFT JOIN lignearticle lart on lart.id_ligne = vart.id_ligne " + 
					"LEFT JOIN employes emp ON emp.id_employe = ve.employe_id_employe " + 
					"WHERE cast(ve.date_vente as date) between :dateDebut and :dateFin";
			query = entityManager.createNativeQuery(sql);
			query.setParameter("dateDebut", dateDebutPArse);
			query.setParameter("dateFin", dateFinPArse);
			return query.getResultList();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
