package com.demo.hm.ws.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hm.ws.dto.RoomBookDto;
import com.demo.hm.ws.dto.RoomEntryDto;
import com.demo.hm.ws.dto.UserLogInDto;
import com.demo.hm.ws.dto.UserOTPDto;
import com.demo.hm.ws.service.UserRoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "UserRoom", description = "REST API for user room details", tags = "UserRoom")
public class UserRoomController {
	
	Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRoomService userRoomService;
	
	@ApiOperation(value = "${userRoomController.createLogin.ApiOperation}", notes = "${userRoomController.createlogin.ApiOperation.Notes}", tags = "UserRoom")
	@PostMapping(path = "/login", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public String loginUser(@Valid @RequestBody UserLogInDto userLogInDao) {
		LOG.info("Status check user room log in  method");
		String userRoom1 = userRoomService.loginUser(userLogInDao);
		return userRoom1;
	}
	
	
	@ApiOperation(value = "${userRoomController.sendOTP.ApiOperation}", notes = "${userRoomController.sendotp.ApiOperation.Notes}", tags = "UserRoom")
	//@PostMapping(path = "/sendOTP" , produces = { MediaType.APPLICATION_JSON_VALUE,
		@PostMapping(path = {"/sendOTP","/resendOTP"}, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public String sendOTP(@Valid @RequestBody UserOTPDto userOTPDao) {
		LOG.info("Status check send OTP method");
		String userRoom1 = userRoomService.sendOTP(userOTPDao);
		return userRoom1;
	}
	
	@ApiOperation(value = "${userRoomController.updateRoomDetails.ApiOperation}", notes = "${userRoomController.updateroomdetails.ApiOperation.Notes}", tags = "UserRoom")
	@PostMapping(path = "/updateRoomId", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public String updateRoomId(@RequestBody RoomBookDto roomBookDao) {
		LOG.info("Status check user room id  method");
		String userRoom1 = userRoomService.updateRoomId(roomBookDao);
		return userRoom1;
	}
	
	@ApiOperation(value = "${userRoomController.roomEntry.ApiOperation}", notes = "${userRoomController.roomentry.ApiOperation.Notes}", tags = "UserRoom")
	@PostMapping(path = "/roomEntry", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public String roomEntry(@Valid @RequestBody RoomEntryDto roomEntryDao) {
		LOG.info("Status check room entry method");

		String userRoom1 = userRoomService.roomEntry(roomEntryDao);
		return userRoom1;
	}
	
	@ApiOperation(value = "${userRoomController.roomExit.ApiOperation}", notes = "${userRoomController.roomexit.ApiOperation.Notes}", tags = "UserRoom")
	@PostMapping(path = "/roomExit", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public String roomExit(@Valid @RequestBody RoomEntryDto roomEntryDao) {
		LOG.info("Status check room exit method");
		String userRoom1 = userRoomService.roomExit(roomEntryDao);
		return userRoom1;
	}
}
