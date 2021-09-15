package com.demo.hp.ws.exception;

public enum ErrorMessages {
	
	    
	    
	    NO_USER_RECORD_FOUND("Record with provided user id is not found"),
	    NO_TRANSACTION_RECORD_FOUND("No Transaction for this userid");
	    

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
