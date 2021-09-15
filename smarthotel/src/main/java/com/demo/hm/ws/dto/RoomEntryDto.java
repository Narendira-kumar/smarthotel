package com.demo.hm.ws.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Room Entry/Exit for User")
public class RoomEntryDto {
	
	@NotNull(message = "User id is missing")
	@ApiModelProperty(notes="User Id")
	private Long userId;
	@NotNull(message = "UserOTP is missing")
	@ApiModelProperty(notes="User OTP")
	private String userOTP;
	
	public RoomEntryDto(Long userId, String userOTP) {
		super();
		this.userId = userId;
		this.userOTP = userOTP;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserOTP() {
		return userOTP;
	}
	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}
	public RoomEntryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
