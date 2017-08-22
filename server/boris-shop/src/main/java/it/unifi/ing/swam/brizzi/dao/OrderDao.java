package it.unifi.ing.swam.brizzi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import it.unifi.ing.swam.brizzi.model.Order;
import it.unifi.ing.swam.brizzi.model.OrderStatus;
import it.unifi.ing.swam.brizzi.model.Product;

public class OrderDao {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void addOrder(Order o){
			em.persist(o);
	}
	
	@Transactional
	public void updateOrder(Order o, long ID){
		Order oldOrder = em.find(Order.class, ID);
		oldOrder.copyOrder(o);
	}
	
	public List<Order> retrieveAllOrders(){
		List<Order> tmp = new ArrayList<>();
		try{
			TypedQuery<Order> query = em.createQuery("from Order ", Order.class);
			tmp = query.getResultList();
		} catch (Exception e){
			e.printStackTrace();
		}
		return tmp;
	}
	@Transactional
	public int deleteOrderByID(long ID){
		int numDeleted = 0;
		try{
//			em.joinTransaction();
			Query query = em.createQuery(
				"DELETE FROM Order o WHERE o.orderID = :id")
				.setParameter("id", ID);
			numDeleted = query.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		return numDeleted;
	}	
	
	public OrderStatus findStatusByID(long ID){		
		em.getTransaction().begin();
		OrderStatus tmp =  em.createQuery("SELECT o.status FROM Order o WHERE o.orderID = :id", OrderStatus.class)
				.setParameter("id", ID)
				.getSingleResult();
		em.getTransaction().commit();
		return tmp;
	}

}
