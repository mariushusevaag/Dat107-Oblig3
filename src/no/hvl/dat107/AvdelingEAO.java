package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AvdelingEAO {

	private EntityManagerFactory emf;

	public AvdelingEAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	}
	
	public Avdeling finnAvdelingMedID(int avdelingID) {

		EntityManager em = emf.createEntityManager();

		Avdeling a1;
		try {
			a1 = em.find(Avdeling.class, avdelingID);

		} finally {
			em.close();
		}
		return a1;
	}
	
	public Avdeling finnAvdelingPaaNavn(String navn) {

		String queryString = "SELECT a FROM Avdeling a WHERE a.avdelingsnavn = :navn";

		EntityManager em = emf.createEntityManager();

		Avdeling avdeling = null;
		try {
			TypedQuery<Avdeling> query
				= em.createQuery(queryString, Avdeling.class);
			query.setParameter("navn", navn);
			avdeling = query.getSingleResult();

		} catch(NoResultException e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}

		return avdeling;
	}
	
	public List<Ansatt> alleAnsatteVedAvdeling(String navn) {
		String queryString = "SELECT a FROM Ansatt a WHERE a.avdeling = :navn";
		
		EntityManager em = emf.createEntityManager();
		List<Ansatt> ansatte = null;

		try {
			TypedQuery<Ansatt> query 
			= em.createQuery(queryString, Ansatt.class);
			query.setParameter("navn", navn);
			ansatte = query.getResultList();
		} catch (NoResultException e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}

		return ansatte;
	}
	
	public void oppdaterAvdelingForAnsatt(Ansatt ansatt) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(ansatt);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public void leggTilNyAvdeling(Avdeling avdeling) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		

		try {
			tx.begin();
			em.persist(avdeling);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
}
