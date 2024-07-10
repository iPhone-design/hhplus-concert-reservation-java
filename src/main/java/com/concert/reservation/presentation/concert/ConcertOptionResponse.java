package com.concert.reservation.presentation.concert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOptionResponse {
    private Long concertOptionId;
    private Long concertId;
    private String concertName;
    private String location;
    private Timestamp openDt;
}

