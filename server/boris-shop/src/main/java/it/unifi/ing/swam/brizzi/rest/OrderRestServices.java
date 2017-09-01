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

import it.unifi.ing.swam.brizzi.controller.OrderController;

@Path("/orders")
public class OrderRestServices {

	@Inject
	OrderController orderController;	

	
	@GET
	@Path("/list")
	public Response getProductList() {
		String productsJson = orderController.retrieveAllOrders();
		return  Response.ok(productsJson, MediaType.APPLICATION_JSON).build();
	}
	
	//c'è da implementare la cancellazione eh !
	@DELETE
	@Path("/delete/{orderID}")
	public Response deleteProductByID(@PathParam("orderID") long id){
		orderController.deleteOrder(id);
		return Response.ok().build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(String requestBody){
		String responseBody = orderController.addOrder(requestBody);
		return Response.ok(responseBody, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("/advance/{orderID}")
	public Response advanceProduct(@PathParam("orderID") long id){
		String responseBody = orderController.advanceOrderStatus(id);
		return Response.ok(responseBody, MediaType.APPLICATION_JSON).build();
		
	}
}


