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
    private Integer price;
    private String status;
}
