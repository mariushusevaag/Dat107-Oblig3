package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class ProsjektEAO {

	private EntityManagerFactory emf;

	public ProsjektEAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	}
	
	public Prosjekt finnProsjekttMedID(int prosjekt_id) {

		EntityManager em = emf.createEntityManager();

		Prosjekt p1;
		try {
			p1 = em.find(Prosjekt.class, prosjekt_id);

		} finally {
			em.close();
		}
		return p1;
	}
	
	public Prosjekt finnProsjektPaaNavn(String prosjekt_navn) {

		String queryString = "SELECT p FROM Prosjekt p WHERE p.prosjekt_navn = :navn";

		EntityManager em = emf.createEntityManager();

		Prosjekt prosjekt = null;
		try {
			TypedQuery<Prosjekt> query
				= em.createQuery(queryString, Prosjekt.class);
			query.setParameter("navn", prosjekt_navn);
			prosjekt = query.getSingleResult();

		} catch (NoResultException e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}

		return prosjekt;
	}
	
	public boolean leggTilProsjekt(String prosjekt_navn, String prosjekt_beskrivelse) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		boolean fullfort = false;
		try {
			tx.begin();
			
			Prosjekt prosjekt = new Prosjekt(prosjekt_navn, prosjekt_beskrivelse);
			
			em.persist(prosjekt);

			tx.commit();
			fullfort = true;
		} catch (NoResultException e) {
		} catch(RollbackException e) {

			//commit failed
			//FE: Unique constraint

		} catch(Exception e) {

			e.printStackTrace();
			tx.rollback();

		} finally {
			em.close();
		}
		return fullfort;
	}
	
	public void skrivUtProsjektTabell() {

		String queryString = "SELECT t FROM Prosjekt t ";

		EntityManager em = emf.createEntityManager();

		List<Prosjekt> prosjekter;
		try {
			TypedQuery<Prosjekt> query = em.createQuery(queryString, Prosjekt.class);
			prosjekter = query.getResultList();
			for(Prosjekt prosjekt : prosjekter) {
				System.out.println(prosjekt);
			}

		} finally {
			em.close();
		}
	}
	public void oppdaterProsjekt(Prosjekt prosjekt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.merge(prosjekt);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
