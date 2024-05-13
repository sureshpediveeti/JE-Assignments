package com.javaexpress.cards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GolbalExceptionHandler {

    @ExceptionHandler(CardAlreadyExistInDbException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleException(CardAlreadyExistInDbException ex) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(CreditCardNotFoundInDBException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleException(CreditCardNotFoundInDBException ex) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception ex) {
        return new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
    }
}
