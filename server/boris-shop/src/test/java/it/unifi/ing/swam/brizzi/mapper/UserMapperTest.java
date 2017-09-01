package it.unifi.ing.swam.brizzi.mapper;

import it.unifi.ing.swam.brizzi.bean.mapper.UserMapper;
import it.unifi.ing.swam.brizzi.dto.UserDto;
import it.unifi.ing.swam.brizzi.model.User;

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

public class UserMapperTest {

	private UserMapper userMapper;
	private User user;
	private UserDto userDto;
	private User newUser;
	
	private long userID;
	private String password;
	private String username;
	
	@Before
	public void setUp() throws InitializationError{
		user = new User();		
		userDto = new UserDto();
		newUser = new User();
		
		userMapper = new UserMapper();
		userID = Long.valueOf(1);
		password = "password";
		username = "username";
		
		try{
			FieldUtils.writeField(user, "userID", userID, true);
			FieldUtils.writeField(user, "password", password, true);
			FieldUtils.writeField(user, "username", username, true);
			
			FieldUtils.writeField(userDto, "userID", userID, true);
			FieldUtils.writeField(userDto, "password", password, true);
			FieldUtils.writeField(userDto, "username", username, true);
			
			FieldUtils.writeField(newUser, "password", password, true);
			FieldUtils.writeField(newUser, "username", username, true);
			
		}catch (IllegalAccessException e){
			throw new InitializationError(e);
		}

	}
	
	@Test
	public void generateDtoTest(){
		assertEquals(userDto.getUserID(), userMapper.generateUserTO(user).getUserID());
		assertEquals(userDto.getUsername(), userMapper.generateUserTO(user).getUsername());
		assertEquals(userDto.getPassword(), userMapper.generateUserTO(user).getPassword());
		
		assertEquals(newUser.getPassword(), userMapper.generateUserFromDTO(userDto).getPassword());
		assertEquals(newUser.getUsername(), userMapper.generateUserFromDTO(userDto).getUsername());
		
	}
	

}
