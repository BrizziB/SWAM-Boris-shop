package it.unifi.ing.swam.brizzi.bean.mapper;

import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.BasicProduct;
import it.unifi.ing.swam.brizzi.model.Product;
import it.unifi.ing.swam.brizzi.model.PromoProduct;
import it.unifi.ing.swam.brizzi.model.ReconditionedProduct;

public class ProductMapper {

	public ProductDto generateProductTO(Product prod){	
		ProductDto productDTO = new ProductDto();
		
		if(prod instanceof BasicProduct){
		productDTO.setDescription(((BasicProduct) prod).getDescription());
		productDTO.setItemID(((BasicProduct) prod).getItemID());
		productDTO.setPrice(((BasicProduct) prod).getPrice());
		productDTO.setQuantity(((BasicProduct) prod).getQuantity());
		}
		
		//questo forse si potrebbe sostituire con un facade?.. vedi un po'
		
		if(prod instanceof ReconditionedProduct){
			productDTO.setConditions(((ReconditionedProduct) prod).getConditions());
		}
		else productDTO.setConditions(null);
		
		if(prod instanceof PromoProduct){
			productDTO.setDiscount(((PromoProduct) prod).getDiscount());
		}
		else productDTO.setDiscount(0);

		return productDTO;
	}
	
//	public ProductDto generateProductTO(PromoProduct prod){	
//		ProductDto productDTO = new ProductDto();
//		productDTO.setDescription(prod.getDescription());
//		productDTO.setItemID(prod.getItemID());
//		productDTO.setPrice(prod.getPrice());
//		productDTO.setQuantity(prod.getQuantity());
//		productDTO.setDiscount(prod.getDiscount());
//		productDTO.setConditions(null);
//		return productDTO;
//	}
//	
//	public ProductDto generateProductTO(ReconditionedProduct prod){	
//		ProductDto productDTO = new ProductDto();
//		productDTO.setDescription(prod.getDescription());
//		productDTO.setItemID(prod.getItemID());
//		productDTO.setPrice(prod.getPrice());
//		productDTO.setQuantity(prod.getQuantity());
//		productDTO.setConditions(prod.getConditions());
//		productDTO.setDiscount(0);
//		return productDTO;
//	}
	
	public Product generateProductFromDTO(ProductDto dto){
		BasicProduct prod = new BasicProduct();
		prod.setDescription(dto.getDescription());
		prod.setPrice(dto.getPrice());
		prod.setQuantity(dto.getQuantity());
		
//		if(dto.getConditions()!=null){
//			ReconditionedProduct product = new ReconditionedProduct(prod);
//			product.setConditions(dto.getConditions());
//			return product;
//		}
//		
//		else if(dto.getDiscount() > 0){
//			PromoProduct product = new PromoProduct(prod);
//			return product;
//		}
		return prod;	
	}
	
}