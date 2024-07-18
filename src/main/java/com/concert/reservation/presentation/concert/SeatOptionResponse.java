package com.concert.reservation.presentation.concert;

import com.concert.reservation.domain.seat.SeatOptionDomain;
import com.concert.reservation.domain.seat.SeatOptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatOptionResponse {
    private Long seatOptionId;
    private Long seatId;
    private Long concertOptionId;
    private Long price;
    private SeatOptionStatus status;

    public static List<SeatOptionResponse> toResponse(List<SeatOptionDomain> listSeatOptionDomain) {
        return listSeatOptionDomain.stream()
                                   .map(SeatOptionResponse::toResponse)
                                   .collect(Collectors.toList());
    }

    public static SeatOptionResponse toResponse(SeatOptionDomain seatOptionDomain) {
        return SeatOptionResponse.builder()
                                 .seatOptionId(seatOptionDomain.getSeatOptionId())
                                 .seatId(seatOptionDomain.getSeatId())
                                 .concertOptionId(seatOptionDomain.getConcertOptionId())
                                 .price(seatOptionDomain.getPrice())
                                 .status(seatOptionDomain.getStatus())
                                 .build();
    }
}

