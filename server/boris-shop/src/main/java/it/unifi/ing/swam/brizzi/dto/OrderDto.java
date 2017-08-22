package it.unifi.ing.swam.brizzi.dto;

import java.io.Serializable;
import java.util.List;

import it.unifi.ing.swam.brizzi.model.OrderStatus;
import it.unifi.ing.swam.brizzi.model.Product;
import it.unifi.ing.swam.brizzi.model.User;

public class OrderDto implements Serializable {

	private long orderID;
	private String buyer;
	private String item;
	private OrderStatus status;
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	

}
