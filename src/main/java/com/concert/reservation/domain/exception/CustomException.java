package com.concert.reservation.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final String detail;

    public CustomException(HttpStatus status, String detail) {
        this.status = status;
        this.detail = detail;
    }
}
