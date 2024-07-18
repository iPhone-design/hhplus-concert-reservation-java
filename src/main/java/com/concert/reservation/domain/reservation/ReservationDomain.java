package com.concert.reservation.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDomain {
    private Long reservationId;
    private Long concertOptionId;
    private Long seatOptionId;
    private Long customerId;
    private ReservationStatus status;
    private LocalDateTime reservationDt;

    public void changeStatusToComplete() {
        this.status = ReservationStatus.COMPLETE;
    }

    public void changeStatusToIncomplete() {
        this.status = ReservationStatus.INCOMPLETE;
    }
}
