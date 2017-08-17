package it.unifi.ing.swam.brizzi.bean.mapper;

import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.Product;

public class ProductMapper {

	public ProductDto generateProductTO(Product prod){	
		ProductDto productDTO = new ProductDto();
		productDTO.setDescription(prod.getDescription());
		productDTO.setItemID(prod.getItemID());
		productDTO.setPrice(prod.getPrice());
		productDTO.setQuantity(prod.getQuantity());
		return productDTO;
	}
	
	public Product generateProductFromDTO(ProductDto dto){
		Product product = new Product();
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());
		//non setto itemID perchè bisogna che sia null, essendo la chiave primaria gestita da hibernate
		return product;
	}
	
}