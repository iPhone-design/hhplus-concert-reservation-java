package com.concert.reservation.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDomain {
    private Long customerId;
    private Long seatOptionId;
    private Long concertOptionId;
    private LocalDate date;

    private Long reservationId;
    private String status;
    private Timestamp reservationDt;
}
