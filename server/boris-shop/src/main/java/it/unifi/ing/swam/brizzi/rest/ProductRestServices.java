package it.unifi.ing.swam.brizzi.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	//c'è da implementare la cancellazione eh !
	@DELETE
	@Path("/delete/{itemID}/{productLinker}")
	public Response deleteProductByID(@PathParam("itemID") long id, @PathParam("productLinker") int linker){
		productController.deleteProduct(id, linker);
		return Response.ok().build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(String requestBody) throws Exception{
		try{
			String responseBody = productController.addProduct(requestBody);
			return Response.ok(responseBody, MediaType.APPLICATION_JSON).build();
		}catch (Exception e){
			return Response.notAcceptable(null).build();
		}
		
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduct(String requestBody){
		String responseBody = productController.updateProduct(requestBody);
		return Response.ok(responseBody, MediaType.APPLICATION_JSON).build();
		
	}
}


