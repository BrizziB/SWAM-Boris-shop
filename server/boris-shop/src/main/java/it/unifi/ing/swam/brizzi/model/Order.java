package it.unifi.ing.swam.brizzi.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.unifi.ing.swam.brizzi.model.Product;

@Entity
@Table(name="Ordini")
public class Order {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderID;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User buyer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Product item;
	
	private OrderStatus status;
	
	public void copyOrder(Order otherOrder){
		this.buyer = otherOrder.getBuyer();
		this.item =  otherOrder.getItem();
		this.status = otherOrder.getStatus();
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Product getItem() {
		return item;
	}

	public void setItem(Product item) {
		this.item =  item;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}


}
