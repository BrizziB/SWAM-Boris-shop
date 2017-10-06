package it.unifi.ing.swam.brizzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class PromoProduct extends ProductDecorator {
	
	private float discount;

	public PromoProduct(){
		super();
	}
	
	public PromoProduct(Product prod, float discount) {
		super(prod);
		this.discount = discount;
	}
	
	@Override
	public float getTotalPrice(){
		System.out.println(product.getTotalPrice()*(1-discount) + " ecco il prezzo PROMO   __\n");
		return product.getTotalPrice()*(1-discount);
	}
	
		
	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	

}
