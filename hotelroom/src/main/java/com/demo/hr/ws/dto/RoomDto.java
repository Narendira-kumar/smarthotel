package com.demo.hr.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Room Dto details")
public class RoomDto {

	@ApiModelProperty(notes="Room Id")
	private Long roomId;
	
	@ApiModelProperty(notes="Room Type")
	private String roomType;
	
	@ApiModelProperty(notes="Capacity")
	private String capacity;
	
	@ApiModelProperty(notes="Location")
	private String location;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public RoomDto(Long roomId, String roomType, String capacity, String location) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.capacity = capacity;
		this.location = location;
	}

	public RoomDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	 
}
