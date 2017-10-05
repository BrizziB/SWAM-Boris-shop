package it.unifi.ing.swam.brizzi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;

import it.unifi.ing.swam.brizzi.bean.mapper.ProductMapper;
import it.unifi.ing.swam.brizzi.dao.OrderDao;
import it.unifi.ing.swam.brizzi.dao.ProductDao;
import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.Product;
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
		List<Product> prodList = productDao.retrieveAllProducts();
		for(Product prod:prodList){
			prodDTOList.add(productMapper.generateProductTO(prod));
		}
		Gson jsonSerializer = new Gson();
		return jsonSerializer.toJson(prodDTOList) ;

	}

	public void deleteProduct(long productID){
		
		orderDao.deleteOrdersByProductID(productID); //nel caso sia in qualche ordine si propaga la cancellazione
		
		int numDeleted = productDao.deleteProductByID(productID);
		if(numDeleted != 1)
			System.out.println("error in deleting single product");
	}

	public String addProduct(String body)throws Exception{
		Gson gson = new Gson();
		ProductDto productDto = gson.fromJson(body, ProductDto.class);
		Product newProduct;
		
		// --- --- --- UNDER TESTING --- --- --- 
		// non lo lascio così, poi credo che farò uno strategy che decida quale tipo di Product istanziare, per ora è per test 		
		//prova validazione input
		Product product = productMapper.generateProductFromDTO(productDto);
		if(productDto.getPrice()<0 || productDto.getQuantity()<0){
			throw new Exception("prezzi o quantità non consentiti");
		}
		// prova uso del decorator -- problemi
		if(productDto.getDiscount()>0 && productDto.getConditions()!=null){//è un prodotto ricondizionato in promo
			newProduct = new PromoProduct(new ReconditionedProduct(product, productDto.getConditions()), productDto.getDiscount());
		}
		else if(productDto.getDiscount()>0){// è un prodotto in promo
			newProduct = new PromoProduct(product, productDto.getDiscount());
		}
		else if(productDto.getConditions()!=null){// è un prodotto ricondizionato
			newProduct = new ReconditionedProduct(product, productDto.getConditions());
		}
		else{ //prodotto base
			 newProduct = product;
		}
		// --- --- --- UNDER TESTING --- --- --- 
		
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
