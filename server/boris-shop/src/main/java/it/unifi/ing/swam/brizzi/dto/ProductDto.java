package it.unifi.ing.swam.brizzi.dto;

import java.io.Serializable;

//per ora non c'è niente

public class ProductDto implements Serializable {

	private long itemID;
	private float price;
	private String description;
	private Integer quantity;
	private String conditions;
	private float discount;
	private int productLinker;
	
	public int getProductLinker() {
		return productLinker;
	}
	public void setProductLinker(int productLinker) {
		this.productLinker = productLinker;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
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
