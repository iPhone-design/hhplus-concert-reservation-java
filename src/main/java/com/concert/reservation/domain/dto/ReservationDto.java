package com.concert.reservation.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReservationDto {
    private Long reservationId;
    private Integer seatId;
    private Long userId;
    private Timestamp reservationDt;
}
