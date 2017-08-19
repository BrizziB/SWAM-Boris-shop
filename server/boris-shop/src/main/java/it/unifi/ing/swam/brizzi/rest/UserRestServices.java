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

import it.unifi.ing.swam.brizzi.controller.UserController;

@Path("/users")
public class UserRestServices {
	
	@Inject
	UserController userController;
	
	@GET
	@Path("/list")
	public Response getUserList(){
		String usersJson = userController.retrieveAllUsers();
		return Response.ok(usersJson, MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("/delete/{userID}")
	public Response deleteUserByID(@PathParam("userID") long ID){
		userController.deleteUser(ID);
		return Response.ok().build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(String requestBody){
		String responseBody = userController.addUser(requestBody);
		return Response.ok(responseBody, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(String requestBody){
		String responseBody = userController.updateUser(requestBody);
		return Response.ok(responseBody, MediaType.APPLICATION_JSON).build();
	}
	
}
