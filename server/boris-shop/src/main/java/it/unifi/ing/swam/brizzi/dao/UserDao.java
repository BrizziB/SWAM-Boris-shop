package it.unifi.ing.swam.brizzi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import it.unifi.ing.swam.brizzi.model.User;

public class UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void addUser(User u){
			em.persist(u);
	}
	
	@Transactional
	public void updateUser(User u, long ID){
		User oldUser = em.find(User.class, ID);
		oldUser.copyUser(u);
	}
	
	public List<User> retrieveAllUsers(){
		TypedQuery<User> query = em.createQuery("from User ", User.class);
		List<User> tmp = query.getResultList();
		return tmp;
	}
	
	@Transactional
	public int deleteUserByID(long ID){
		int numDeleted = 0;
		try{
			Query query = em.createQuery(
				"DELETE FROM User u WHERE u.userID = :id")
				.setParameter("id", ID);
			numDeleted = query.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		return numDeleted;
	}

}
