package com.demo.hr.ws.service;

import com.demo.hr.ws.dto.RoomBookDto;
import com.demo.hr.ws.dto.RoomDto;
import com.demo.hr.ws.entity.Room;

public interface RoomService {

	public Room createRoom(RoomDto roomDto);

	public String bookRoom(RoomBookDto roomBookDao);

	 
}
