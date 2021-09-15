package com.demo.hm.ws.exception;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionsHandler{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = {EntityNotFoundException.class})
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request)
	{
		logger.error("entity_not_found : " + ex.getMessage());
		ErrorResponseModel dto = new ErrorResponseModel();
		dto.addFieldError("entity_not_found", ex.getMessage());
		//Error errorMessage = new Error("entity_not_found", ex.getMessage());
		
		return new ResponseEntity<>(dto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request)	{
		logger.error("internal_error : " + ex.getMessage());
		ErrorResponseModel dto = new ErrorResponseModel();
		dto.addFieldError("internal_error", ex.getMessage());
		//Error errorMessage = new Error("internal_error", ex.getMessage());
		return new ResponseEntity<>(dto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	 
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseModel processValidationError(MethodArgumentNotValidException ex) {
		logger.error("Request body validation failed : " + ex.getMessage());
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
 
        return processFieldErrors(fieldErrors);
    }
 
    private ErrorResponseModel processFieldErrors(List<FieldError> fieldErrors) {
    	ErrorResponseModel dto = new ErrorResponseModel();    	
    	fieldErrors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            dto.addFieldError(fieldName, errorMessage);
        });       
        return dto;
    }
    
    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponseModel handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String error = ex.getParameter() + " parameter is missing";
        ErrorResponseModel dto = new ErrorResponseModel();
		dto.addFieldError("path_parameter_missing", error);
		return dto;
        //return new ResponseEntity<Object>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }
}
