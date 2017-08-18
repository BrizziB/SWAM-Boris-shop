package it.unifi.ing.swam.brizzi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import it.unifi.ing.swam.brizzi.bean.mapper.ProductMapper;
import it.unifi.ing.swam.brizzi.dao.ProductDao;
import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.Product;

public class ProductController {

	@Inject	
	private ProductMapper productMapper;
	@Inject
	private ProductDao productDao;
	
	
	public String retrieveAllProducts(){
		List<ProductDto> prodDTOList = new ArrayList<>();
		List<Product> prodList = productDao.retrieveAllProducts();
		for(Product prod:prodList){
			prodDTOList.add(productMapper.generateProductTO(prod));
		}
		Gson jsonSerializer = new Gson();
		return jsonSerializer.toJson(prodDTOList) ;
		
	}
	
	public void deleteProduct(long productID){
	  int numDeleted = productDao.deleteProductByID(productID);
	  if(numDeleted != 1)
		  System.out.println("error in deleting single product");
	}
	
	public String addProduct(String body){
		Gson gson = new Gson();
		ProductDto productDto = gson.fromJson(body, ProductDto.class);
		Product newProduct = productMapper.generateProductFromDTO(productDto);
		productDao.addProduct(newProduct);
		return gson.toJson(productDto);  //così è un po' finto perchè rispondo con lo stesso oggetto che è era nella request, però in teoria potrei averlo lavorato o aver aggiunto dei campi, quindi credo abbia senso
	}
	
	public String updateProduct(String body){
		Gson gson = new Gson();
		ProductDto productDto = gson.fromJson(body, ProductDto.class);
		Product updatedProduct = productMapper.generateProductFromDTO(productDto);
		long productID = productDto.getItemID();
		productDao.updateProduct(updatedProduct, productID);
		return gson.toJson(productDto);
	}
	
}
