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


@Entity
@Table(name="Prodotti")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Product {
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long itemID;
	protected float price;
	@Lob
	protected String description;
	protected Integer quantity;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
	protected List<Order> orders;
	
	public Product(){}
			
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public long getItemID() {
		return itemID;
	}

	public void setItemID(long itemID) {
		this.itemID = itemID;
	}

	
}
