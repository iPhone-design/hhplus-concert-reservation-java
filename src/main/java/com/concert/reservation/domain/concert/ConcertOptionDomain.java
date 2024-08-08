package com.concert.reservation.domain.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOptionDomain {
    private Long concertOptionId;
    private Long concertId;
    private String concertName;
    private String location;
    private LocalDateTime openDt;

    public static ConcertOptionDomain toConcertOptionDomain(String serializedToken) {
        String[] parts = serializedToken.split(":");

        return ConcertOptionDomain.builder().concertOptionId(Long.valueOf(parts[0])).concertId(Long.valueOf(parts[1])).concertName(parts[2]).location(parts[3]).openDt(LocalDateTime.now()).build();
    }
}
