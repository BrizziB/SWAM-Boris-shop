package it.unifi.ing.swam.brizzi.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;
import org.mockito.Mockito;

import com.google.gson.Gson;

import it.unifi.ing.swam.brizzi.bean.mapper.ProductMapper;
import it.unifi.ing.swam.brizzi.dao.ProductDao;
import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.BasicProduct;
import it.unifi.ing.swam.brizzi.model.Product;
import it.unifi.ing.swam.brizzi.model.PromoProduct;
import it.unifi.ing.swam.brizzi.model.ReconditionedProduct;



public class ProductControllerTest {

	private ProductController productController;
	private ProductDao productDao;
	private ProductMapper productMapper;
	
	private BasicProduct product1;
	private Product product2;
	private ArrayList<Product> allDecoratedProducts;
	private ArrayList<Product> allBasicProducts;
	private List<ProductDto> allProductsDto;
	private List<ArrayList<Product>> allProducts;

	
	
	@Before
	public void setUp() throws InitializationError{
		
		productController = new ProductController();
		productMapper = new ProductMapper();
		productDao = mock(ProductDao.class);
		product1 = new BasicProduct();
		product1.setProductLinker(0);
		Product tmpProd1 = new BasicProduct();
		tmpProd1.setProductLinker(1);
		Product tmpProd2 = new PromoProduct(tmpProd1, (float) 0.5);
		tmpProd2.setProductLinker(1);
		product2 = new ReconditionedProduct(tmpProd2, "medie");
		product2.setProductLinker(1);

		
		allDecoratedProducts = new ArrayList<>();
		allBasicProducts = new ArrayList<>();
		allProductsDto = new ArrayList<>();

		
		try{

			FieldUtils.writeField(product1, "quantity", 10 , true); 
			FieldUtils.writeField(product1, "description", "product1" , true);
			FieldUtils.writeField(product1, "price", (float)10.9 , true);
			
			FieldUtils.writeField(tmpProd1, "quantity", 9 , true); 
			FieldUtils.writeField(tmpProd1, "description", "tmpProd1" , true);
			FieldUtils.writeField(tmpProd1, "price", (float)0.1 , true);

			
			
			FieldUtils.writeField(productController, "productDao", productDao, true); //collego il mock del Dao alla classe da testare
			FieldUtils.writeField(productController, "productMapper", productMapper, true);
			
			allBasicProducts.add(product1);
			allBasicProducts.add(tmpProd1);
			
			allDecoratedProducts.add(tmpProd2);
			allDecoratedProducts.add(product2);
			allProductsDto.add(productMapper.generateBasicProductTO(product1));
			
			ProductDto product2Dto = productMapper.generateBasicProductTO(tmpProd1);
			productMapper.decorateProductTO(product2Dto, tmpProd2);
			productMapper.decorateProductTO(product2Dto, product2);
			allProductsDto.add(product2Dto);
			
			allProducts = new ArrayList<ArrayList<Product>>();
			allProducts.add(allBasicProducts);
			allProducts.add(allDecoratedProducts);
			
		}catch (IllegalAccessException e){
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void getAllUsers() throws InitializationError{
		Mockito.doReturn(allProducts)
			.when(productDao).retrieveAllProducts();
		
		Gson gson = new Gson();
		assertEquals(gson.toJson(allProductsDto), productController.retrieveAllProducts());
			
	}

}