package it.unifi.ing.swam.brizzi.model;

import javax.persistence.Entity;

@Entity
public class PromoProduct extends ProductDecorator {
	
	private float discount;

	public PromoProduct(Product prod, float discount) {
		super(prod);
		this.discount = discount;
	}
	
	public PromoProduct(Product prod){
		super(prod);
	}
	
		
	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	

}
