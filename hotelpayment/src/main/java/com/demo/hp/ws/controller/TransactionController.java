package com.demo.hp.ws.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hp.ws.component.EmailService;
import com.demo.hp.ws.dto.TransactionDto;
import com.demo.hp.ws.entity.Transaction;
import com.demo.hp.ws.model.response.TransactionResponseModel;
import com.demo.hp.ws.service1.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Transaction", description = "REST API for transaction", tags = "Payment")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	

	@ApiOperation(value = "${transactionController.createTransaction.ApiOperation}", notes = "${transactionController.createtransaction.ApiOperation.Notes}", tags = "Payment") 
	@PostMapping(path = "/addTrans", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public TransactionResponseModel createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
		Transaction trans1= transactionService.createTransaction(transactionDto);
		TransactionResponseModel response = new TransactionResponseModel();
		response.setTransactionId(trans1.getTransactionId());
		return response ;
	}

	 
	@ApiOperation(value = "${transactionController.userReport.ApiOperation}", notes = "${transactionController.userreport.ApiOperation.Notes}", tags = "Payment")
	@PostMapping(path = "/userreport/{id}")
	public String userReport(@PathVariable("id") Long id) {
		String response = transactionService.userReport(id);
		return response;
	}
	
	@ApiOperation(value = "${transactionController.userSettlement.ApiOperation}", notes = "${transactionController.usersettlement.ApiOperation.Notes}", tags = "Payment")
	@PostMapping(path = "/settlement/{id}")
	public String userSettlement(@PathVariable("id") Long id) {
		String response = transactionService.updateTransaction(id);
		return response;
	}
	}
		  