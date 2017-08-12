package it.unifi.ing.swam.brizzi;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import it.unifi.ing.swam.brizzi.model.Product;


public class JPA_Product {
//	/*@Test
//	public static void main(String args[]) {
//		
////		ProductDao productDao = new ProductDao();
//		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("prova");
//		EntityManager em = emf.createEntityManager();
//		
////		productDao.setEntityManager(em);
//		
//		Product product1 = new Product();
//		//product1.setItemID(0);
//		product1.setDescription("maschera da sub");
//		product1.setPrice(39.8f);
//		product1.setQuantity(20);
//		
//		Product product2 = new Product();
//		//product2.setItemID(1);
//		product2.setDescription("pinne");
//		product2.setPrice(12.8f);
//		product2.setQuantity(40);
//		
////		em.getTransaction().begin();	
////		productDao.addProduct(product1);
////		em.getTransaction().commit();
////
////
////		
//		try {
//			TimeUnit.SECONDS.sleep(5);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
////		em.getTransaction().begin(); //potrebbe non servire !
////		List<Product> tmp = em.createQuery("from Product ", Product.class)
////				.getResultList();
//		TypedQuery<Product> query = em.createQuery("from Product ", Product.class);
//		List<Product> tmp = query.getResultList();
//		
////		em.getTransaction().commit();
//
//		
//		for(Product p: tmp){
//			System.out.println(p.getDescription());
//		}
//		
//		em.close();
//		emf.close();
//		
//	}*/
}
