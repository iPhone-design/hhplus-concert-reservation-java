package com.concert.reservation.interfaces.presentation.event;

import com.concert.reservation.domain.reservation.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEvent {
    private Long reservationId;
    private Long concertOptionId;
    private Long seatOptionId;
    private Long customerId;
    private ReservationStatus status;
    private LocalDateTime reservationDt;
}
