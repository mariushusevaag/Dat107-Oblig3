package no.hvl.dat107;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
