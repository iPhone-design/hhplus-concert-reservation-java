package com.concert.reservation.interfaces.api.controller.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private Long reservationId;
    private Long concertOptionId;
    private Long seatOptionId;
    private Long customerId;
    private ReservationStatus status;
    private LocalDateTime reservationDt;

    public static ReservationResponse toResponse(ReservationDomain reservationDomain) {
        return ReservationResponse.builder()
                                  .reservationId(reservationDomain.getReservationId())
                                  .concertOptionId(reservationDomain.getConcertOptionId())
                                  .seatOptionId(reservationDomain.getSeatOptionId())
                                  .customerId(reservationDomain.getCustomerId())
                                  .status(reservationDomain.getStatus())
                                  .reservationDt(reservationDomain.getReservationDt())
                                  .build();
    }
}

