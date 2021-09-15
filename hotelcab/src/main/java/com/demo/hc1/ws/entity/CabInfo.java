package com.demo.hc1.ws.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name= "CAB_INFO")
@ApiModel(description="Cab details")
public class CabInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CAB_ID", nullable = false)
	@ApiModelProperty(notes="Cab Id")
	private Long cabId;

	@Column(name = "DRIVER_NAME", length = 128)
	@ApiModelProperty(notes="Driver Name")
	private String driverName;
	
	@Column(name = "CABSTATUS", length = 128)
	@ApiModelProperty(notes="Cab Status")
	private String cabstatus;
	
	@Column(name = "PLACE", length = 40)
	@ApiModelProperty(notes="Place")
	private String place;
	
	@Column(name = "USER_ID", length = 40)
	@ApiModelProperty(notes="User Id")
	private Long userId;

	 
	@Column(name = "BOOKING_DATE")
	@ApiModelProperty(notes="Booking Date")
	private LocalDateTime bookingDate;


	public Long getCabId() {
		return cabId;
	}


	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}


	public String getDriverName() {
		return driverName;
	}


	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}


	public String getCabstatus() {
		return cabstatus;
	}


	public void setCabstatus(String cabstatus) {
		this.cabstatus = cabstatus;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public LocalDateTime getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}


	public CabInfo(Long cabId, String driverName, String cabstatus, String place, Long userId,
			LocalDateTime bookingDate) {
		super();
		this.cabId = cabId;
		this.driverName = driverName;
		this.cabstatus = cabstatus;
		this.place = place;
		this.userId = userId;
		this.bookingDate = bookingDate;
	}


	public CabInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	 
	 
}
