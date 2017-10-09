package it.unifi.ing.swam.brizzi.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;

import it.unifi.ing.swam.brizzi.bean.mapper.ProductMapper;
import it.unifi.ing.swam.brizzi.dao.OrderDao;
import it.unifi.ing.swam.brizzi.dao.ProductDao;
import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.BasicProduct;
import it.unifi.ing.swam.brizzi.model.Product;
import it.unifi.ing.swam.brizzi.model.ProductDecorator;
import it.unifi.ing.swam.brizzi.model.PromoProduct;
import it.unifi.ing.swam.brizzi.model.ReconditionedProduct;

public class ProductController {

	@Inject	
	private ProductMapper productMapper;
	@Inject
	private ProductDao productDao;
	@Inject
	private OrderDao orderDao;


	public String retrieveAllProducts(){
		List<ProductDto> prodDTOList = new ArrayList<>();
		List<BasicProduct> basicProducts = new ArrayList<>();
		List<Product> decoratedProducts = new ArrayList<>();
		productDao.retrieveAllProducts(basicProducts, decoratedProducts);
		
		ProductDto tmpDto;
		//ora il numero di iterazioni è fissato a num(basicProducts)*num(decoratedProducts) 
		//si potrebbe migliorare implementando il secondo for con un iteratore e togliendo 
		//gli elementi mano mano che vengono trovati..
		for(Product prod : basicProducts){
			tmpDto = productMapper.generateBasicProductTO(prod);
			for(Product decoratedProd : decoratedProducts){
				if(decoratedProd.getProductLinker() == prod.getProductLinker()){
					tmpDto = productMapper.decorateProductTO(tmpDto, decoratedProd);
				}
			}
			prodDTOList.add(tmpDto);
		}
		Gson jsonSerializer = new Gson();
		return jsonSerializer.toJson(prodDTOList) ;

	}

	public void deleteProduct(long itemID, int productLinker){
		orderDao.deleteOrdersByProductID(itemID); //nel caso sia in qualche ordine si propaga la cancellazione
		productDao.deleteDecoratedProduct(productLinker);

	}

	public String addProduct(String body)throws Exception{
		Gson gson = new Gson();
		ProductDto productDto = gson.fromJson(body, ProductDto.class);
		if(productDto.getPrice()<0 || productDto.getQuantity()<0){
			throw new Exception("prezzi o quantità non consentiti");
		}
		productDto.setProductLinker(productDto.hashCode());
		Product product = productMapper.generateProductFromDTO(productDto);	
		productDao.addProduct(product);
		return gson.toJson(productDto);  //così è un po' finto perchè rispondo con lo stesso oggetto che è era nella request, però in teoria potrei averlo lavorato o aver aggiunto dei campi, quindi credo abbia senso
	}

	public String updateProduct(String body){
		Gson gson = new Gson();
		ProductDto productDto = gson.fromJson(body, ProductDto.class);
		
		Product updatedProduct = productMapper.generateProductFromDTO(productDto);
		
		int productLinker = productDto.getProductLinker();
		productDao.updateProduct(productDto);	
		
		
		return gson.toJson(productDto);
	}

}










