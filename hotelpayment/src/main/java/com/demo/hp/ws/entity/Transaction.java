package com.demo.hp.ws.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity(name= "TRANSACTION")
@ApiModel(description="Transaction details")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID", nullable = false)
	@ApiModelProperty(notes="Transaction Id")
	private Long transactionId;

	@Column(name = "TRANSACTION_TYPE", length = 128)
	@ApiModelProperty(notes="Transaction Type")
	private String transactionType;

	@Column(name = "BILLAMOUNT", length = 128)
	@ApiModelProperty(notes="Bill Amount")
	private long billAmount;
	
	@Column(name = "USER_ID", length = 40)
	@ApiModelProperty(notes="User Id")
	private Long userId;
	
	@Column(name = "TRANSACTION_STATUS", length = 40)
	@ApiModelProperty(notes="Transaction Status")
	private String transactionStatus;

	@Column(name = "TRANSACTION_DATE")
	@ApiModelProperty(notes="Transaction Date")
	private Date transactionDate;

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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	 

	 

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Transaction(Long transactionId, String transactionType, long billAmount, Long userId,
			String transactionStatus, Date transactionDate) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.billAmount = billAmount;
		this.userId = userId;
		this.transactionStatus = transactionStatus;
		this.transactionDate = transactionDate;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
 

	 


	 
	
}
