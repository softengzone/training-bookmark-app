package com.softengzone.java.training.exception;

@SuppressWarnings("serial")
public class AccountNotFoundException extends RuntimeException {

	private static final String ERROR = "Account not found";
	
	public AccountNotFoundException() {
		super(ERROR);
	}
	
	public AccountNotFoundException(String error) {
		super(error);
	}
	
	
}
