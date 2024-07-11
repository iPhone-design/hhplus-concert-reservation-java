package com.concert.reservation.presentation.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatOptionResponse {
    private Long seatOptionId;
    private Long seatId;
    private Long concertOptionId;
    private Long price;
    private String status;
}

