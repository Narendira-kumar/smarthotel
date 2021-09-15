package com.demo.hm.ws.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Log in User account details")
public class UserLogInDto {
	
	@NotNull(message = "User id is missing")
	@ApiModelProperty(notes="User Id")
	private Long userId;
	@NotNull(message = "Login is missing")
	@ApiModelProperty(notes="Login")
	private String login;
	@NotNull(message = "Password is missing")
	@ApiModelProperty(notes="Password")
	private String password;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserLogInDto(@NotNull(message = "User id is missing") Long userId,
			@NotNull(message = "Login is missing") String login,
			@NotNull(message = "Password is missing") String password) {
		super();
		this.userId = userId;
		this.login = login;
		this.password = password;
	}
	public UserLogInDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	
	 

}
