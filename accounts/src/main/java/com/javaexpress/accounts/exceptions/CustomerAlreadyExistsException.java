package com.javaexpress.accounts.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException {
	
	public CustomerAlreadyExistsException(String msg) {
		super(msg);
	}

}
