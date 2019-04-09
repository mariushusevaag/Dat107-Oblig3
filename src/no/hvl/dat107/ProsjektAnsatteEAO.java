package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProsjektAnsatteEAO {


	
	private EntityManagerFactory emf;

	public ProsjektAnsatteEAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
	}
	
	public void leggTilDeltager(ProsjektAnsatte prosjektAnsatte) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(prosjektAnsatte);
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
	
	public void skrivUtProsjektDeltagerTabell() {

		String queryString = "SELECT t FROM Prosjekt t ";

		EntityManager em = emf.createEntityManager();

		List<ProsjektAnsatte> prosjektDeltagere;
		try {
			TypedQuery<ProsjektAnsatte> query = em.createQuery(queryString, ProsjektAnsatte.class);
			prosjektDeltagere = query.getResultList();
			for(ProsjektAnsatte prosjektAnsatte : prosjektDeltagere) {
				System.out.println(prosjektAnsatte);
			}

		} finally {
			em.close();
		}
	}

}
