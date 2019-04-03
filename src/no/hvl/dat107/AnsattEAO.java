package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class AnsattEAO {

	private EntityManagerFactory emf;

	public AnsattEAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	}
	
	public Ansatt finnAnsattMedID(int ansattID) {

		EntityManager em = emf.createEntityManager();

		Ansatt a1;
		try {
			a1 = em.find(Ansatt.class, ansattID);

		} finally {
			em.close();
		}
		return a1;
	}
	
	public Ansatt finnAnsattPaaNavn(String brukernavn) {

		String queryString = "SELECT a FROM Ansatt a WHERE a.brukernavn = :navn";

		EntityManager em = emf.createEntityManager();

		Ansatt ansatt = null;
		try {
			TypedQuery<Ansatt> query
				= em.createQuery(queryString, Ansatt.class);
			query.setParameter("navn", brukernavn);
			ansatt = query.getSingleResult();

		} catch (NoResultException e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}

		return ansatt;
	}
	
	public boolean leggTilAnsatt(String brukernavn, String fornavn, String etternavn, LocalDate ansettelse_dato,
			String stilling, int maanedslonn, String avdeling) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		boolean fullfort = false;
		try {
			tx.begin();
			
			Ansatt ansatt = new Ansatt(brukernavn, fornavn, etternavn, ansettelse_dato, stilling, maanedslonn, avdeling);
			
			em.persist(ansatt);

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
	
	public void skrivUtTabell() {

		String queryString = "SELECT t FROM Ansatt t ";

		EntityManager em = emf.createEntityManager();

		List<Ansatt> ansatte;
		try {
			TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
			ansatte = query.getResultList();
			for(Ansatt ansatt : ansatte) {
				System.out.println(ansatt);
			}

		} finally {
			em.close();
		}
	}
	public void oppdaterAnsatt(Ansatt ansatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			em.merge(ansatt);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}