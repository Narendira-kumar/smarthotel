package com.demo.hc1.ws.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.constraints.NotNull;

public class CabBookDto {
	
	@NotNull(message ="User id is missing")
	private Long userId;
	private String cabstatus;
	private String place;
	private LocalDateTime bookingDate;
 
	
	 
	 
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	 
	 
	public CabBookDto(@NotNull(message = "User id is missing") Long userId, String cabstatus, String place,
			LocalDateTime bookingDate) {
		super();
		this.userId = userId;
		this.cabstatus = cabstatus;
		this.place = place;
		this.bookingDate = bookingDate;
		 
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
	public CabBookDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	

}
