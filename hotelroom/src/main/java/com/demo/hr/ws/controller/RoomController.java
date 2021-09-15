package com.demo.hr.ws.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hr.ws.dto.RoomBookDto;
import com.demo.hr.ws.dto.RoomDto;
import com.demo.hr.ws.entity.Room;
import com.demo.hr.ws.model.response.RoomResponseModel;
import com.demo.hr.ws.service.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Room", description = "REST API for Room", tags = "Room")
public class RoomController {

	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RoomService roomService;

	@ApiOperation(value = "${roomController.createRoom.ApiOperation}", notes = "${roomController.createroom.ApiOperation.Notes}", tags = "Room")
	@PostMapping(path = "/addRoom", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public RoomResponseModel createRoom(@RequestBody RoomDto roomDto) {
		LOG.info("Status check create room method");
		Room room1 = roomService.createRoom(roomDto);
		RoomResponseModel response= new RoomResponseModel();
		response.setRoomId(room1.getRoomId());
		return response;
	}
	
	 
	@ApiOperation(value = "${roomController.bookRoom.ApiOperation}", notes = "${roomController.bookroom.ApiOperation.Notes}", tags = "Room")
	@PostMapping(path = "/bookRoom", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public String bookRoom(@Valid @RequestBody RoomBookDto roomBookDao) {
		LOG.info("Status check book  room method");
		String room1 = roomService.bookRoom(roomBookDao);
		return room1;
	}
	
	 
}
