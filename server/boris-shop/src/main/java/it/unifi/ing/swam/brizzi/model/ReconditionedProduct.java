package it.unifi.ing.swam.brizzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class ReconditionedProduct extends ProductDecorator {
	
	
	public String conditions;
	
	public ReconditionedProduct(Product prod, String cond) {
		super(prod);
		this.conditions=cond;
	}
	
	@Override
	public float getTotalPrice(){
		System.out.println(product.getTotalPrice() * 0.7 + " ecco il prezzo RICONDIZIONATO   __\n");
		return (float) (product.getTotalPrice() * 0.7);
	}
	
	public ReconditionedProduct(){
		super();
	}
	
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	

}
