package com.example.org.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.org.constants.Constants;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AccountAddFailedException.class)
	public ResponseEntity<ResponseMessage<Void>> handleAccountAddFailedException(AccountAddFailedException ex){
		ResponseMessage<Void> response=new ResponseMessage<>(Constants.ACCOUNT_ADD_FAILED, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ResponseMessage<Void>> handleAccountNotFoundException(AccountNotFoundException ex){
		ResponseMessage<Void> response=new ResponseMessage<>(Constants.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccountUpdateFailedException.class)
	public ResponseEntity<ResponseMessage<Void>> handleAccountUpdateFailedException(AccountUpdateFailedException ex){
		ResponseMessage<Void> response=new ResponseMessage<>(Constants.ACCOUNT_UPDATE_FAILED, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountDeleteFailedException.class)
	public ResponseEntity<ResponseMessage<Void>> handleAccountDeleteFailedException(AccountDeleteFailedException ex){
		ResponseMessage<Void> response=new ResponseMessage<>(Constants.ACCOUNT_DELETE_FAILED, HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}
