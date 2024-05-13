package com.javaexpress.loans.exceptions;

public class LoanAlreadyExistInDBException extends RuntimeException {
    public LoanAlreadyExistInDBException(String msg) {
        super(msg);
    }
}
