package com.demo.hm.ws.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name= "USER")
@ApiModel(description="User details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", nullable = false)
	@ApiModelProperty(notes="User Id")
	private Long userId;

	@Column(name = "FIRST_NAME", length = 128)
	@ApiModelProperty(notes="First Name")
	private String firstName;

	@Column(name = "LAST_NAME", length = 128)
	@ApiModelProperty(notes="Last Name")
	private String lastName;

	@Column(name = "PHONE_NUMBER", length = 25)
	@ApiModelProperty(notes="Phone Number")
	private String phoneNumber;

	@Email(message = "Email format is not valid")	
	@NotNull(message = "Email id is missing")
	@Column(name = "EMAIL", nullable = false, length = 255)
	@ApiModelProperty(notes="Email id is Mandatory")
	private String email;

	@Column(name = "STATE", length = 40)
	@ApiModelProperty(notes="State")
	private String state;

	@Column(name = "COUNTRY", length = 40)
	@ApiModelProperty(notes="Country")
	private String country;

	@Column(name = "BIRTHDATE_DAY", length = 2)
	@ApiModelProperty(notes="Birth Day")
	@Min(0)@Max(31)
	private int birthdateDay;

	@Column(name = "BIRTHDATE_MONTH", length = 2)
	@ApiModelProperty(notes="Birth Month")
	@Min(0)@Max(12)
	private int birthdateMonth;

	@Column(name = "BIRTHDATE_YEAR", length = 4)
	@ApiModelProperty(notes="Birth Year")
	private int birthdateYear;
	
	@Column(name = "IDENTITY_PROOF_NBR", length = 40)
	@ApiModelProperty(notes="Identity Proof Value Either Adhaar/PAN/Passport")
	private String identityProofNbr;

	@Column(name = "CREATION_DATE")
	@ApiModelProperty(notes="Creation Date")
	private LocalDateTime creationDate;

	@Column(name = "LAST_MODIFICATION_DATE")
	@ApiModelProperty(notes="Last Modification Date")
	private LocalDateTime modificationDate;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getBirthdateDay() {
		return birthdateDay;
	}

	public void setBirthdateDay(int birthdateDay) {
		this.birthdateDay = birthdateDay;
	}

	public int getBirthdateMonth() {
		return birthdateMonth;
	}

	public void setBirthdateMonth(int birthdateMonth) {
		this.birthdateMonth = birthdateMonth;
	}

	public int getBirthdateYear() {
		return birthdateYear;
	}

	public void setBirthdateYear(int birthdateYear) {
		this.birthdateYear = birthdateYear;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getIdentityProofNbr() {
		return identityProofNbr;
	}

	public void setIdentityProofNbr(String identityProofNbr) {
		this.identityProofNbr = identityProofNbr;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String firstName, String lastName, String phoneNumber, String email,
			String state, String country, int birthdateDay, int birthdateMonth, int birthdateYear,
			String identityProofNbr, LocalDateTime creationDate, LocalDateTime modificationDate) {
		super();
		this.userId = userId;
		 
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.state = state;
		this.country = country;
		this.birthdateDay = birthdateDay;
		this.birthdateMonth = birthdateMonth;
		this.birthdateYear = birthdateYear;
		this.identityProofNbr = identityProofNbr;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}

	 

	

}
