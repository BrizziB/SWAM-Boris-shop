package it.unifi.ing.swam.brizzi.bean.mapper;

import it.unifi.ing.swam.brizzi.dto.UserDto;
import it.unifi.ing.swam.brizzi.model.User;

public class UserMapper {

	public UserDto generateUserTO(User user){
		UserDto userDto = new UserDto();
		userDto.setUserID(user.getUserID());
		userDto.setUsername(user.getUsername());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
	
	public User generateUserFromDTO(UserDto dto){
		User user = new User();
		user.setPassword(dto.getPassword());
		user.setUsername(dto.getUsername());
		return user;
	}

}
