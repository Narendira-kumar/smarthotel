package com.demo.hm.ws.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.hm.ws.dto.RoomBookDto;
import com.demo.hm.ws.dto.UserEmailDto;
import com.demo.hm.ws.entity.User;
import com.demo.hm.ws.repository.UserRepository;
import com.demo.hm.ws.service.UserService;

import static com.demo.hm.ws.exception.ConstantMessages.*;

import javax.persistence.EntityNotFoundException;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public User createUser(User user) {
	
		  //verify the given email exist in user table
	 
	    User existingUser = userRepository.findByEmail(user.getEmail()); 
	   	       
	    if(null != existingUser) {
	    	user.setUserId(existingUser.getUserId());
	    	user.setCreationDate(existingUser.getCreationDate());
	    }
		     userRepository.save(user);
		return   user ;
	}


	@Override
	public User getUser(long id) {
		 User existingUser = userRepository.findByUserId(id);
		return existingUser;
	}
	
	@Override
	public String checkUser(long id) {
		 User existingUser = userRepository.findByUserId(id);
		 if (null != existingUser)
		return SUCCESS;
		 else ;
		 return FALIURE;	 
	}


	@Override
	public UserEmailDto getUserEmailDetails(long id) {
		 
	    	User existingUser = userRepository.findByUserId(id);

			if (existingUser == null)
				throw new EntityNotFoundException(
						com.demo.hm.ws.exception.ErrorMessages.NO_USER_RECORD_FOUND.getErrorMessage());
			
			UserEmailDto userEmailDto = new UserEmailDto();
			userEmailDto.setFirstName(existingUser.getFirstName());
			userEmailDto.setLastName(existingUser.getLastName());
			userEmailDto.setEmail(existingUser.getEmail());
		
			return userEmailDto;
	}


	

}
