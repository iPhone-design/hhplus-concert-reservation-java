package com.concert.reservation.interfaces.presentation.common.handler;

import com.concert.reservation.domain.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorDTO> handleCustom400Exception(CustomException ex) {
        return ErrorDTO.toResponseEntity(ex);
    }
}
