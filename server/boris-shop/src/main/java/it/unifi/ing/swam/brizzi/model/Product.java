package it.unifi.ing.swam.brizzi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="Prodotti")
public class Product {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long itemID;
	private float price;
	@Lob
	private String description;
	private Integer quantity;
	
	public Product(){}

	public void copyProduct(Product foreignProduct){
		this.price = foreignProduct.getPrice();
		this.description = foreignProduct.getDescription();
		this.quantity = foreignProduct.getQuantity();
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
