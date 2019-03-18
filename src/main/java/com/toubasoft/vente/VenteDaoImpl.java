package com.toubasoft.vente;

import java.math.BigDecimal;
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
		String sql = "SELECT lart.nom_article, lart.ref_article, lart.quantite, lart.prix, lart.montant_total, lart.id_ligne "
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

			String sql = "SELECT distinct ve.id_vente, ve.date_vente, CONCAT(emp.prenom, ' ',  emp.nom) as employe, ve.encaissement-ve.rendu as montant, ve.encaissement ,ve.rendu "
					+ "FROM ventes ve " + "LEFT JOIN vente_article vart on ve.id_vente = vart.id_vente "
					+ "LEFT JOIN lignearticle lart on lart.id_ligne = vart.id_ligne "
					+ "LEFT JOIN employes emp ON emp.id_employe = ve.employe_id_employe "
					+ "WHERE cast(ve.date_vente as date) between :dateDebut and :dateFin";
			query = entityManager.createNativeQuery(sql);
			query.setParameter("dateDebut", dateDebutPArse);
			query.setParameter("dateFin", dateFinPArse);
			return query.getResultList();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteLigneArticle(Long idVente, Long idLigne) {
		// get ligne article
		LigneArticle ligne = entityManager.find(LigneArticle.class, idLigne);

		// Mise a jour total vente
		Ventes ventes = findById(idVente);
		ventes.setMontantTotal(ventes.getMontantTotal() - ligne.getMontantTotal());
		entityManager.merge(ventes);

		// Delete in vente article
		entityManager.createNativeQuery("delete from vente_article where id_ligne = :idLigne")
				.setParameter("idLigne", idLigne).executeUpdate();

		// remove ligne artilcle
		entityManager.createNativeQuery("delete from lignearticle where id_ligne = :idLigne")
				.setParameter("idLigne", idLigne).executeUpdate();

		// Check if list ligne article is null for vente and delete it
		
	}

	public Ventes findById(Long idVente) {
		return entityManager.find(Ventes.class, idVente);
	}
	
	@Override
	public CaVenteDTO caVente() {
		CaVenteDTO caVenteDTO = new CaVenteDTO();

		String sqlJour = "select sum(vet.montant_total) from ventes vet where vet.date_vente::date between current_date and current_date+1";
		BigDecimal bigInteger = (BigDecimal) entityManager.createNativeQuery(sqlJour).getResultList().get(0);
		if (null != bigInteger) {
			caVenteDTO.setCaJour(bigInteger.intValue());			
		} else {
			caVenteDTO.setCaJour(0);			
		}
		
		String sqlMonth = "select sum(vet.montant_total) from ventes vet where vet.date_vente >= cast(date_trunc('month', current_date) as date) and vet.date_vente < current_date+1 ";
		BigDecimal bigIntegerMois = (BigDecimal) entityManager.createNativeQuery(sqlMonth).getResultList().get(0);
		if (null != bigIntegerMois) {
			caVenteDTO.setCaMois(bigIntegerMois.intValue());
		} else {
			caVenteDTO.setCaMois(0);			
		}
		
		caVenteDTO.setCaAnnee(0);	
		return caVenteDTO;
	}
	
	@Override
	public List<Object[]> popularArticle() {
		String sqlJour = "SELECT sum(quantite) as nb, la.nom_article "
				+ " FROM lignearticle la "
				+ " left join vente_article veta on veta.id_ligne = la.id_ligne "
				+ " left join ventes vet on vet.id_vente = veta.id_vente "
				+ " where vet.date_vente >= cast(date_trunc('month', current_date) as date) "
				+ " and  vet.date_vente < current_date+1 "
				+ " GROUP BY la.nom_article "
				+ " ORDER BY nb DESC";
		return entityManager.createNativeQuery(sqlJour).setMaxResults(3).getResultList();
	}
	
	@Override
	public Integer caVenteEmploye(Long id) {
		String sqlJour = "select sum(vet.montant_total) from ventes vet where vet.employe_id_employe = :id and vet.date_vente::date between current_date and current_date+1";
		BigDecimal bigInteger = (BigDecimal) entityManager.createNativeQuery(sqlJour).
				setParameter("id", id).getResultList().get(0);
		return bigInteger.intValue();
	}
	
	@Override
	public TypeVenteDTO typeVente() {
		// TODO Auto-generated method stub
		return null;
	}
}
