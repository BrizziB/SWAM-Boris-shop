package it.unifi.ing.swam.brizzi.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.ing.swam.brizzi.controller.ProductController;

@Path("/products")
public class ProductRestServices {

	@Inject
	ProductController productController;	

	
	@GET
	@Path("/list")
	public Response getProductList() {
		String productsJson = productController.retrieveAllProducts();
		return  Response.ok(productsJson, MediaType.APPLICATION_JSON).build();
	}
}


