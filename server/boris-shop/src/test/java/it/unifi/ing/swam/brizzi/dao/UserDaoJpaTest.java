package it.unifi.ing.swam.brizzi.dao;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import it.unifi.ing.swam.brizzi.model.User;

public class UserDaoJpaTest extends JpaTest {
	
	
	private UserDao userDao;
	private User user;

	@Override
	protected void init() throws InitializationError {		
		userDao = new UserDao();
		try {
			FieldUtils.writeField(userDao, "em", em, true);
		}catch (IllegalAccessException e){
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testSave(){
		User u = new User();
		u.setUsername("altroNome");
		u.setPassword("altraPassword");
		
		userDao.addUser(u);
		
		User persistedUser = em.createQuery("from User where username=:username", User.class)
								.setParameter("username", u.getUsername()).getSingleResult();
		assertEquals(u, persistedUser );
		
	}
	
	@Test
	public void testUpdate(){
		User newUser = new User();
		newUser.setUsername("nuovo");
		newUser.setPassword("nuova");
		
		em.persist(newUser);
		
		newUser = em.createQuery("from User where username=:username", User.class)
				.setParameter("username", newUser.getUsername()).getSingleResult();
		
		newUser.setPassword("newPassword");
		
		userDao.updateUser(newUser, newUser.getUserID());
		
		User persistedUser = em.find(User.class, newUser.getUserID());
		
		assertEquals ("newPassword", persistedUser.getPassword());
	}
	
	@Test
	public void testDelete(){
		User newUser = new User();
		newUser.setUsername("nuovo");
		newUser.setPassword("nuova");
		
		em.persist(newUser);
		
		long userID = em.createQuery("from User where username=:username", User.class)
		.setParameter("username", newUser.getUsername()).getSingleResult().getUserID();
		
		int numDeleted = userDao.deleteUserByID(userID);
		
		assertEquals(numDeleted, 1);

		
	}
	
	
	
	
	
}
