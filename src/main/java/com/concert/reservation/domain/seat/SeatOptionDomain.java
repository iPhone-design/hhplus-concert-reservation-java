package com.concert.reservation.domain.seat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatOptionDomain {
    private Long seatOptionId;
    private Long seatId;
    private Long concertOptionId;
    private Long price;
    private SeatOptionStatus status;

    public void changeStatusToAvailable() {
        this.status = SeatOptionStatus.AVAILABLE;
    }

    public void changeStatusToUnavailable() {
        this.status = SeatOptionStatus.UNAVAILABLE;
    }
}
