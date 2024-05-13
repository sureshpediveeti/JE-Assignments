package com.javaexpress.accounts.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javaexpress.accounts.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity handleException(CustomerAlreadyExistsException ex) {
		
		ErrorResponseDto errorResponseDto =  new ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleException(Exception ex) {
		return new ErrorResponseDto(HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorResponseDto handleException(ResourceNotFoundException ex) {
		
		ErrorResponseDto errorResponseDto =  new ErrorResponseDto(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now());
		return errorResponseDto;
	}

}
