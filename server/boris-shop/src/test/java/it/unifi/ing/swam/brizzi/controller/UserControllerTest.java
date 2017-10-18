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

import com.google.gson.Gson;

import it.unifi.ing.swam.brizzi.bean.mapper.UserMapper;
import it.unifi.ing.swam.brizzi.dao.UserDao;
import it.unifi.ing.swam.brizzi.dto.UserDto;
import it.unifi.ing.swam.brizzi.model.User;


public class UserControllerTest {

	private UserController userController;
	private UserDao userDao;
	private UserMapper userMapper;
	
	private User user1;
	private User user2;
	private List<User> allUsers;
	private List<UserDto> allUsersDto;
	private Long user1_ID;
	private Long user2_ID;
	
	
	@Before
	public void setUp() throws InitializationError{
		
		userController = new UserController();
		userMapper = new UserMapper();
		userDao = mock(UserDao.class);
		user1 = new User();
		user2 = new User();
		user1_ID = Long.valueOf(1);
		user2_ID = Long.valueOf(2);
		allUsers = new ArrayList<>();
		allUsersDto = new ArrayList<>();

		
		try{
			FieldUtils.writeField(user1, "userID", user1_ID, true); 
			FieldUtils.writeField(user2, "userID", user2_ID, true); 
			FieldUtils.writeField(userController, "userDao", userDao, true); //collego il mock del Dao alla classe da testare
			FieldUtils.writeField(userController, "userMapper", userMapper, true);
			
			allUsers.add(user1);
			allUsers.add(user2);
			allUsersDto.add(userMapper.generateUserTO(user1));
			allUsersDto.add(userMapper.generateUserTO(user2));
			
		}catch (IllegalAccessException e){
			throw new InitializationError(e);
		}
	}
	
	@Test
	public void testGetAllUsers() throws InitializationError{
		when(userDao.retrieveAllUsers()).thenReturn(allUsers);
		Gson gson = new Gson();
		assertEquals(gson.toJson(allUsersDto), userController.retrieveAllUsers());
			
	}

}
