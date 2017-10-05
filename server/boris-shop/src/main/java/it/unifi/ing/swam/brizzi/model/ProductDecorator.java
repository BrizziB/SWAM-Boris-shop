package it.unifi.ing.swam.brizzi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


public class ProductDecorator extends Product{
	
	
	protected Product product;
	
	public ProductDecorator(Product prod){
		this.product = prod;
	}
	
	public ProductDecorator(){
		
	}
	
}
