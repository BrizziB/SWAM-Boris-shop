package it.unifi.ing.swam.brizzi.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class BasicProduct extends ProductDecorator {

	public BasicProduct(Product prod){
		super(prod);
		}
	
	public BasicProduct(){
		
	}
	
}
