package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

public class ProsjektAnsatteEAO {


	
	private EntityManagerFactory emf;

	public ProsjektAnsatteEAO() {
		emf = Persistence.createEntityManagerFactory("AnsattPersistenceUnit");
	}
	
	public boolean leggTilDeltager(int p_id, int a_id, String rolle, double timer) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		boolean fullfort = false;
		try {
			tx.begin();
			
			ProsjektAnsatte pa = new ProsjektAnsatte(p_id, a_id, rolle, timer);
			
			em.persist(pa);

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
