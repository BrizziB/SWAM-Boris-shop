package it.unifi.ing.swam.brizzi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;

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


}
