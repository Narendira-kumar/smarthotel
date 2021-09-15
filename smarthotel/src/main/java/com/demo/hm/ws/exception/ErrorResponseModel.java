package com.demo.hm.ws.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponseModel {

	private List<Error> errors = new ArrayList<>();

	public ErrorResponseModel() {

	}

	public void addFieldError(String errorCode, String message) {
		Error error = new Error(errorCode, message);
		errors.add(error);
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

}
