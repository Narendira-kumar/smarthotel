package com.demo.hc1.ws.exception;

public enum ErrorMessages {
	
	    
	    NO_USER_RECORD_FOUND("Record with provided user id is not found");
	   
	 public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

	private String errorMessage;

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
