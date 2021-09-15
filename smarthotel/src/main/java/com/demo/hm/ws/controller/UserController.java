package com.demo.hm.ws.controller;

 

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hm.ws.dto.UserEmailDto;
import com.demo.hm.ws.entity.User;
import com.demo.hm.ws.model.response.UserResponseModel;
import com.demo.hm.ws.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Account", description = "REST API for user account", tags = "User")
public class UserController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;

	
	@ApiOperation(value = "${userController.createUser.ApiOperation}", notes = "${userController.createuser.ApiOperation.Notes}", tags = "User")
	@PostMapping(path = "/register", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public UserResponseModel createUser(@Valid @RequestBody User user) {
		LOG.info("Status check user  method");
		User user1 = userService.createUser(user);
		UserResponseModel response= new UserResponseModel();
		response.setUserId(user1.getUserId());
		return response;
	}
	
	@ApiOperation(value = "${userController.retrieveUser.ApiOperation}", notes = "${userController.retrieveuser.ApiOperation.Notes}", tags = "User")
	@GetMapping(path = "/user/{id}")
	public User getUser(@PathVariable("id") Long id) {
		LOG.info("Status check user Id method");
		User user1 = userService.getUser(id);
		return user1;
	}
	
	@ApiOperation(value = "${userController.checkUser.ApiOperation}", notes = "${userController.checkuser.ApiOperation.Notes}", tags = "User")
	@GetMapping(path = "/checkuser/{id}")
	public String checkUser(@PathVariable("id") Long id) {
		LOG.info("Status check user Id method");
		String response = userService.checkUser(id);
		return response;
	}

	@ApiOperation(value = "${userController.userEmailDetails.ApiOperation}", notes = "${userController.checkemaildetails.ApiOperation.Notes}", tags = "User")
	@GetMapping(path = "/userEmail/{id}")
	public UserEmailDto getUserEmail(@PathVariable("id") Long id) {
		LOG.info("Status get user email details method");
		UserEmailDto response = userService.getUserEmailDetails(id);
		return response;
	}
	
}
