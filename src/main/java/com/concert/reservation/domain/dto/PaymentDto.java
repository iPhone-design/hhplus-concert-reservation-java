package com.concert.reservation.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PaymentDto {
    private Long paymentId;
    private Long userId;
    private Long concertId;
    private Integer amount;
    private String status;
    private Timestamp paymentDt;
}