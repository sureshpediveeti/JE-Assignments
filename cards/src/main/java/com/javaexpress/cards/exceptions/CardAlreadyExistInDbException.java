package com.javaexpress.cards.exceptions;

public class CardAlreadyExistInDbException extends RuntimeException {

    public CardAlreadyExistInDbException(String msg) {
        super(msg);
    }
}
