package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domaine.Person;
import domaine.Residence;

public class ResidenceJpa {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");

	private EntityManager manager = factory.createEntityManager();
   
	private EntityTransaction tx = manager.getTransaction();

	public void createResidence(Residence Residence) {
		tx.begin();
		manager.persist(Residence);
		tx.commit();
	}

	public List<Residence> getResidenceAll() {
		return manager.createQuery("Select a From Residence a", Residence.class).getResultList();
	}

	public Residence getResidenceBy(long id) {
		return manager.find(Residence.class, id);
	}

	public void removeResidence(int id) {
		Residence resid = getResidenceBy(id);
		if (resid != null) {
			manager.remove(resid);
		}
	}

	public void updatePerson(Residence Residence) {
		Residence resid = getResidenceBy(Residence.getId());
		if (resid != null) {
			manager.merge(Residence);
		}
	}

	public static void main(String[] args) {
		ResidenceJpa rjp = new ResidenceJpa();
		/*System.out.println(pjp.getResidenceBy(1L).getNameResidence());*/
		
		PersonJpa personJpa = new PersonJpa();
		Person aubin= personJpa.getPersonBy(1L);
		Residence residence = new Residence("Appart", aubin, null);
		rjp.createResidence(residence);
		
	}

}
