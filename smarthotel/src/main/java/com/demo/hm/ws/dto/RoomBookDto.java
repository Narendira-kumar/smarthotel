package com.demo.hm.ws.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Room Booing From Room Microservice")
public class RoomBookDto {
	 
	@ApiModelProperty(notes="User Id")
	private Long userId;
	
	@ApiModelProperty(notes="Room Id")
	private Long roomId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public RoomBookDto(Long userId, Long roomId) {
		super();
		this.userId = userId;
		this.roomId = roomId;
	}
	 
	  
	

}
