package com.example.org.exceptions;

public class AccountNotFoundException extends RuntimeException{
	
	public AccountNotFoundException(String message) {
		super(message);
	}

}
