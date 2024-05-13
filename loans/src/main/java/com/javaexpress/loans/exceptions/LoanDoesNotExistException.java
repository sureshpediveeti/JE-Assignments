package com.javaexpress.loans.exceptions;

public class LoanDoesNotExistException extends RuntimeException {

    public LoanDoesNotExistException(String msg) {
        super(msg);
    }
}
