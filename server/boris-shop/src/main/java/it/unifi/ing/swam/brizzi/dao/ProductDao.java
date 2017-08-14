package it.unifi.ing.swam.brizzi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.unifi.ing.swam.brizzi.model.Product;

//senza tag @Model credo non me lo riconosca come bean
// NON USARLO MAI a meno che: The built-in stereotype @Model intended for use with beans that define
//the model layer of an MVC web application architecture such as JSF.
public class ProductDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addProduct(Product p){
			em.persist(p);
	}

	public List<Product> retrieveAllProducts(){

		TypedQuery<Product> query = em.createQuery("from Product ", Product.class);
		List<Product> tmp = query.getResultList();
		return tmp;
	}

	public Integer findQuantityByID(long ID){		
		em.getTransaction().begin();
		Integer tmp =  em.createQuery("SELECT p.quantity FROM Product p WHERE p.itemID = :id", Integer.class)
				.setParameter("id", ID)
				.getSingleResult();
		em.getTransaction().commit();
		return tmp;
	}
	
}
