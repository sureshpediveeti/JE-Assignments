package com.javaexpress.cards.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private HttpStatus httpStatus;
    private String errorMessage;
    private LocalDateTime errorDate;
}
