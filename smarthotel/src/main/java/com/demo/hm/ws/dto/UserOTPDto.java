package com.demo.hm.ws.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="User OTP for Room Entry/Exit")
public class UserOTPDto {
	
	@NotNull(message = "User id is missing")
	@ApiModelProperty(notes="User Id")
	private Long userId;
	 
	
	public UserOTPDto(Long userId ) {
		super();
		this.userId = userId;
		 
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	 
	public UserOTPDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
