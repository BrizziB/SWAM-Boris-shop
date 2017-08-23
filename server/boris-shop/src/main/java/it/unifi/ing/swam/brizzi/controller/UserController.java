package it.unifi.ing.swam.brizzi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;

import it.unifi.ing.swam.brizzi.bean.mapper.UserMapper;
import it.unifi.ing.swam.brizzi.dao.OrderDao;
import it.unifi.ing.swam.brizzi.dao.UserDao;
import it.unifi.ing.swam.brizzi.dto.UserDto;
import it.unifi.ing.swam.brizzi.model.User;

public class UserController {

	@Inject
	private UserMapper userMapper;
	@Inject
	private UserDao userDao;
	@Inject
	private OrderDao orderDao;
	
	public String retrieveAllUsers(){
		List<UserDto> userDTOList = new ArrayList<>();
		List<User> userList = userDao.retrieveAllUsers();
		for(User user:userList){
			userDTOList.add(userMapper.generateUserTO(user));
		}
		Gson jsonSerializer = new Gson();
		return jsonSerializer.toJson(userDTOList);
		
	}
	public void deleteUser(long ID){
		
		orderDao.deleteOrdersByUserID(ID); //nel caso sia in qualche ordine si propaga la cancellazione
		
		int numDeleted = userDao.deleteUserByID(ID);
		if(numDeleted != 1)
			  System.out.println("error in deleting single user");
		}
	
	public String addUser(String body){
		Gson gson = new Gson();
		UserDto userDto = gson.fromJson(body, UserDto.class);
		User newUser = userMapper.generateUserFromDTO(userDto);
		userDao.addUser(newUser);
		return gson.toJson(userDto);  //così è un po' finto perchè rispondo con lo stesso oggetto che è era nella request, però in teoria potrei averlo lavorato o aver aggiunto dei campi, quindi credo abbia senso
		
	}

	public String updateUser(String body){
		Gson gson = new Gson();
		UserDto userDto = gson.fromJson(body, UserDto.class);
		User updatedUser = userMapper.generateUserFromDTO(userDto);
		long userID = userDto.getUserID();
		userDao.updateUser(updatedUser, userID);
		return gson.toJson(userDto);
	}
	
}
