package com.javaexpress.cards.exceptions;

public class CreditCardNotFoundInDBException extends RuntimeException {

    public CreditCardNotFoundInDBException(String msg) {
        super(msg);
    }
}
