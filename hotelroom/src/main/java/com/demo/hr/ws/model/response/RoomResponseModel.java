package com.demo.hr.ws.model.response;

public class RoomResponseModel {
	
	public RoomResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long roomId;

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public RoomResponseModel(Long roomId) {
		super();
		this.roomId = roomId;
	}
	
	

}
