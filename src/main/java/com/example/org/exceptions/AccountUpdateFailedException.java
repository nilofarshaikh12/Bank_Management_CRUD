package com.example.org.exceptions;

public class AccountUpdateFailedException extends RuntimeException{
	
	public AccountUpdateFailedException(String message) {
		super(message);
	}

}
