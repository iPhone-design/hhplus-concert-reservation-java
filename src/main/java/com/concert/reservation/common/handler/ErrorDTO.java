package com.concert.reservation.common.handler;

import com.concert.reservation.domain.exception.CustomException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@Slf4j
public class ErrorDTO {
    private String status;
    private String message;

    public static ResponseEntity<ErrorDTO> toResponseEntity(CustomException ex) {
        log.info("Status ===> {}", ex.getStatus());
        log.info("Message ===> {}", ex.getDetail());

        return ResponseEntity
                .status(ex.getStatus())
                .body(ErrorDTO.builder()
                              .status(String.valueOf(ex.getStatus()))
                              .message(ex.getDetail())
                              .build());
    }
}
