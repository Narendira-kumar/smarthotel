package com.demo.hp.ws.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Transaction Dto details") 
public class TransactionDto {

	@ApiModelProperty(notes="Transaction Id")
	private Long transactionId;

	@ApiModelProperty(notes="Transaction Type")
	private String transactionType;

	@ApiModelProperty(notes="Bill Amount")
	private long billAmount;
	
	@NotNull(message = "User Id is missing")
	@ApiModelProperty(notes="User  Id")
	private Long userId;
	
	 

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public long getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(long billAmount) {
		this.billAmount = billAmount;
	}

	 

	 
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	 

	public TransactionDto(Long transactionId, String transactionType, long billAmount, Long userId) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.billAmount = billAmount;
		this.userId = userId;
		 
	}

	public TransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
 

	 


	 
	
}
