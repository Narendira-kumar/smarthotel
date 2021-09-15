package com.demo.hr.ws.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name= "ROOM")
@ApiModel(description="Room details")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_ID", nullable = false)
	@ApiModelProperty(notes="Room Id")
	private Long roomId;
	 
	@Column(name = "ROOM_TYPE", length = 128)
	@ApiModelProperty(notes="Room Type")
	private String roomType;

	@Column(name = "CAPACITY", length = 128)
	@ApiModelProperty(notes="Capacity")
	private String capacity;

	@Column(name = "LOCATION", length = 25)
	@ApiModelProperty(notes="Location")
	private String location;

	@Column(name = "ROOM_STATUS", length = 25)
	@ApiModelProperty(notes="Room Status")
	private String roomStatus;

	@Column(name = "USER_ID", length = 40)
	@ApiModelProperty(notes="User Id")
	private Long userId;

	 @Column(name = "CHECK_IN_DATE")
	 @ApiModelProperty(notes="Check in date")
	private LocalDateTime checkInDate;

	@Column(name = "CHECK_OUT_DATE")
	@ApiModelProperty(notes="Check out date")
	private LocalDateTime checkOutDate;

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

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDateTime checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDateTime getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Room(Long roomId, String roomType, String capacity, String location, String roomStatus, Long userId,
			LocalDateTime checkInDate, LocalDateTime checkOutDate) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
		this.capacity = capacity;
		this.location = location;
		this.roomStatus = roomStatus;
		this.userId = userId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	 
	
	
}
