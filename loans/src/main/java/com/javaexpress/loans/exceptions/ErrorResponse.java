package com.javaexpress.loans.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String errorMessage;
    private LocalDateTime errorDate;
}
