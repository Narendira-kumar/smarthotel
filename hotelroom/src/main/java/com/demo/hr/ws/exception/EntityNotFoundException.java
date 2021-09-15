package com.demo.hr.ws.exception;

public class EntityNotFoundException extends RuntimeException{
	
	public EntityNotFoundException(String message)
	{
		super(message);
	}
}
