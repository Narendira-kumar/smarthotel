package com.demo.hm.ws.exception;

public enum ErrorMessages {
	
	    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
	    INTERNAL_SERVER_ERROR("Internal server error"),
	    NO_USER_RECORD_FOUND("Record with provided user id is not found"),
	    NO_ROOM_RECORD_FOUND("Record with provided Room id is not found");
	    
	    

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
