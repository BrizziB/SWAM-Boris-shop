package it.unifi.ing.swam.brizzi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runners.model.InitializationError;

public abstract class JpaTest {

	private static EntityManagerFactory emf;
	protected EntityManager em;
	
	@BeforeClass
	public static void setUpClass(){
		emf = Persistence.createEntityManagerFactory("test");
	}
	@Before
	public void setUp() throws InitializationError{
		em = emf.createEntityManager();

		
		em.getTransaction().begin();
		init(); //implementando questo metodo le classi eredi di questa possono inizializzare solo quello che serve, 
		//le inizializzazioni standard sono già fatte, come tutto il procedimento di chiusura
		em.getTransaction().commit();
		em.clear();
		
		em.getTransaction().begin();
		
	}
	
	@After
	public void tearDown(){
		if(em.getTransaction().isActive()){
			em.getTransaction().rollback();
		}
		em.close();
	}
	
	@AfterClass
	public static void tearDownClass(){
		emf.close();
	}
	
	protected abstract void init() throws InitializationError;
	
	
	

}
