package it.unifi.ing.swam.brizzi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import it.unifi.ing.swam.brizzi.dto.ProductDto;
import it.unifi.ing.swam.brizzi.model.BasicProduct;
import it.unifi.ing.swam.brizzi.model.Product;
import it.unifi.ing.swam.brizzi.model.ProductDecorator;
import it.unifi.ing.swam.brizzi.model.PromoProduct;
import it.unifi.ing.swam.brizzi.model.ReconditionedProduct;
import it.unifi.ing.swam.brizzi.model.BasicProduct;

public class ProductDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void addProduct(Product newProduct){
//			em.persist(newProduct);
		
		if( newProduct instanceof ProductDecorator ){//ha un product al suo interno
			addProduct(((ProductDecorator) newProduct).getProduct());
		}
		em.persist(newProduct);
		
	}
	
	
	public List<Product> getDecoratedProductByLinker(int ID){
		List<Product> product = null ;
		try{
			TypedQuery<Product> query = em.createQuery("from Product p where p.productLinker = :linker ", Product.class)
					.setParameter("linker", ID);
			product = query.getResultList();
		} catch (Exception e){
			e.printStackTrace();
		}
		return product;
	}
	
	
	@Transactional
	public void updateProduct(ProductDto updatedProduct){
//		Product oldProduct = em.find(Product.class, ID);
		List<Product> oldProduct = null ;
		try{
			TypedQuery<Product> query = em.createQuery("from Product p where p.productLinker = :linker ", Product.class)
					.setParameter("linker", updatedProduct.getProductLinker());
			oldProduct = query.getResultList();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		for(Product prod : oldProduct){
			if(prod instanceof BasicProduct){
				((BasicProduct) prod).setDescription((updatedProduct).getDescription());
				((BasicProduct) prod).setQuantity((updatedProduct).getQuantity());
				((BasicProduct) prod).setPrice((updatedProduct).getPrice());
			}
			else if(prod instanceof PromoProduct){
				((PromoProduct) prod).setDiscount((updatedProduct).getDiscount());
			}
			else if(prod instanceof ReconditionedProduct){
				((ReconditionedProduct) prod).setConditions((updatedProduct).getConditions());
			}
			
			
		}
	}

	public void retrieveAllProducts(List<BasicProduct> basicProducts, List<Product> decoratedProducts){
		List<Product> tmp = new ArrayList<>();
		try{
			TypedQuery<Product> query = em.createQuery("from Product ", Product.class);
			tmp = query.getResultList();
		} catch (Exception e){
			e.printStackTrace();
		}
		
		for(Product prod : tmp){
			if(prod instanceof BasicProduct){
				
				basicProducts.add((BasicProduct) prod);
			}
			else{
				decoratedProducts.add(prod);
			}
		}

	}
	
	@Transactional
	public  int deleteDecoratedProduct(int productLinker){
		int numDeleted = 0;
		try{
//			em.joinTransaction();
			Query query = em.createQuery(
				"DELETE FROM Product p WHERE p.productLinker = :linker")
				.setParameter("linker", productLinker);
			 numDeleted = query.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
		return numDeleted;
	}	
	
	
	@Transactional
	public int deleteProductByID(long ID){
		int numDeleted = 0;
		try{
//			em.joinTransaction();
			Query query = em.createQuery(
				"DELETE FROM Product p WHERE p.itemID = :id")
				.setParameter("id", ID);
			numDeleted = query.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return numDeleted;
	}	
	
	public Integer findQuantityByID(long ID){		
		em.getTransaction().begin();
		Integer tmp =  em.createQuery("SELECT p.quantity FROM Product p WHERE p.itemID = :id", Integer.class)
				.setParameter("id", ID)
				.getSingleResult();
		em.getTransaction().commit();
		return tmp;
	}
	
}
