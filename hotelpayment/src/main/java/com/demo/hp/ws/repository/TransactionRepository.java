package com.demo.hp.ws.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hp.ws.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long>{
	
	Transaction findByTransactionId(Long transactionId);
	
	List<Transaction> findByUserId(Long  userId);

}
