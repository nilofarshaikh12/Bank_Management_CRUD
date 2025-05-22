package com.example.org.exceptions;

public class AccountAddFailedException extends RuntimeException{
	
	public AccountAddFailedException(String message) {
		super(message);
	}

}
