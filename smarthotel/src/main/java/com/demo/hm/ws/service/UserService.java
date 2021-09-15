package com.demo.hm.ws.service;

import com.demo.hm.ws.dto.UserEmailDto;
import com.demo.hm.ws.entity.User;

public interface UserService {

	public User createUser(User user);	
	public User getUser(long id);
	public String checkUser(long id);
	
	public UserEmailDto getUserEmailDetails(long id);
	
	
	
	 
}
