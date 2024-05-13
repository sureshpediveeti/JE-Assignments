package com.javaexpress.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {
	
	private HttpStatus errorStatus;
	private String errorMessage;
	private LocalDateTime errorDateTime;

}
