package it.unifi.ing.swam.brizzi.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Ordini")
public class Order {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderID;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User buyer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private BasicProduct item;
	
	private OrderStatus status;
	
	public void copyOrder(Order otherOrder){
		this.buyer = otherOrder.getBuyer();
		this.item = otherOrder.getItem();
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

	public BasicProduct getItem() {
		return item;
	}

	public void setItem(BasicProduct item) {
		this.item = item;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}


}
