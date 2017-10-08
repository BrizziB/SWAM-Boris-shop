package it.unifi.ing.swam.brizzi.model;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class ProductDecorator extends Product{
	
	@OneToOne(targetEntity=Product.class)
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
	
	public Product findRoot(){ //ritorna il BasicProduct decorato con i vari decorator, spero !
		Product prod = getProduct();
		
		if (prod instanceof BasicProduct){
			return prod;
		}
		else return ((ProductDecorator)prod).findRoot();
	}
	
	public ProductDecorator(){
		
	}
	
}
