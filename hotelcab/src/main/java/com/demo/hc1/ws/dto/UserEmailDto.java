package com.demo.hc1.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="User Details for Email Transactions")
public class UserEmailDto {

	@ApiModelProperty(notes="User Id")
	private Long userId;
	@ApiModelProperty(notes="First Name")
	private String firstName;
	@ApiModelProperty(notes="Last Name")
	private String lastName;
	@ApiModelProperty(notes="Email")
	private String email;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserEmailDto(Long userId, String firstName, String lastName, String email) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public UserEmailDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
