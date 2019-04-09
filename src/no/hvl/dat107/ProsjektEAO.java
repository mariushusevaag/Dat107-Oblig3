package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
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

	public void leggTilAnsatt(Ansatt ansatt, Prosjekt prosjekt) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			ProsjektAnsatte pa = new ProsjektAnsatte(ansatt, prosjekt);
			prosjekt.getpDListe().add(pa);

			em.persist(pa);

			tx.commit();

		} catch(RollbackException e) {
			//commit failed
			//FE: Unique constraint

		} catch(Throwable e) {
			e.printStackTrace();
			tx.rollback();

		} finally {
			em.close();
		}
	}

	public boolean leggTilAnsatt(String brukerNavn, String forNavn, String etterNavn, LocalDate ansettelseDato,
			String stilling, int maanedslonn, int avdelingNr) {

			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();

			boolean gikkdet = false;
			try {
				tx.begin();

				Avdeling avdeling = em.find(Avdeling.class, avdelingNr);
				Ansatt ansatt = new Ansatt(brukerNavn, forNavn, etterNavn, ansettelseDato, stilling, maanedslonn, avdeling.getAvdeling_navn());

				em.persist(ansatt);

				avdeling.addAnsatt(ansatt);

				tx.commit();
				gikkdet = true;
			} catch(RollbackException e) {

				//commit failed
				//FE: Unique constraint

			} catch(Exception e) {

				e.printStackTrace();
				tx.rollback();

			} finally {
				em.close();
			}
			return gikkdet;
		}

	public void setRolle(Ansatt ansatt, Prosjekt prosjekt, String rolle) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			ProsjektAnsatte pa = finnProsjektAnsatte(ansatt.getAnsatt_id(), prosjekt.getProsjekt_id());
			pa.setRolle(rolle);

			em.merge(prosjekt);
			em.merge(pa);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	private ProsjektAnsatte finnProsjektAnsatte(int ansatt_id, int prosjekt_id) {
		String queryString = "SELECT pa FROM ProsjektAnsatte pa WHERE pa.ansatt.ansatt_id = :aId AND pa.prosjekt.prosjekt_id = :pId";

		EntityManager em = emf.createEntityManager();

		ProsjektAnsatte pd = null;
		try {
			TypedQuery<ProsjektAnsatte> query = em.createQuery(queryString, ProsjektAnsatte.class);
			query.setParameter("aId", ansatt_id);
			query.setParameter("pId", prosjekt_id);
			pd = query.getSingleResult();

		} catch (NoResultException e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return pd;
	}

	public void leggTilTimer(Ansatt ansatt, Prosjekt prosjekt, BigDecimal antallTimer) {
		ProsjektAnsatte pa = finnProsjektAnsatte(ansatt.getAnsatt_id(), prosjekt.getProsjekt_id());
		setTimer(ansatt, prosjekt, antallTimer.add(pa.getTimer()));
	}
	
	public void setTimer(Ansatt ansatt, Prosjekt prosjekt, BigDecimal antallTimer) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			ProsjektAnsatte pa = finnProsjektAnsatte(ansatt.getAnsatt_id(), prosjekt.getProsjekt_id());
			pa.setTimer(antallTimer);

			em.merge(prosjekt);
			em.merge(pa);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}
