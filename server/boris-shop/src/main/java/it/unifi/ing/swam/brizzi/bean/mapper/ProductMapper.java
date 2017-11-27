package it.unifi.ing.swam.brizzi.bean.mapper;

import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.BasicProduct;
import it.unifi.ing.swam.brizzi.model.Product;
import it.unifi.ing.swam.brizzi.model.PromoProduct;
import it.unifi.ing.swam.brizzi.model.ReconditionedProduct;

public class ProductMapper {
	

	public ProductDto generateBasicProductTO(Product prod){	
		ProductDto productDTO = new ProductDto();
		productDTO.setDescription(((BasicProduct) prod).getDescription());
		productDTO.setItemID(((BasicProduct) prod).getItemID());
		productDTO.setPrice(((BasicProduct) prod).getPrice());
		productDTO.setQuantity(((BasicProduct) prod).getQuantity());
		productDTO.setProductLinker(prod.getProductLinker());
		return productDTO;
	}
		
	public ProductDto decorateProductTO(ProductDto baseProd, Product decoration){
		
		if(decoration instanceof PromoProduct){
			baseProd.setDiscount(((PromoProduct) decoration).getDiscount());
		}
		else if(decoration instanceof ReconditionedProduct){
			baseProd.setConditions(((ReconditionedProduct) decoration).getConditions());
		}
		return baseProd;
	}
		
	public Product generateProductFromDTO(ProductDto productDto){
		BasicProduct product = new BasicProduct();
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setQuantity(productDto.getQuantity());
		product.setProductLinker(productDto.getProductLinker());
		Product newProduct;
		if(productDto.getDiscount()>0 && productDto.getConditions()!=null){//è un prodotto ricondizionato in promo
			newProduct = new PromoProduct(new ReconditionedProduct(product, productDto.getConditions()), productDto.getDiscount());
		}
		else if(productDto.getDiscount()>0){// è un prodotto in promo
			product.setPrice(product.getPrice() * (1-productDto.getDiscount()));
			productDto.setPrice(product.getPrice());
			newProduct = new PromoProduct(product, productDto.getDiscount());
		}
		else if(productDto.getConditions()!=null){// è un prodotto ricondizionato
			newProduct = new ReconditionedProduct(product, productDto.getConditions());
		}
		else{ //prodotto base
			 newProduct = product;
		}		
		return newProduct;	
	}
	
}