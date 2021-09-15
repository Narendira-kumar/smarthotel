package com.demo.hm.ws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name="USER_ROOM")
@ApiModel(description = "User Room details")

public class UserRoom {

	@Id

	@NotNull(message = "userId is missing")
	@Column(name = "USER_ID", nullable = false)
	@ApiModelProperty(notes = "User Id")
	private Long userId;

	@Column(name = "USER_NAME", length = 128)
	@ApiModelProperty(notes = "Login")
	private String login;

	@Column(name = "PASSWORD", length = 255)
	@ApiModelProperty(notes = "Password")
	private String password;

	@Column(name = "USER_OTP", length = 40)
	@ApiModelProperty(notes = "User OTP")
	private String userOTP;

	@Column(name = "ROOM_ID")
	@ApiModelProperty(notes = "Room Id")
	private Long roomId;

	@Column(name = "USER_ROOM_STATUS")
	@ApiModelProperty(notes = "User Room Status")
	private String userRoomStatus;

	@Column(name = "USER_OTP_ACCESSED")
	@ApiModelProperty(notes = "User OTP Accessed")
	private String userOTPAccessed;

	public String getUserOTPAccessed() {
		return userOTPAccessed;
	}

	public void setUserOTPAccessed(String userOTPAccessed) {
		this.userOTPAccessed = userOTPAccessed;
	}

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

	public String getUserOTP() {
		return userOTP;
	}

	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public UserRoom(@NotNull(message = "userId is missing") Long userId, String login, String password, String userOTP,
			Long roomId, String userRoomStatus, String userOTPAccessed) {
		super();
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.userOTP = userOTP;
		this.roomId = roomId;
		this.userRoomStatus = userRoomStatus;
		this.userOTPAccessed = userOTPAccessed;
	}

	public String getUserRoomStatus() {
		return userRoomStatus;
	}

	public void setUserRoomStatus(String userRoomStatus) {
		this.userRoomStatus = userRoomStatus;
	}

	public UserRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

}
