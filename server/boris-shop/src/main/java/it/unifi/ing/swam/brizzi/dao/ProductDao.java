package it.unifi.ing.swam.brizzi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import it.unifi.ing.swam.brizzi.model.Product;

//senza tag @Model credo non me lo riconosca come bean
// NON USARLO MAI a meno che: The built-in stereotype @Model intended for use with beans that define
//the model layer of an MVC web application architecture such as JSF.
//ah ok ! 
public class ProductDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void addProduct(Product p){
			em.persist(p);
	}
	
	@Transactional
	public void updateProduct(Product p, long ID){
		Product oldProduct = em.find(Product.class, ID);
		oldProduct.copyProduct(p);
	}
	
	public List<Product> retrieveAllProducts(){
		List<Product> tmp = new ArrayList<>();
		try{
			TypedQuery<Product> query = em.createQuery("from Product ", Product.class);
			tmp = query.getResultList();
		} catch (Exception e){
			e.printStackTrace();
		}
		return tmp;
	}
	@Transactional
	public int deleteProductByID(long ID){
		int numDeleted = 0;
		try{
//			em.joinTransaction();
			Query query = em.createQuery(
				"DELETE FROM Product p WHERE p.itemID = :id")
				.setParameter("id", ID);
			numDeleted = query.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		return numDeleted;
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
