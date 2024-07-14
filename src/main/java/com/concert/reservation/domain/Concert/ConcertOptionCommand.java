package com.concert.reservation.domain.concert;

import com.concert.reservation.infrastructure.entity.ConcertOption;
import com.concert.reservation.presentation.concert.ConcertOptionResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ConcertOptionCommand {
    public static ConcertOptionDomain toDomain (ConcertOption concertOption) {
        return ConcertOptionDomain.builder()
                .concertOptionId(concertOption.getConcertOptionId())
                .concertId(concertOption.getConcertId())
                .concertName(concertOption.getConcertName())
                .location(concertOption.getLocation())
                .openDt(concertOption.getOpenDt())
                .build();
    }

    public static ConcertOption toEntity (ConcertOptionDomain concertOptionDomain) {
        return ConcertOption.builder()
                .concertOptionId(concertOptionDomain.getConcertOptionId())
                .concertId(concertOptionDomain.getConcertId())
                .concertName(concertOptionDomain.getConcertName())
                .location(concertOptionDomain.getLocation())
                .openDt(concertOptionDomain.getOpenDt())
                .build();
    }

    public static ConcertOptionResponse toResponse (ConcertOptionDomain concertOptionDomain) {
        return ConcertOptionResponse.builder()
                .concertOptionId(concertOptionDomain.getConcertOptionId())
                .concertId(concertOptionDomain.getConcertId())
                .concertName(concertOptionDomain.getConcertName())
                .location(concertOptionDomain.getLocation())
                .openDt(concertOptionDomain.getOpenDt())
                .build();
    }

    public static List<ConcertOptionResponse> toResponse (List<ConcertOptionDomain> concertOptionDomain) {
        return concertOptionDomain.stream()
                                  .map(ConcertOptionCommand::toResponse)
                                  .collect(Collectors.toList());
    }
}
