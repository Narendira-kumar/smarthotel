package com.demo.hm.ws.service;

import com.demo.hm.ws.dto.RoomBookDto;
import com.demo.hm.ws.dto.RoomEntryDto;
import com.demo.hm.ws.dto.UserLogInDto;
import com.demo.hm.ws.dto.UserOTPDto;

public interface UserRoomService {
	public String loginUser(UserLogInDto userLogInDao);	
	 

	public String  sendOTP(UserOTPDto userOTPDao);
	
	public String  roomEntry(RoomEntryDto roomEntryDao);
	
	public String  roomExit(RoomEntryDto roomEntryDao);
	
	public String updateRoomId(RoomBookDto roomBookDao);
}
