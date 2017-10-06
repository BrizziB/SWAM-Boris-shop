package it.unifi.ing.swam.brizzi.dao;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.ing.swam.brizzi.model.BasicProduct;
import it.unifi.ing.swam.brizzi.model.Product;
import it.unifi.ing.swam.brizzi.model.PromoProduct;
import it.unifi.ing.swam.brizzi.model.User;

public class ProjectDaoJpaTest extends JpaTest {
	
	private ProductDao productDao;
	private Product product;
	

	@Override
	protected void init() throws InitializationError {
		productDao = new ProductDao();
		try{
			FieldUtils.writeField(productDao,  "em",  em, true);
		}catch (IllegalAccessException e){
			throw new InitializationError(e);
		}
	}
	@Test
	public void testSaveBasic(){
		BasicProduct p = new BasicProduct();
		p.setDescription("prova_1");
		p.setPrice((float) 11.30);
		p.setQuantity(12);
		
		
		productDao.addProduct(p);
		
		Product persistedProduct = em.createQuery("from Product where description=:description", Product.class)
								.setParameter("description", p.getDescription()).getSingleResult();
		assertEquals(p, persistedProduct );
		
	}
	
	@Test
	public void testSaveDecorated(){
		BasicProduct b = new BasicProduct();
		
		b.setDescription("prova_1");
		b.setPrice((float) 11.30);
		b.setQuantity(12);
		
		float discount = (float) 0.2;
		
		PromoProduct p = new PromoProduct(b, discount);
		
			
		//salva sul DB il prodotto
		productDao.addProduct(p);
		
		
		Product persistedProduct = em.createQuery("from Product where discount=:discount", Product.class)
								.setParameter("discount", p.getDiscount()).getSingleResult();
		
		//controlla che sia stato persistito nel modo giusto
		assertEquals(p, persistedProduct );
		
	}
	
//	@Test
//	public void testUpdate(){
//		Product newProduct = new Product();
//		newProduct.setDescription("nuovo");
//		newProduct.setPassword("nuova");
//		
//		em.persist(newProduct);
//		
//		newProduct = em.createQuery("from Product where username=:username", Product.class)
//				.setParameter("username", newProduct.getUsername()).getSingleResult();
//		
//		newProduct.setPassword("newPassword");
//		
//		userDao.updateUser(newProduct, newProduct.getUserID());
//		
//		Product persistedUser = em.find(Product.class, newProduct.getUserID());
//		
//		assertEquals ("newPassword", persistedUser.getPassword());
//	}
//	
//	@Test
//	public void testDelete(){
//		User newUser = new User();
//		newUser.setUsername("nuovo");
//		newUser.setPassword("nuova");
//		
//		em.persist(newUser);
//		
//		long userID = em.createQuery("from User where username=:username", User.class)
//		.setParameter("username", newUser.getUsername()).getSingleResult().getUserID();
//		
//		int numDeleted = userDao.deleteUserByID(userID);
//		
//		assertEquals(numDeleted, 1);
//
//		
//	}
}
