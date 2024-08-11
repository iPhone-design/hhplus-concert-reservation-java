package com.concert.reservation.interfaces.controller.concert;

import com.concert.reservation.domain.concert.ConcertOptionDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOptionResponse {
    private Long concertOptionId;
    private Long concertId;
    private String concertName;
    private String location;
    private LocalDateTime openDt;

    public static List<ConcertOptionResponse> toResponse(List<ConcertOptionDomain> listConcertOptionDomain) {
        return listConcertOptionDomain.stream()
                                      .map(ConcertOptionResponse::toResponse)
                                      .collect(Collectors.toList());
    }

    public static ConcertOptionResponse toResponse(ConcertOptionDomain concertOptionDomain) {
        return ConcertOptionResponse.builder()
                .concertOptionId(concertOptionDomain.getConcertOptionId())
                .concertId(concertOptionDomain.getConcertId())
                .concertName(concertOptionDomain.getConcertName())
                .location(concertOptionDomain.getLocation())
                .openDt(concertOptionDomain.getOpenDt())
                .build();
    }
}

