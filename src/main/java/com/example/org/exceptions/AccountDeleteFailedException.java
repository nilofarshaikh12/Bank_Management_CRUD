package com.example.org.exceptions;

public class AccountDeleteFailedException extends RuntimeException{
	
	public AccountDeleteFailedException(String message) {
		super(message);
	}

}
