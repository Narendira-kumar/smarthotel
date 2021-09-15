package com.demo.hp.ws.service1;

import com.demo.hp.ws.dto.TransactionDto;
import com.demo.hp.ws.entity.Transaction;

public interface TransactionService {
	
	
	public Transaction  createTransaction(TransactionDto transactionDto);
	
	public String userReport(long id);
	
	public String updateTransaction(long userId);
	
	}
