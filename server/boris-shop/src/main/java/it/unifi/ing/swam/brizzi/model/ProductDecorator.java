package it.unifi.ing.swam.brizzi.model;

import javax.persistence.Entity;
import javax.persistence.Transient;


public class ProductDecorator extends Product{
	
	
	protected Product product;
	
	public ProductDecorator(Product prod){
		this.product = prod;
	}

	@Override
	public String getDescription(){
		return product.getDescription();
	}
	
	@Override
	public float getTotalPrice() {
		return product.getTotalPrice();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	public ProductDecorator(){
		
	}
	
}
