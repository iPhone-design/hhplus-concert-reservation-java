package com.concert.reservation.domain.seat;

import com.concert.reservation.infrastructure.entity.SeatOption;
import com.concert.reservation.presentation.concert.SeatOptionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class SeatOptionCommand {
    public static SeatOptionDomain toDomain (SeatOption seatOption) {
        if (seatOption != null) {
            return SeatOptionDomain.builder()
                    .seatOptionId(seatOption.getSeatOptionId())
                    .seatId(seatOption.getSeatId())
                    .concertOptionId(seatOption.getConcertOptionId())
                    .price(seatOption.getPrice())
                    .status(seatOption.getStatus())
                    .build();
        }
        else {
            return null;
        }
    }

    public static SeatOption toEntity (SeatOptionDomain seatOptionDomain) {
        return SeatOption.builder()
                .seatOptionId(seatOptionDomain.getSeatOptionId())
                .seatId(seatOptionDomain.getSeatId())
                .concertOptionId(seatOptionDomain.getConcertOptionId())
                .price(seatOptionDomain.getPrice())
                .status(seatOptionDomain.getStatus())
                .build();
    }

    public static SeatOptionResponse toResponse (SeatOptionDomain seatOptionDomain) {
        return SeatOptionResponse.builder()
                .seatOptionId(seatOptionDomain.getSeatOptionId())
                .seatId(seatOptionDomain.getSeatId())
                .concertOptionId(seatOptionDomain.getConcertOptionId())
                .price(seatOptionDomain.getPrice())
                .status(seatOptionDomain.getStatus())
                .build();
    }

    public static List<SeatOptionResponse> toResponse (List<SeatOptionDomain> seatOptionDomain) {
        return seatOptionDomain.stream()
                .map(SeatOptionCommand::toResponse)
                .collect(Collectors.toList());
    }
}
