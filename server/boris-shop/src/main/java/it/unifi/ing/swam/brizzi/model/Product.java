package it.unifi.ing.swam.brizzi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@MappedSuperclass
public abstract class Product {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	protected long itemID;
	

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade = CascadeType.ALL)
	protected List<Order> orders;
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}	
	
	public long getItemID() {
		return itemID;
	}

	public void setItemID(long itemID) {
		this.itemID = itemID;
	}
	
	public abstract String getDescription();
	
	public abstract float getTotalPrice();


	
}
