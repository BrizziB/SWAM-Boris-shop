package it.unifi.ing.swam.brizzi.dto;

import java.io.Serializable;

//per ora non c'è niente

public class ProductDto implements Serializable {

	private long itemID;
	private float price;
	private String description;
	private Integer quantity;
	
	public long getItemID() {
		return itemID;
	}
	public void setItemID(long itemID) {
		this.itemID = itemID;
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
}
