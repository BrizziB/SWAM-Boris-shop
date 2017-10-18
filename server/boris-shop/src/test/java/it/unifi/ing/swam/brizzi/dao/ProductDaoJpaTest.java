package it.unifi.ing.swam.brizzi.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.ing.swam.brizzi.bean.mapper.ProductMapper;
import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.BasicProduct;
import it.unifi.ing.swam.brizzi.model.Product;
import it.unifi.ing.swam.brizzi.model.PromoProduct;
import it.unifi.ing.swam.brizzi.model.ReconditionedProduct;
import it.unifi.ing.swam.brizzi.model.User;

public class ProductDaoJpaTest extends JpaTest {
	
	private ProductDao productDao;
	private ProductMapper productMapper;
	private Product product;
	

	@Override
	protected void init() throws InitializationError {
		productMapper = new ProductMapper();
		productDao = new ProductDao();
		try{
			FieldUtils.writeField(productDao,  "em",  em, true);
		}catch (IllegalAccessException e){
			throw new InitializationError(e);
		}
	}
	
	
	@Test
	public void testRetrieveDecorated(){
		BasicProduct bp = new BasicProduct();
		bp.setDescription("prova_1");
		bp.setPrice((float) 11.30);
		bp.setQuantity(12);
		
		PromoProduct pp = new PromoProduct(bp, (float) 0.35);
		
		ReconditionedProduct product = new ReconditionedProduct(bp, "good");
		
		int productLinker = product.hashCode();
		product.setProductLinker(productLinker);
		
		productDao.addProduct(product);
		
		List<Product> retrievedProduct;
		
		retrievedProduct = productDao.getDecoratedProductByLinker(productLinker);

		
		boolean differenceFound = false;
		
		for(Product p : retrievedProduct){
			if (p instanceof BasicProduct){
				
				if(p.getDescription() != ((BasicProduct) p).getDescription() ||
						((BasicProduct) p).getPrice() != bp.getPrice() ||
						((BasicProduct) p).getQuantity() != bp.getQuantity())
				{
					differenceFound = true;
				}
			}
			else if(p instanceof PromoProduct){
				if(((PromoProduct) p).getDiscount() != pp.getDiscount()){
					differenceFound = true;
				}
			}
			else if(p instanceof ReconditionedProduct){
				if(((ReconditionedProduct) p).getConditions() != product.getConditions()){
					differenceFound = true;
				}
			}
		}
		assertFalse(differenceFound);
		
		
		
	}
	
	@Test
	public void testSaveBasic(){
		BasicProduct p = new BasicProduct();
		p.setDescription("prova_1");
		p.setPrice((float) 11.30);
		p.setQuantity(12);
		
		
		productDao.addProduct(p);
		
		Product persistedProduct = em.createQuery("from Product where description=:description", Product.class)
								.setParameter("description", p.getDescription()).getSingleResult();
		assertEquals(p, persistedProduct );
		
	}
	
	@Test
	public void testSaveDecorated(){
		BasicProduct b = new BasicProduct();
		
		b.setDescription("prova_1");
		b.setPrice((float) 11.30);
		b.setQuantity(12);
		
		float discount = (float) 0.2;
		
		PromoProduct p = new PromoProduct(b, discount);
		
			//salva sul DB il prodotto
		productDao.addProduct(p);
		Product persistedProduct = em.createQuery("from PromoProduct ", Product.class)
								.getSingleResult();
		
		//controlla che sia stato persistito nel modo giusto
		assertEquals(p, persistedProduct );
		
	}
	
	@Test
	public void testUpdateBasic(){
		BasicProduct newProduct = new BasicProduct();
		newProduct.setDescription("nuovo");
		newProduct.setQuantity(10);
		
		
		em.persist(newProduct);
		
		newProduct = em.createQuery("from BasicProduct where description=:description", BasicProduct.class)
				.setParameter("description", newProduct.getDescription()).getSingleResult();
		
		newProduct.setDescription("newDescription");
		
		
		
		productDao.updateProduct(productMapper.generateBasicProductTO(newProduct));
		
		BasicProduct persistedProduct = em.createQuery("from BasicProduct where description=:description", BasicProduct.class)
				.setParameter("description", newProduct.getDescription()).getSingleResult();
		
		assertEquals ("newDescription", persistedProduct.getDescription());
	}
	
	@Test
	public void testUpdateDecorated(){
		BasicProduct newProduct = new BasicProduct();
		newProduct.setDescription("nuovo");
		newProduct.setQuantity(10);
		newProduct.setPrice((float) 0.15);
		
		PromoProduct newPromoProduct = new PromoProduct(newProduct, (float) 0.25);
		
		productDao.addProduct(newPromoProduct);
		
		newPromoProduct = (PromoProduct)em.createQuery("from PromoProduct", Product.class).getSingleResult();
		newProduct = em.createQuery("from BasicProduct", BasicProduct.class).getSingleResult();
		newPromoProduct.setDiscount((float)0.55);
		
		ProductDto prod = productMapper.generateBasicProductTO(newProduct);
		prod = productMapper.decorateProductTO(prod, newPromoProduct);
				
		productDao.updateProduct(prod);
		
		
		Product persistedProduct = em.createQuery("from PromoProduct", Product.class).getSingleResult();

		
		double epsilon = 0.001;
		
		assertTrue(((PromoProduct) persistedProduct).getDiscount() - 0.55 < epsilon);
	}
	
	
	@Test
	public void testDelete(){
		BasicProduct newProduct = new BasicProduct();
		newProduct.setDescription("nuovo");
		newProduct.setQuantity(10);
		newProduct.setPrice((float) 0.15);
		
		PromoProduct newPromoProduct = new PromoProduct(newProduct, (float) 0.25);
		
		productDao.addProduct(newPromoProduct);
		
		newPromoProduct = (PromoProduct)em.createQuery("from PromoProduct", Product.class).getSingleResult();
		
		int numDeleted = productDao.deleteDecoratedProduct(newPromoProduct.getProductLinker());
		
		assertEquals(numDeleted, 2); //deve cancelare sia il prodotto base che quello promo
		
	}
}
