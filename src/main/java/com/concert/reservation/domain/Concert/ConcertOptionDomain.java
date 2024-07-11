package com.concert.reservation.domain.Concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOptionDomain {
    private Long concertOptionId;
    private Long concertId;
    private String concertName;
    private String location;
    private Timestamp openDt;
}