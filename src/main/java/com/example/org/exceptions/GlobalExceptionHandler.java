package com.example.org.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.org.constants.Constants;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountAddFailedException.class)
	public ResponseEntity<ErrorResponse> handleAccountAddFailedException(AccountAddFailedException ex){
		
		ErrorResponse errorResponse=new ErrorResponse(Constants.ACCOUNT_ADD_FAILED, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex){
		
		ErrorResponse errorResponse=new ErrorResponse(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}
}
