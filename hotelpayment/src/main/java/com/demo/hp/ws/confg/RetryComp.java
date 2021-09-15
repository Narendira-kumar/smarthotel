package com.demo.hp.ws.confg;

import org.springframework.stereotype.Component;

import com.demo.hp.ws.exception.EntityNotFoundException;

import io.github.resilience4j.retry.RetryConfig;

@Component
public class RetryComp{
	
	RetryConfig config = RetryConfig.custom()
		    .maxAttempts(2)
		    .ignoreExceptions(EntityNotFoundException.class)
		    .build();
	
	 

}
