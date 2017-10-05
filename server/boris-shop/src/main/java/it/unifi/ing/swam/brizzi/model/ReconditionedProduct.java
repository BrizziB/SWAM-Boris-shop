package it.unifi.ing.swam.brizzi.model;

import javax.persistence.Entity;

@Entity
public class ReconditionedProduct extends ProductDecorator {

	public String conditions;
	
	public ReconditionedProduct(Product prod, String cond) {
		super(prod);
		this.conditions=cond;
	}
	
	public ReconditionedProduct(Product prod){
		super(prod);
	}
	
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	

}
